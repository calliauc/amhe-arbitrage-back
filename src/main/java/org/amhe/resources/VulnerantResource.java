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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVulnerantById(@PathParam("id") final Long id) {
        Vulnerant vulnerant = vulnerantRepo.getVulnerantById(id);
        if (null == vulnerant) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(vulnerant).build();
    }

    @GET
    @Path("/ruleset")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVulnerantsByRuleset(final List<Long> ids) {
        List<Vulnerant> vulnerants = vulnerantRepo.getListVulnerantsRuleset(ids);
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editVulnerant(final Vulnerant vulnerant) {
        Vulnerant vulnerantCree = vulnerantRepo.editVulnerant(vulnerant);
        return Response.status(200).entity(vulnerantCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteVulnerant(@PathParam("id") final Long id) {
        vulnerantRepo.deleteVulnerant(id);
        return Response.status(200).build();
    }
}
