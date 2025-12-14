package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Catalogo.EstatusEnvio;

public class EstatusEnvioImp {
    
    public static List<EstatusEnvio> obtenerEstatusEnvio(){
        List<EstatusEnvio> estatus = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try{
                estatus = conexionBD.selectList("estatusEnvio.obtener-estatusEnvio");
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return estatus;
    }
}
