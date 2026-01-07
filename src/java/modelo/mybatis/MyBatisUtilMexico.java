package modelo.mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class MyBatisUtilMexico {

    private static SqlSessionFactory factory;
    private static Exception initError;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("modelo/mybatis/mybatis-config-mexico.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            initError = e;
            e.printStackTrace();
        }
    }

    public static SqlSession getSession() {
        if (factory == null) {
            throw new RuntimeException(
                    "SqlSessionFactory no inicializada. Causa: " + (initError != null ? initError.toString() : "desconocida"),
                    initError
            );
        }
        return factory.openSession();
    }
}
