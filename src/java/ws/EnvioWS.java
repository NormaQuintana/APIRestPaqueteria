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
    
    @Path("obtener-todos/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerEnviosPorColaborador(@PathParam("idColaborador") Integer idColaborador) {
        return EnvioImp.obtenerEnviosPorConductor(idColaborador);
    }

    @Path("obtener-por-id/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Envio obtenerEnvioPorId(@PathParam("idEnvio") Integer idEnvio) {
        try {
            return EnvioImp.obtenerPorId(idEnvio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Path("obtener-por-guia/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Envio obtenerEnvioPorGuia(@PathParam("noGuia") String noGuia) {
        try {
            return EnvioImp.obtenerPorGuia(noGuia);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
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
            return EnvioImp.actualizarEnvio(envio);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Path("actualizar-estatus")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta actualizarEstatus(String json) {
        Gson gson = new Gson();
        try {
            java.util.Map<String, Object> datos = gson.fromJson(json, java.util.Map.class);

            int idEnvio = ((Double) datos.get("idEnvio")).intValue();
            int idEstatusEnvio = ((Double) datos.get("idEstatusEnvio")).intValue();
            int idColaborador = ((Double) datos.get("idColaborador")).intValue();
            String comentario = (String) datos.get("comentario");

            return EnvioImp.actualizarEstatus(idEnvio, idEstatusEnvio, idColaborador, comentario);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}