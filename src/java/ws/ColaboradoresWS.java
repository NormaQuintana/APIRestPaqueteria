package ws;

import dominio.ColaboradorImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.EntidadesPrincipales.Colaborador;

@Path("colaborador")
public class ColaboradoresWS {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores(){
        return ColaboradorImp.obtenerColaboradores();
    }
}
