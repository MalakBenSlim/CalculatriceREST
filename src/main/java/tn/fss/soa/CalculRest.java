package tn.fss.soa;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

/**
 * Ressource REST : /api/calcul/{operation}?a=..&b=..
 * Exemple : GET /CalculatriceREST/api/calcul/addition?a=10&b=5
 */
@Path("/calcul")
@Produces(MediaType.APPLICATION_JSON)
public class CalculRest {

    private final Calculatrice calc = new Calculatrice();

    @GET
    @Path("/addition")
    public Operation addition(@QueryParam("a") double a,
                              @QueryParam("b") double b) {
        return new Operation("addition", a, b, calc.addition(a, b));
    }

    @GET
    @Path("/soustraction")
    public Operation soustraction(@QueryParam("a") double a,
                                  @QueryParam("b") double b) {
        return new Operation("soustraction", a, b, calc.soustraction(a, b));
    }

    @GET
    @Path("/multiplication")
    public Operation multiplication(@QueryParam("a") double a,
                                    @QueryParam("b") double b) {
        return new Operation("multiplication", a, b, calc.multiplication(a, b));
    }

    @GET
    @Path("/division")
    public Response division(@QueryParam("a") double a,
                             @QueryParam("b") double b) {
        try {
            Operation op = new Operation("division", a, b, calc.division(a, b));
            return Response.ok(op).build();
        } catch (IllegalArgumentException e) {
            // Division par zéro -> HTTP 400 avec un message JSON
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("erreur", e.getMessage()))
                    .build();
        }
    }
}
