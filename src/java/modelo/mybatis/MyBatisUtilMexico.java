package modelo.mybatis;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtilMexico {

    private static final String RESOURCE = "modelo/mybatis/mybatis-config-mexico.xml";

    private static final String ENVIROMENT = "geo_lectura";
    private static SqlSessionFactory sqlMapper;

    private static SqlSessionFactory getSqlSessionFactory() throws IOException {
        if (sqlMapper == null) {
            Reader reader = Resources.getResourceAsReader(RESOURCE);
            sqlMapper = new SqlSessionFactoryBuilder().build(reader, ENVIROMENT);
        }
        return sqlMapper;
    }

    public static SqlSession getSession() {
        SqlSession session = null;
        try {
            session = getSqlSessionFactory().openSession(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return session;
    }

}
