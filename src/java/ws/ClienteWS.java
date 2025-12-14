package ws;

import com.google.gson.Gson;
import dominio.ClienteImp;
import dto.Respuesta;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.EntidadesPrincipales.Cliente;

@Path("cliente")
public class ClienteWS {
    
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
    
}
