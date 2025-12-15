package ws;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dominio.HistorialEnvioImp;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import pojo.EntidadesPrincipales.HistorialEnvio;

@Path("historialEnvio")
public class HistorialEnvioWS {

    @POST
    @Path("registrar")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrar(
            @FormParam("idEnvio") Integer idEnvio,
            @FormParam("idColaborador") Integer idColaborador, 
            @FormParam("idEstatusEnvio") Integer idEstatusEnvio,
            @FormParam("comentario") String comentario) {
        
        if (idEnvio == null || idColaborador == null || idEstatusEnvio == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Faltan datos obligatorios (idEnvio, idColaborador, idEstatusEnvio).")
                           .build();
        }
        
        HistorialEnvio historial = new HistorialEnvio();
        historial.setIdEnvio(idEnvio);
        historial.setIdColaborador(idColaborador);
        historial.setIdEstatusEnvio(idEstatusEnvio);
        historial.setComentario(comentario);

        boolean exito = HistorialEnvioImp.registrarHistorialEnvio(historial);
        
        if (exito) {
            return Response.status(Response.Status.CREATED).entity("{\"success\": true}").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"success\": false, \"message\": \"Error al registrar el cambio de estatus en el historial (fallo de BD).\"}")
                           .build();
        }
    }
    

    @GET
    @Path("consultar/{noGuia}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialEnvio> consultarPorGuia(@PathParam("noGuia") String noGuia) {
        return HistorialEnvioImp.consultarHistorialPorGuia(noGuia);
    }
}