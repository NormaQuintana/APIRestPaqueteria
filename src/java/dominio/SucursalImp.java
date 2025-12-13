package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Sucursal;

public class SucursalImp {
    public static List<Sucursal> obtenerSucursales(){
        List<Sucursal> sucursales = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                sucursales = conexionBD.selectList("sucursal.obtener-todas");
                conexionBD.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            
        }
        return sucursales;
        
    
}
}
