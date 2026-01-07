package dominio;
import java.util.UUID;
import dto.Respuesta;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        session = MyBatisUtil.getSession(); // o como lo abras tú
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
        if (session != null) session.rollback();
        r.setError(true);
        r.setMensaje(ex.getMessage());
        return r;

    } finally {
        if (session != null) session.close();
    }
}

public static Respuesta eliminarEnvio(Integer idEnvio){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
               int filasAfectadas = conexionBD.update("envio.eliminar", idEnvio);
                if(filasAfectadas > 0 ){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Envio eliminado exitosamente");
                }else{
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, no se encontró el envio con ese ID.");
                }
                conexionBD.close(); 
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
}
