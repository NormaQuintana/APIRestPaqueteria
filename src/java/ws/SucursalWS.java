package ws;

import dominio.SucursalImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.EntidadesPrincipales.Sucursal;

@Path("sucursal")
public class SucursalWS {
    @Path("obtener-todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> obtenerSucursales(){
        return SucursalImp.obtenerSucursales();
    }
}
