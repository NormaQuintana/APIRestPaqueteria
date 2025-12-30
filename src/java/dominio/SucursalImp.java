package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Sucursal;
import utilidades.Constantes;

public class SucursalImp {

    public static List<Sucursal> obtenerSucursales() {
        List<Sucursal> sucursales = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                sucursales = conexionBD.selectList("sucursal.obtener-todas");
                conexionBD.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return sucursales;
    }

    public static Respuesta registrar(Sucursal sucursal) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                Integer existe = conexionBD.selectOne("sucursal.verificar-existencia", sucursal);
                if (existe != null && existe > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Ya existe una sucursal registrada con la misma dirección y nombre.");
                    return respuesta;
                }
                int filasAfectadas = conexionBD.insert("sucursal.registrar", sucursal);
                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Sucursal: " + sucursal.getNombre() + " registrada correctamente");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la sucursal no fue agregada, favor de verificar la infromacion");

                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }

    public static Respuesta editarSucursal(Sucursal sucursal) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                Integer existe = conexionBD.selectOne("sucursal.verificar-edicion", sucursal);
                if (existe != null && existe > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Ya existe una sucursal registrada con la misma dirección y nombre.");
                    return respuesta;
                }
                int filasAfectadas = conexionBD.update("sucursal.editar", sucursal);
                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Sucursal: " + sucursal.getNombre() + " actualizada correctamente");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la sucursal no fue editada, favor de verificar la infromacion");

                }
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }

        return respuesta;
    }

    public static Respuesta eliminarSucursal(int idSucursal) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("sucursal.eliminar", idSucursal);
                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Sucursal eliminada exitosamente");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, no se encontró la sucursal con ese ID.");
                }
                conexionBD.close();
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
}
