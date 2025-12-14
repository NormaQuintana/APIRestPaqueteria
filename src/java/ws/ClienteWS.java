package ws;

import com.google.gson.Gson;
import dominio.ClienteImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.EntidadesPrincipales.Cliente;

@Path("cliente")
public class ClienteWS {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerClientesWS() {
        return ClienteImp.obtenerClientes();
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarCliente(String json) {
        Gson gson = new Gson();
        try {
            Cliente cliente = gson.fromJson(json, Cliente.class);
            return ClienteImp.registrarCliente(cliente);
        } catch (Exception e) {
            throw new BadRequestException("Error al procesar la solicitud: " + e.getMessage());
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editarClienteWS(String json){
        Gson gson = new Gson();
        try{
            Cliente cliente = gson.fromJson(json, Cliente.class);
            return ClienteImp.editarCliente(cliente);
        }catch(Exception e){
            throw new BadRequestException("Error al procesar la solicitud de edición: " + e.getMessage());
        }
    }
    
}
