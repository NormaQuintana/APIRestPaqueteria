package dominio;

import java.util.HashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtilMexico;
import org.apache.ibatis.session.SqlSession;

public class GeografiaImp {

    public static List<HashMap<String, Object>> obtenerColoniasPorCP(int codigoPostal) {
        SqlSession conn = MyBatisUtilMexico.getSession();
        try {
            return conn.selectList("geografia.coloniasPorCP", codigoPostal);
        } catch (Exception e) {
            e.printStackTrace(); 
            throw e;
        } finally {
            conn.close();
        }
    }

}
