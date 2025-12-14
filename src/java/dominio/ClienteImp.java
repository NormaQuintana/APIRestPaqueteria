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
                if (cliente.getNombre() == null || cliente.getNombre().isEmpty() ||
                    cliente.getApellidoPaterno() == null || cliente.getApellidoPaterno().isEmpty() ||
                    cliente.getCorreo() == null || cliente.getCorreo().isEmpty() ||
                    cliente.getTelefono() == null || cliente.getTelefono().isEmpty() ||
                    cliente.getIdColonia() == null) {
                    
                    respuesta.setError(true);
                    respuesta.setMensaje("Faltan campos obligatorios para el registro del cliente.");
                    return respuesta; 
                }

                int filasAfectadas = conexionBD.insert("cliente.registrar", cliente);
                
                if (filasAfectadas > 0) {
                    conexionBD.commit();
                    respuesta.setError(false);
                    respuesta.setMensaje("Cliente " + cliente.getNombre() + " registrado correctamente.");
                } else {
                    conexionBD.rollback(); 
                    respuesta.setError(true);
                    respuesta.setMensaje("Lo sentimos, el cliente no fue agregado. Verifique la información.");
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
