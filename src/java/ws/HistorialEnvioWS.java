package ws;

import dominio.HistorialEnvioImp;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.EntidadesPrincipales.HistorialEnvio;

@Path("historialEnvio")
public class HistorialEnvioWS {

    @Path("consultar/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialEnvio> consultar(@PathParam("noGuia") String noGuia) {
        return HistorialEnvioImp.consultarPorGuia(noGuia);
    }
}
