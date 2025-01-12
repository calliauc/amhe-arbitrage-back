package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.models.Coup;
import org.amhe.repos.CoupRepo;

import java.util.List;

@Path("/coups")
public class CoupResource {
    @Inject
    CoupRepo coupRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoups() {
        List<Coup> coups = coupRepo.getCoups();
        if (coups.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(coups).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoupById(@PathParam("id") final Long id) {
        Coup coup = coupRepo.getCoupById(id);
        if (null == coup) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(coup).build();
    }

    @GET
    @Path("/match/{match_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCoupByMatchId(@PathParam("match_id") final Long match_id) {
        List<Coup> coup = coupRepo.getCoupsByMatchId(match_id);
        if (null == coup) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(coup).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCoup(final Coup nouveauCoup) {
        Coup coupCree = coupRepo.createCoup(nouveauCoup);
        return Response.status(201).entity(coupCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCoup(final Coup coup) {
        Coup coupCree = coupRepo.editCoup(coup);
        return Response.status(200).entity(coupCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCoup(@PathParam("id") final Long id) {
        try {
            coupRepo.deleteCoup(id);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
