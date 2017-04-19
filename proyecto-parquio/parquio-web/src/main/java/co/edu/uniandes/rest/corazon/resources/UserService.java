/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.corazon.resources;

import co.edu.uniandes.csw.isis2503.security.jwt.api.JsonWebToken;
import co.edu.uniandes.csw.isis2503.security.jwt.api.JwtHashAlgorithm;
import co.edu.uniandes.csw.isis2503.security.logic.dto.UserDTO;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author jhonatan
 */
@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    
    @Path("/login")
    @POST
    public Response login(UserDTO userStorm) {
        String token = new Gson().toJson(JsonWebToken.encode(userStorm, "Un14nd3s2014@", JwtHashAlgorithm.HS256));
        return Response.status(200).entity(token).build();
    }
    
}
