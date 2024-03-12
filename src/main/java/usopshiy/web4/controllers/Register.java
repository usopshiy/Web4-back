package usopshiy.web4.controllers;

import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;
import usopshiy.web4.database.Auth;

@Path("/reg")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Register {
    @EJB
    Auth auth;
    @POST
    public Response regUser(String json){
        JSONObject object = new JSONObject(json);
        String login = object.optString("login");
        String email = object.optString("email");
        String password = object.optString("password");
        return Response.ok().entity(auth.register(login, email, password)).build();
    }
}
