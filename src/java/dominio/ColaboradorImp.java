package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Colaborador;

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
}
