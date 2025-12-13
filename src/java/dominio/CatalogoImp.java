package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Catalogo.Rol;
import pojo.Catalogo.TipoUnidad;

public class CatalogoImp {
    public static List<Rol> obtenerRoles(){
        List<Rol> roles = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                roles = conexionBD.selectList("catalogo.obtener-roles");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return roles;
    }
    
    public static List<TipoUnidad> obtenerTiposUnidad(){
        List<TipoUnidad> tiposUnidad = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                tiposUnidad = conexionBD.selectList("catalogo.obtener-tipos-unidad");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return tiposUnidad;
    }
}
