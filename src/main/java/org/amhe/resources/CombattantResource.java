package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.logiques.CombattantLogique;
import org.amhe.models.Combattant;
import org.amhe.repos.CombattantRepo;

import java.util.List;
import java.util.Set;

@Path("/combattants")
public class CombattantResource {
    @Inject
    CombattantRepo combattantRepo;

    @Inject
    CombattantLogique combattantLogique;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCombattants() {
        List<Combattant> combattants = combattantRepo.getCombattants();
        if (combattants.isEmpty()) {
            return Response.status(204).header("Message", "Pas de combattant").build();
        }
        return Response.status(200).entity(combattants).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCombattantById(@PathParam("id") final Long id) {
        Combattant combattant = combattantRepo.getCombattantById(id);
        if (null == combattant) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(combattant).build();
    }

    @POST
    @Path("/tags")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCombattantsByMatchtags(final List<Long> tags) {
        Set<Combattant> combattants = combattantLogique.getCombattantsByMatchTags(tags);
        if (combattants.isEmpty()) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(combattants).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCombattant(final Combattant nouveauCombattant) {
        Combattant combattantCree = combattantRepo.createCombattant(nouveauCombattant);
        return Response.status(201).entity(combattantCree).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCombattant(@PathParam("id") final Long id, final Combattant combattant) {
        Combattant combattantCree = combattantRepo.editCombattant(combattant);
        return Response.status(200).entity(combattantCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCombattant(@PathParam("id") final Long id) {
        combattantRepo.deleteCombattant(id);
        return Response.status(200).build();
    }
}
