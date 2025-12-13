package ws;

import com.google.gson.Gson;
import dominio.UnidadImp;
import dto.Respuesta;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.EntidadesPrincipales.Unidad;


@Path("unidad")
public class UnidadWS {

    @Path("obtener-todas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidades(){
        return UnidadImp.obtenerUnidades();
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarUnidad(String json){
        Gson gson = new Gson();
        try{
            Unidad unidad = gson.fromJson(json, Unidad.class);
            return UnidadImp.registrar(unidad);
        }catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editarUnidad(String json){
        Gson gson = new Gson();
        try{
            Unidad unidad = gson.fromJson(json, Unidad.class);
            return UnidadImp.editarUnidad(unidad);
        }catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("dar-baja")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta darBajaUnidad(String json){
        Gson gson = new Gson();
        try{
            Unidad unidad = gson.fromJson(json, Unidad.class);
            return UnidadImp.darBajaUnidad(unidad);
        }catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("buscar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Unidad> buscarUnidades(String json){
        Gson gson = new Gson();
        try{
            Unidad filtros = gson.fromJson(json, Unidad.class); 
            return UnidadImp.buscarUnidades(filtros);
        }catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}