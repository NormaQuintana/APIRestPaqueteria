package ws;

import dominio.ColaboradorImp;
import java.util.List;
import javax.ws.rs.GET;
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
    
    @Path("obtener-por-noPerosnal/{noPersonal}")
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
}
