package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.HistorialEnvio;

public class HistorialEnvioImp {

    public static boolean registrarHistorialEnvio(HistorialEnvio historial) {
        boolean exito = false;
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                int resultado = conexionBD.insert("historialEnvio.registrar-historialEnvio", historial);
                
                if (resultado > 0) {
                    conexionBD.commit();
                    exito = true;
                } else {
                    conexionBD.rollback();
                }
            } catch (Exception e) {
                conexionBD.rollback();
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return exito;
    }

    public static List<HistorialEnvio> consultarHistorialPorGuia(String noGuia) {
        List<HistorialEnvio> historial = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                historial = conexionBD.selectList("historialEnvio.consultar-historial-por-guia", noGuia);
                
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return historial;
    }
}