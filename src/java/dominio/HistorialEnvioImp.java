package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.HistorialEnvio;

public class HistorialEnvioImp {

    public static List<HistorialEnvio> consultarPorGuia(String noGuia) {
        SqlSession session = null;
        try {
            session = MyBatisUtil.getSession();
            return session.selectList("historialEnvio.consultar-por-guia", noGuia);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
