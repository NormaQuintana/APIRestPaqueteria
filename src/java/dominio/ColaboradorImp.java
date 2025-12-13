package dominio;

import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Colaborador;

public class ColaboradorImp {
    public static List<Colaborador> obtenerColaboradores(){
        List<Colaborador> colaboradores = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                colaboradores = conexionBD.selectList("colaborador.obtener-todos");
                conexionBD.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
}
