package dominio;

import dto.Respuesta;
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Colaborador;
import utilidades.Constantes;

public class ColaboradorImp {
    public static List<Colaborador> obtenerColaboradores(){
        List<Colaborador> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                colaboradores = conexionBD.selectList("colaborador.obtener-todos");
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static List<Colaborador> obtenerColaboradoresPorRol(Integer idRol){
        List<Colaborador> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                colaboradores = conexionBD.selectList("colaborador.obtener-por-rol", idRol);
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static Colaborador obtenerColaboradoresPorNoPersonal(String noPersonal){
        Colaborador colaborador = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                colaborador = conexionBD.selectOne("colaborador.obtener-por-noPersonal", noPersonal);
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return colaborador;
    }
    
    public static List<Colaborador> obtenerColaboradoresPorNombre(String nombre){
        List<Colaborador> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                colaboradores = conexionBD.selectList("colaborador.obtener-por-nombre", nombre);
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    
    public static Respuesta registrarColaborador(Colaborador colaborador){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Integer existe = conexionBD.selectOne("colaborador.verificar-existencia", colaborador);
                if (existe != null && existe > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Error: El Curp, correo o no.Personal ya se encuentran registrados");
                    return respuesta; 
                }
                int filasAfectadas = conexionBD.insert("colaborador.registrar", colaborador);
                if(filasAfectadas > 0){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Colaborador: " + colaborador.getNombre() + " registrado correctamente");
                }else{
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, el colaborador no fue agregado, favor de verificar la infromacion");                    
                }
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }finally{
                if (conexionBD != null) { 
                    conexionBD.close(); 
                }
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Respuesta editar(Colaborador colaborador){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Integer existe = conexionBD.selectOne("colaborador.verificar-existencia", colaborador);
                if (existe != null && existe > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Error: El Curp, correo o no.Personal ya se encuentran registrados");
                    return respuesta; 
                }
                int filasAfectadas = conexionBD.update("colaborador.editar", colaborador);
                if(filasAfectadas > 0){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Colaborador: " + colaborador.getNombre() + " editado correctamente");
                }else{
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, la sucursal no fue editada, favor de verificar la infromacion");
                }  
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }finally{
                if (conexionBD != null) { 
                    conexionBD.close(); 
                }
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Respuesta eliminarColaborador(Integer idColaborador){
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
               int filasAfectadas = conexionBD.update("colaborador.eliminar", idColaborador);
                if(filasAfectadas > 0 ){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Colaborador eliminado exitosamente");
                }else{
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, no se encontró el colaborador con ese ID.");
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
    
    public static Respuesta registrarFoto(int idColaborador, byte[] foto){
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                Colaborador colaborador = new Colaborador();
                colaborador.setIdColaborador(idColaborador);
                colaborador.setFoto(foto);
                int filasAfectadas = conexionBD.update("colaborador.guardar-foto", colaborador);
                if(filasAfectadas > 0){
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("La fotografia ha sido guardada exitosamente");
                }else{
                    respuesta.setMensaje("Lo sientimos, no se pudo guardar la imagen, intentelo mas tarde");
                }
            }catch(Exception e){
                respuesta.setMensaje(e.getMessage());
            }finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }else{
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
    
    public static Colaborador obtenerFoto(Integer idColaborador){
        Colaborador colaborador = new Colaborador();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                colaborador = conexionBD.selectOne("colaborador.obtener-foto", idColaborador);
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return colaborador;
    }
}
