package ws;

import com.google.gson.Gson;
import dominio.PaqueteImp;
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
import pojo.EntidadesPrincipales.Paquete;

@Path("paquete")
public class PaqueteWS {

    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetes() {
        return PaqueteImp.obtenerPaquetes();
    }

    @Path("obtener-por-id/{idPaquete}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Paquete obtenerPaquetePorId(@PathParam("idPaquete") Integer idPaquete) {
        if (idPaquete != null && idPaquete > 0) {
            return PaqueteImp.obtenerPaquetePorId(idPaquete);
        }
        throw new BadRequestException("idPaquete inválido");
    }

    @Path("obtener-por-envio/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetesPorEnvio(@PathParam("idEnvio") Integer idEnvio) {
        if (idEnvio != null && idEnvio > 0) {
            return PaqueteImp.obtenerPaquetesPorEnvio(idEnvio);
        }
        throw new BadRequestException("idEnvio inválido");
    }

    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarPaquete(String json) {
        Gson gson = new Gson();
        Respuesta r = new Respuesta();

        try {
            Paquete paquete = gson.fromJson(json, Paquete.class);

            // Validaciones de entrada (antes de pegarle a BD)
            if (paquete == null) {
                r.setError(true);
                r.setMensaje("JSON inválido.");
                return r;
            }

            if (paquete.getDescripcion() == null || paquete.getDescripcion().trim().isEmpty()) {
                r.setError(true);
                r.setMensaje("La descripción es obligatoria.");
                return r;
            }
            if (paquete.getDescripcion().length() > 200) {
                r.setError(true);
                r.setMensaje("La descripción no debe exceder 200 caracteres.");
                return r;
            }

            if (paquete.getPeso() <= 0) {
                r.setError(true);
                r.setMensaje("El peso debe ser mayor a 0.");
                return r;
            }
            if (paquete.getAlto() <= 0 || paquete.getAncho() <= 0 || paquete.getProfundidad() <= 0) {
                r.setError(true);
                r.setMensaje("Las dimensiones (alto, ancho, profundidad) deben ser mayores a 0.");
                return r;
            }

            if (paquete.getIdEnvio() <= 0) {
                r.setError(true);
                r.setMensaje("idEnvio inválido.");
                return r;
            }

            // Normalización simple
            paquete.setDescripcion(paquete.getDescripcion().trim());

            return PaqueteImp.registrarPaquete(paquete);

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editarPaquete(String json) {
        Gson gson = new Gson();
        Respuesta r = new Respuesta();

        try {
            Paquete paquete = gson.fromJson(json, Paquete.class);

            if (paquete == null || paquete.getIdPaquete() <= 0) {
                r.setError(true);
                r.setMensaje("idPaquete inválido.");
                return r;
            }
            if (paquete.getDescripcion() == null || paquete.getDescripcion().trim().isEmpty() || paquete.getDescripcion().length() > 200) {
                r.setError(true);
                r.setMensaje("Descripción obligatoria (máx. 200).");
                return r;
            }
            if (paquete.getPeso() <= 0 || paquete.getAlto() <= 0 || paquete.getAncho() <= 0 || paquete.getProfundidad() <= 0) {
                r.setError(true);
                r.setMensaje("Peso y dimensiones deben ser mayores a 0.");
                return r;
            }
            if (paquete.getIdEnvio() <= 0) {
                r.setError(true);
                r.setMensaje("idEnvio inválido.");
                return r;
            }

            paquete.setDescripcion(paquete.getDescripcion().trim());
            return PaqueteImp.editarPaquete(paquete);

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Path("eliminar/{idPaquete}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarPaquete(@PathParam("idPaquete") Integer idPaquete) {
        if (idPaquete != null && idPaquete > 0) {
            return PaqueteImp.eliminarPaquete(idPaquete);
        }
        throw new BadRequestException("idPaquete inválido");
    }
}
