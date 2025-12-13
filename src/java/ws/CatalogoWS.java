package ws;

import dominio.CatalogoImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Catalogo.Rol;

@Path("catalogo")
public class CatalogoWS {
    @Path("obtener-roles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtenerRolesSistema(){
        return CatalogoImp.obtenerRoles();
    }
}
