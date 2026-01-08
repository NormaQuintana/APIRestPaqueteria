package dominio;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.Respuesta;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Envio;
import utilidades.Constantes;

public class EnvioImp {

    public static List<Envio> obtenerEnvios() {
        List<Envio> envios = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                envios = conexionBD.selectList("envio.obtener-todos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return envios;
    }

    public static List<Envio> obtenerEnviosPorConductor(int idColaborador) {
        List<Envio> envios = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                envios = conexionBD.selectList("envio.obtener-todos-conductor", idColaborador);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return envios;
    }

    public static Envio obtenerPorId(int idEnvio) {
        Envio envio = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                envio = conexionBD.selectOne("envio.obtener-por-id", idEnvio);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return envio;
    }

    public static Envio obtenerPorGuia(String noGuia) {
        Envio envio = null;
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                envio = conexionBD.selectOne("envio.obtener-por-guia", noGuia);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return envio;
    }

    public static Respuesta registrarEnvio(Envio envio) {
        Respuesta respuesta = new Respuesta();

        if (envio.getNoGuia() == null || envio.getNoGuia().trim().isEmpty()) {
            String guia = "PW-" + UUID.randomUUID().toString()
                    .replace("-", "")
                    .substring(0, 12)
                    .toUpperCase();
            envio.setNoGuia(guia);
        }

        // por si tu tabla no permite null o quieres que arranque en 0
        envio.setCosto((float) 0.0);

        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.insert("envio.registrar", envio);
                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setIdGenerado(envio.getIdEnvio());
                    respuesta.setMensaje("Envío registrado correctamente. Guía: " + envio.getNoGuia());
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo registrar el envío, verifique la información.");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                String msg = (e.getMessage() != null) ? e.getMessage() : e.toString();
                respuesta.setMensaje(msg);
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }

    private static double obtenerDistanciaKm(int cpOrigen, int cpDestino) throws Exception {
        String urlStr = "http://sublimas.com.mx:8080/calculadora/api/envios/distancia/" + cpOrigen + "," + cpDestino;
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);

        int code = conn.getResponseCode();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (code >= 200 && code < 300) ? conn.getInputStream() : conn.getErrorStream()
        ));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        if (code < 200 || code >= 300) {
            throw new RuntimeException("WS distancia respondió HTTP " + code + ": " + sb.toString());
        }

        JsonObject json = new Gson().fromJson(sb.toString(), JsonObject.class);

        if (json.has("error") && json.get("error").getAsBoolean()) {
            String msg = json.has("mensaje") ? json.get("mensaje").getAsString() : "Error en WS distancia";
            throw new RuntimeException(msg);
        }

        if (!json.has("distanciaKM")) {
            throw new RuntimeException("Respuesta sin distanciaKM: " + sb.toString());
        }

        return json.get("distanciaKM").getAsDouble();
    }

    public static Respuesta recalcularCostoEnvio(int idEnvio) {
        Respuesta r = new Respuesta();
        SqlSession session = MyBatisUtil.getSession();

        if (session == null) {
            r.setError(true);
            r.setMensaje(Constantes.MSJ_ERROR_BD);
            return r;
        }

        try {
            Envio envio = session.selectOne("envio.obtener-por-id", idEnvio);
            if (envio == null) {
                r.setError(true);
                r.setMensaje("No existe el envío con id " + idEnvio);
                return r;
            }

            Integer cpOrigen = session.selectOne("sucursal.obtener-cp-por-id", envio.getIdSucursal());
            Integer cpDestino = session.selectOne("envio.obtener-cp-destino-por-envio", idEnvio);

            if (cpOrigen == null || cpOrigen < 10000 || cpOrigen > 99999) {
                r.setError(true);
                r.setMensaje("CP origen inválido: " + cpOrigen);
                return r;
            }

            if (cpDestino == null || cpDestino < 10000 || cpDestino > 99999) {
                r.setError(true);
                r.setMensaje("CP destino inválido: " + cpDestino);
                return r;
            }

            Integer numPaquetes = session.selectOne("paquete.contar-por-envio", idEnvio);
            if (numPaquetes == null) {
                numPaquetes = 0;
            }

            double distanciaKm = obtenerDistanciaKm(cpOrigen, cpDestino);

            double costoPorKm;
            if (distanciaKm >= 1 && distanciaKm <= 200) {
                costoPorKm = 4.0;
            } else if (distanciaKm <= 500) {
                costoPorKm = 3.0;
            } else if (distanciaKm <= 1000) {
                costoPorKm = 2.0;
            } else if (distanciaKm <= 2000) {
                costoPorKm = 1.0;
            } else {
                costoPorKm = 0.5;
            }

            double adicional;
            if (numPaquetes <= 1) {
                adicional = 0.0;
            } else if (numPaquetes == 2) {
                adicional = 50.0;
            } else if (numPaquetes == 3) {
                adicional = 80.0;
            } else if (numPaquetes == 4) {
                adicional = 110.0;
            } else {
                adicional = 150.0;
            }

            double costoTotal = (distanciaKm * costoPorKm) + adicional;

            envio.setCosto((float) costoTotal);
            int filas = session.update("envio.actualizar-costo", envio);

            if (filas > 0) {
                session.commit();
                r.setError(false);
                r.setMensaje("Costo recalculado correctamente: $" + String.format("%.2f", costoTotal));
            } else {
                session.rollback();
                r.setError(true);
                r.setMensaje("No se pudo actualizar el costo del envío.");
            }

            return r;

        } catch (Exception ex) {
            session.rollback();
            r.setError(true);
            String msg = (ex.getMessage() != null) ? ex.getMessage() : ex.toString();
            r.setMensaje(msg);
            return r;

        } finally {
            session.close();
        }
    }

    public static Respuesta actualizarEnvio(Envio envio) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("envio.editar", envio);
                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Envío actualizado correctamente.");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo actualizar el envío.");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }

    public static Respuesta actualizarEstatus(int idEnvio, int idEstatusEnvio, int idColaborador, String comentario) {
        Respuesta r = new Respuesta();
        SqlSession session = null;

        try {
            session = MyBatisUtil.getSession();
            Map<String, Object> params = new HashMap<>();
            params.put("idEnvio", idEnvio);
            params.put("idEstatusEnvio", idEstatusEnvio);
            params.put("idColaborador", idColaborador);
            params.put("comentario", comentario);

            session.insert("envio.insertar-historial-estatus", params);
            session.update("envio.actualizar-estatus-envio", params);

            session.commit();

            r.setError(false);
            r.setMensaje("Estatus actualizado correctamente.");
            return r;

        } catch (Exception ex) {
            if (session != null) {
                session.rollback();
            }
            r.setError(true);
            r.setMensaje(ex.getMessage());
            return r;

        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Respuesta eliminarEnvio(Integer idEnvio) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("envio.eliminar", idEnvio);
                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Envio eliminado exitosamente");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, no se encontró el envio con ese ID.");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }

        return respuesta;
    }
}
