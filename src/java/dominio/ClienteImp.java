package dominio;

import dto.Respuesta; 
import java.util.List;
import modelo.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.EntidadesPrincipales.Cliente;
import utilidades.Constantes; 

public class ClienteImp {
    
    public static List<Cliente> obtenerClientes() {
        List<Cliente> clientes = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if (conexionBD != null) {
            try {
                clientes = conexionBD.selectList("cliente.obtener-todos");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return clientes;
    }

    public static Respuesta registrarCliente(Cliente cliente) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();

        if (conexionBD != null) {
            try {
                // [INICIO DE LA CORRECCIÓN DEL BUG]

                // 1. Verificar si ya existe un cliente activo con el mismo correo o teléfono
                Integer existe = conexionBD.selectOne("cliente.verificar-existencia", cliente);
                if (existe != null && existe > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Error: Ya existe un cliente activo con el mismo correo electrónico o número de teléfono. No se puede registrar.");
                    return respuesta; 
                }

                // [FIN DE LA CORRECCIÓN DEL BUG]

                // Lógica de inserción existente (solo se ejecuta si no hay duplicados)
                int filasAfectadas = conexionBD.insert("cliente.registrar", cliente);

                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Cliente: " + cliente.getNombre() + " registrado correctamente.");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, el cliente no fue registrado.");
                }

            } catch (Exception e) {
                conexionBD.rollback();
                e.printStackTrace();
                respuesta.setError(true);
                respuesta.setMensaje("Error en la base de datos al registrar el cliente: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;        
    }
    
    public static Respuesta editarCliente(Cliente cliente) {
        Respuesta respuesta = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
              
                Integer existe = conexionBD.selectOne("cliente.verificar-edicion", cliente);
                if (existe != null && existe > 0) {
                    respuesta.setError(true);
                    respuesta.setMensaje("Ya existe otro cliente activo con el mismo correo o número de teléfono.");
                    return respuesta; 
                }
                
                int filasAfectadas = conexionBD.update("cliente.editar", cliente);
                
                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Cliente: " + cliente.getNombre() + " actualizado correctamente.");
                } else {
                    conexionBD.rollback();
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, el cliente no fue editado o no se encontró el ID.");
                }
            } catch (Exception e) {
                conexionBD.rollback();
                e.printStackTrace();
                respuesta.setError(true);
                respuesta.setMensaje("Error en la base de datos al editar el cliente: " + e.getMessage());
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje(Constantes.MSJ_ERROR_BD);
        }
        return respuesta;
    }
}
