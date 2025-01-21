package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.models.Vulnerant;
import org.amhe.repos.VulnerantRepo;

import java.util.List;

@Path("/vulnerants")
public class VulnerantResource {
    @Inject
    VulnerantRepo vulnerantRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVulnerant() {
        List<Vulnerant> vulnerant = vulnerantRepo.getVulnerants();
        if (vulnerant.isEmpty()) {
            return Response.status(204).header("Message", "Pas de vulnerant").build();
        }
        return Response.status(200).entity(vulnerant).build();
    }

    @GET
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVulnerantById(@PathParam("code") final String code) {
        Vulnerant vulnerant = vulnerantRepo.getVulnerantByCode(code);
        if (null == vulnerant) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(vulnerant).build();
    }

    @POST
    @Path("/ruleset")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVulnerantsByRuleset(final List<String> codes) {
        List<Vulnerant> vulnerants = vulnerantRepo.getListVulnerantsRuleset(codes);
        if (null == vulnerants) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(vulnerants).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createVulnerant(final Vulnerant nouveauVulnerant) {
        Vulnerant vulnerantCree = vulnerantRepo.createVulnerant(nouveauVulnerant);
        return Response.status(201).entity(vulnerantCree).build();
    }

    @PUT
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editVulnerant(@PathParam("code") final String code, final Vulnerant vulnerant) {
        Vulnerant vulnerantCree = vulnerantRepo.editVulnerant(vulnerant);
        return Response.status(200).entity(vulnerantCree).build();
    }

    @DELETE
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteVulnerant(@PathParam("code") final String code) {
        vulnerantRepo.deleteVulnerant(code);
        return Response.status(200).build();
    }
}
