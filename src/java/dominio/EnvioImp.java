package dominio;

import dto.Respuesta;
import java.util.List;
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

    public static Envio obtenerEnvioPorId(Integer idEnvio) {
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

    public static Envio obtenerEnvioPorNoGuia(String noGuia) {
        Envio envio = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                envio = conexionBD.selectOne("envio.obtener-por-noGuia", noGuia);
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
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Integer existe = conexionBD.selectOne("envio.verificar-existencia", envio);
                if (existe != null && existe > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Error: El número de guía ya se encuentra registrado");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.insert("envio.registrar", envio);

                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Envío registrado correctamente con guía: " + envio.getNoGuia());
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, el envío no fue registrado. Verifica la información.");
                }

            } catch (Exception e) {
                try { conexionBD.rollback(); } catch (Exception ex) { /* ignorar */ }
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

    public static Respuesta editarEnvio(Envio envio) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Integer existe = conexionBD.selectOne("envio.verificar-existencia", envio);
                if (existe != null && existe > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Error: El número de guía ya se encuentra registrado");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.update("envio.editar", envio);

                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Envío editado correctamente (ID: " + envio.getIdEnvio() + ")");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, el envío no fue editado. Verifica la información.");
                }

            } catch (Exception e) {
                try { conexionBD.rollback(); } catch (Exception ex) {  }
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

    public static Respuesta eliminarEnvio(Integer idEnvio) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
               
                int filasAfectadas = conexionBD.delete("envio.eliminar", idEnvio);

                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Envío eliminado correctamente.");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró un envío con ese ID.");
                }

            } catch (Exception e) {
                try { conexionBD.rollback(); } catch (Exception ex) { /* ignorar */ }
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
