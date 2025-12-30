package modelo.mybatis;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class MyBatisUtilMexico {

    private static SqlSessionFactory factory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("modelo/mybatis/mybatis-config-mexico.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSession() {
        return factory.openSession();
    }
}
