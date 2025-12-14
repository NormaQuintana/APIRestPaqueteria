package ws;

import dominio.EstatusEnvioImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Catalogo.EstatusEnvio;


@Path("catalogo/estatusEnvio")
public class EstatusEnvioWS {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EstatusEnvio> obtenerEstatusEnvio(){
        return EstatusEnvioImp.obtenerEstatusEnvio();
    }
}