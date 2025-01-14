package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.models.Cible;
import org.amhe.repos.CibleRepo;

import java.util.List;

@Path("/cibles")
public class CibleResource {
    @Inject
    CibleRepo cibleRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCible() {
        List<Cible> cible = cibleRepo.getCibles();
        if (cible.isEmpty()) {
            return Response.status(204).header("Message", "Pas de cible").build();
        }
        return Response.status(200).entity(cible).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCibleById(@PathParam("id") final Long id) {
        Cible cible = cibleRepo.getCibleById(id);
        if (null == cible) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(cible).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCible(final Cible nouveauCible) {
        Cible cibleCree = cibleRepo.createCible(nouveauCible);
        return Response.status(201).entity(cibleCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCible(final Cible cible) {
        Cible cibleCree = cibleRepo.editCible(cible);
        return Response.status(200).entity(cibleCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCible(@PathParam("id") final Long id) {
        cibleRepo.deleteCible(id);
        return Response.status(200).build();
    }
}
