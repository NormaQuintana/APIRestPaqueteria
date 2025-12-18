package ws;

import com.google.gson.Gson;
import dominio.ColaboradorImp;
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
import pojo.EntidadesPrincipales.Colaborador;

@Path("colaborador")
public class ColaboradorWS {
    
    @Path("obtener-todos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores(){
        return ColaboradorImp.obtenerColaboradores();
    }
    
    @Path("obtener-por-rol/{idRol}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradoresPorRol(@PathParam("idRol") Integer idRol){
        return ColaboradorImp.obtenerColaboradoresPorRol(idRol);
    }
    
    @Path("obtener-por-noPersonal/{noPersonal}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerColaboradoresPorNoPersonal(@PathParam("noPersonal") String noPersonal){
        return ColaboradorImp.obtenerColaboradoresPorNoPersonal(noPersonal);
    }
    
    @Path("obtener-por-nombre/{nombre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradoresPorNombre(@PathParam("nombre") String nombre){
        return ColaboradorImp.obtenerColaboradoresPorNombre(nombre);
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta registrarColaborador(String json){
        Gson gson = new Gson();
        try{
            Colaborador colaborador = gson.fromJson(json, Colaborador.class);
            return ColaboradorImp.registrarColaborador(colaborador);
        }catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Respuesta editarColaborador(String json){
        Gson gson = new Gson();
        try{
            Colaborador colaborador = gson.fromJson(json, Colaborador.class);
            return ColaboradorImp.editar(colaborador);
        }catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("eliminar/{idColaborador}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarColaborador(@PathParam("idColaborador") Integer idColaborador){
        try{
            return ColaboradorImp.eliminarColaborador(idColaborador);
        }catch(Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
    
    @Path("subir-foto/{idColaborador}")
    @PUT
    @Produces (MediaType.APPLICATION_JSON)
     public Respuesta subirFoto(@PathParam ("idColaborador") Integer idColaborador, byte[] foto){
        if(idColaborador != null && idColaborador > 0 && foto.length > 0){
            return ColaboradorImp.registrarFoto(idColaborador, foto);
        }
        throw new BadRequestException();
    }
     
    @Path("obtener-foto/{idColaborador}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerFoto(@PathParam("idColaborador") Integer idColaborador){
        if(idColaborador != null && idColaborador > 0){
            return ColaboradorImp.obtenerFoto(idColaborador);
        }
        throw new BadRequestException(); 
         
    }
}
