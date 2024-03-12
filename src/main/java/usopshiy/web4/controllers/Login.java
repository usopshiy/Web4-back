package usopshiy.web4.controllers;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import usopshiy.web4.database.Auth;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class Login {
    @EJB
    Auth auth;
    @POST
    public Response checkAuth(String json){
        JSONObject jsonObject = new JSONObject(json);
        String email = jsonObject.optString("email");
        String password = jsonObject.optString("password");
        String resp = auth.login(email, password);
        System.out.println(json + "\n" + email + password);
        System.out.println(resp);
        System.out.println(Response.ok(auth.login(email, password)).toString());
        return Response.status(Response.Status.OK).entity(auth.login(email, password)).build();
    }
}
