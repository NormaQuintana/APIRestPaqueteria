package ws;

import dominio.AutenticacionImp;
import dto.RSAutenticacion;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("autenticacion")
public class AutenticacionWS {
    
    @Path("colaborador")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RSAutenticacion autenticarColaborador(
            @FormParam("noPersonal") String noPersonal,
            @FormParam("password") String password){
        if(noPersonal != null && !noPersonal.isEmpty() 
                && (password != null && !password.isEmpty())){
            return AutenticacionImp.autenticarColaborador(noPersonal, password);
        }
        throw new BadRequestException();
    }
    
    @Path("conductor")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RSAutenticacion autenticarConductor(
            @FormParam("noPersonal") String noPersonal,
            @FormParam("password") String password){
        if(noPersonal != null && !noPersonal.isEmpty() 
                && (password != null && !password.isEmpty())){
            return AutenticacionImp.autenticarConductor(noPersonal, password);
        }
        throw new BadRequestException();
    }
}
