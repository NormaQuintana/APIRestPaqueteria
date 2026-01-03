package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Unidad;
import utilidades.Constantes;

public class UnidadImp {

    public static List<Unidad> obtenerUnidades(){
        List<Unidad> unidades = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                unidades = conexionBD.selectList("unidad.obtener-todas");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return unidades;
    }
    
    public static Respuesta registrar(Unidad unidad){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Integer existeVin = conexionBD.selectOne("unidad.verificar-vin", unidad);
                if (existeVin != null && existeVin > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Ya existe una unidad registrada con el mismo VIN.");
                    return respuesta;
                }

                Integer existeNii = conexionBD.selectOne("unidad.verificar-nii", unidad);
                if (existeNii != null && existeNii > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Ya existe una unidad registrada con el mismo Número de Identificación Interno.");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.insert("unidad.registrar", unidad);

                if(filasAfectadas > 0){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Unidad: " + unidad.getNoIdentificacion() + " registrada correctamente");
                }else{
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la unidad no fue agregada.");
                }
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                 conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Respuesta editarUnidad(Unidad unidad){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Integer existeNii = conexionBD.selectOne("unidad.verificar-nii-edicion", unidad);
                if (existeNii != null && existeNii > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Ya existe otra unidad registrada con el mismo Número de Identificación Interno.");
                    return respuesta;
                }

                int filasAfectadas = conexionBD.update("unidad.editar", unidad);

                if(filasAfectadas > 0){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Unidad con NII: " + unidad.getNoIdentificacion() + " actualizada correctamente.");
                }else{
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la unidad no fue editada. Verifique la información.");
                }
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                 conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
 
    public static Respuesta darBajaUnidad(Unidad unidad){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.update("unidad.dar-baja", unidad);

                if(filasAfectadas > 0){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Unidad dada de baja exitosamente.");
                }else{
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, no se encontró la unidad con ese ID.");
                }
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            } finally {
                 conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static List<Unidad> buscarUnidades(Unidad unidad){
    List<Unidad> unidades = null;
    SqlSession conexionBD = MyBatisUtil.getSession();
    if(conexionBD != null){
        try{
            unidades = conexionBD.selectList("unidad.buscar-unidades", unidad);
            conexionBD.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
        return unidades;
    }
    
    public static Respuesta asignarConductor(Unidad unidad) {
    Respuesta respuesta = new Respuesta();
    SqlSession conexionBD = MyBatisUtil.getSession();
    
    Integer idConductorAAsignar = unidad.getIdColaborador();
    
    if (idConductorAAsignar != null && idConductorAAsignar == 0) {
        idConductorAAsignar = null;
        unidad.setIdColaborador(null); 
    }

    if (conexionBD != null) {
        try {
  
            if (idConductorAAsignar != null && idConductorAAsignar > 0) {
                
               
                Integer conteo = conexionBD.selectOne("unidad.verificar-conductor-asignado", unidad);
                
                if (conteo != null && conteo > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Error: El conductor ya tiene otra unidad asignada. Debe desasignarla primero.");
                    return respuesta; 
                }
            }

               
                int filasAfectadas = conexionBD.update("unidad.asignar-conductor", unidad);

                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);

                    if (unidad.getIdColaborador() != null && unidad.getIdColaborador() > 0) {
                        respuesta.setMensaje("Conductor ID " + unidad.getIdColaborador() + " asignado a Unidad ID " + unidad.getIdUnidad() + " correctamente.");
                    } else {
                        respuesta.setMensaje("Conductor desasignado de Unidad ID " + unidad.getIdUnidad() + " correctamente.");
                    }

                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, no se encontró la Unidad ID " + unidad.getIdUnidad() + " para actualizar.");
                }

            } catch (Exception e) {
                conexionBD.rollback();
                e.printStackTrace();
                respuesta.setError(true);
                respuesta.setMensaje("Error en la base de datos al asignar/desasignar conductor: " + e.getMessage());
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
