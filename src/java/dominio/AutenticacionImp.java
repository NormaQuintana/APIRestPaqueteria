package dominio;

import dto.RSAutenticacion;
import java.util.HashMap;
import java.util.LinkedHashMap;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Colaborador;

public class AutenticacionImp {
    public static RSAutenticacion autenticarColaborador(String noPersonal, String password){
        RSAutenticacion autenticacion = new RSAutenticacion();
        autenticacion.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("password", password);
                Colaborador colaborador = conexionBD.selectOne("autenticacion.colaborador", parametros);
                if(colaborador != null){
                    autenticacion.setError(false);
                    autenticacion.setMensaje("Credenciales correctas del usuario: "+colaborador.getNombre());
                    autenticacion.setColaborador(colaborador);
                }else{
                    autenticacion.setMensaje("Credenciales incorrectas, por favor verifique la informacion");
                }
            }catch(Exception e){
                autenticacion.setMensaje(e.getMessage());
            }
            conexionBD.close();
        }else{
            autenticacion.setMensaje("Lo sentimos por el momento no hay conexion a los datos");
        }
        return autenticacion;
    }
}
