package ws;

import com.google.gson.Gson;
import dominio.GeografiaImp;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("geografia")
public class GeografiaWS {

    private final Gson gson = new Gson();

    @GET
    @Path("colonias-por-cp/{cp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response coloniasPorCp(@PathParam("cp") int cp) {
        try {
            List<HashMap<String, Object>> lista = GeografiaImp.obtenerColoniasPorCP(cp);
            String json = gson.toJson(lista);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity("{\"error\":\"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
