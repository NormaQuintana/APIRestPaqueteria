package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Paquete;
import utilidades.Constantes;

public class PaqueteImp {

    public static List<Paquete> obtenerPaquetes() {
        List<Paquete> paquetes = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.obtener-todos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return paquetes;
    }

    public static Paquete obtenerPaquetePorId(Integer idPaquete) {
        Paquete paquete = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                paquete = conexionBD.selectOne("paquete.obtener-por-id", idPaquete);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return paquete;
    }

    public static List<Paquete> obtenerPaquetesPorEnvio(Integer idEnvio) {
        List<Paquete> paquetes = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                paquetes = conexionBD.selectList("paquete.obtener-por-envio", idEnvio);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return paquetes;
    }

    public static Respuesta registrarPaquete(Paquete paquete) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                // Validación de BD: el envío debe existir (evita error de FK y da mensaje claro)
                Integer existeEnvio = conexionBD.selectOne("paquete.existe-envio", paquete.getIdEnvio());
                if (existeEnvio == null || existeEnvio <= 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se puede registrar el paquete: el envío no existe.");
                    return respuesta;
                }

                int filas = conexionBD.insert("paquete.registrar", paquete);
                if (filas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Paquete registrado correctamente (ID: " + paquete.getIdPaquete() + ")");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo registrar el paquete. Verifica la información.");
                }
            } catch (Exception e) {
                try { conexionBD.rollback(); } catch (Exception ex) { }
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

    public static Respuesta editarPaquete(Paquete paquete) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                Integer existeEnvio = conexionBD.selectOne("paquete.existe-envio", paquete.getIdEnvio());
                if (existeEnvio == null || existeEnvio <= 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se puede editar el paquete: el envío no existe.");
                    return respuesta;
                }

                int filas = conexionBD.update("paquete.editar", paquete);
                if (filas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Paquete editado correctamente.");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró el paquete o no se pudo editar.");
                }
            } catch (Exception e) {
                try { conexionBD.rollback(); } catch (Exception ex) { }
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

    public static Respuesta eliminarPaquete(Integer idPaquete) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                int filas = conexionBD.delete("paquete.eliminar", idPaquete);
                if (filas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Paquete eliminado correctamente.");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("No se encontró un paquete con ese ID.");
                }
            } catch (Exception e) {
                try { conexionBD.rollback(); } catch (Exception ex) { }
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
