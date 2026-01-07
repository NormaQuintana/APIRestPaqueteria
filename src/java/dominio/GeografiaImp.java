package dominio;

import java.util.HashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtilMexico;
import org.apache.ibatis.session.SqlSession;

public class GeografiaImp {

    public static List<HashMap<String, Object>> obtenerColoniasPorCP(int codigoPostal) {
        SqlSession conn = MyBatisUtilMexico.getSession();
        if (conn == null) {
            throw new RuntimeException("No se pudo abrir conexión MyBatis (SqlSession null).");
        }
        try {
            return conn.selectList("geografia.coloniasPorCP", codigoPostal);
        } finally {
            conn.close();
        }
    }

}
