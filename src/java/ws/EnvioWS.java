package ws;

import com.google.gson.Gson;
import dominio.EnvioImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.EntidadesPrincipales.Envio;

@Path("envio")
public class EnvioWS {

    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerEnvios() {
        return EnvioImp.obtenerEnvios();
    }

    @Path("obtener-por-id/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Envio obtenerEnvioPorId(@PathParam("idEnvio") Integer idEnvio) {
        if (idEnvio != null && idEnvio > 0) {
            return EnvioImp.obtenerEnvioPorId(idEnvio);
        }
        throw new BadRequestException();
    }

    @Path("obtener-por-noGuia/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Envio obtenerEnvioPorNoGuia(@PathParam("noGuia") String noGuia) {
        if (noGuia != null && !noGuia.isEmpty()) {
            return EnvioImp.obtenerEnvioPorNoGuia(noGuia);
        }
        throw new BadRequestException();
    }

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarEnvio(String json) {
        Gson gson = new Gson();
        try {
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.registrarEnvio(envio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editarEnvio(String json) {
        Gson gson = new Gson();
        try {
            Envio envio = gson.fromJson(json, Envio.class);
            return EnvioImp.editarEnvio(envio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Path("eliminar/{idEnvio}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarEnvio(@PathParam("idEnvio") Integer idEnvio) {
        try {
            if (idEnvio != null && idEnvio > 0) {
                return EnvioImp.eliminarEnvio(idEnvio);
            }
            throw new BadRequestException();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
