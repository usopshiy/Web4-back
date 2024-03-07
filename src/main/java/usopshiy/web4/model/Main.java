package usopshiy.web4.model;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;
import usopshiy.web4.database.Points;

@Path("/main")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Main {
    @EJB
    Points points;
    @POST
    @Path("/addPoint")
    public Response addPoint(String json){
        JSONObject object = new JSONObject(json);

        String email = object.optString("email");
        String x = object.optString("x");
        String y = object.optString("y");
        String r = object.optString("r");
        String result = points.addPoint(email, x, y, r);
        if (result.equals("Unauthorized") || result.equals("Bad format")) {
            return Response.ok().entity("false").build();
        } else return Response.ok().entity(result).build();
    }

    @POST
    @Path("/getPoints")
    public Response getPoints(String json){
        JSONObject object = new JSONObject(json);

        String email = object.optString("email");
        return Response.ok().entity(points.getPoints(email)).build();
    }

    @DELETE
    @Path("/clear")
    public Response clearPoints(String json){
        JSONObject object = new JSONObject(json);
        String email = object.optString("email");

        System.out.println(email);
        return  Response.ok().entity(points.clearPoints(email)).build();
    }
}
