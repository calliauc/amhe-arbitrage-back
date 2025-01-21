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
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCibleById(@PathParam("code") final String code) {
        Cible cible = cibleRepo.getCibleByCode(code);
        if (null == cible) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(cible).build();
    }

    @POST
    @Path("/ruleset")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCibleByRuleset(final List<String> codes) {
        List<Cible> cibles = cibleRepo.getListCibleRuleset(codes);
        if (null == cibles) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(cibles).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCible(final Cible nouveauCible) {
        Cible cibleCree = cibleRepo.createCible(nouveauCible);
        return Response.status(201).entity(cibleCree).build();
    }

    @PUT
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editCible(@PathParam("code") final String code, final Cible cible) {
        Cible cibleCree = cibleRepo.editCible(cible);
        return Response.status(200).entity(cibleCree).build();
    }

    @DELETE
    @Path("/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCible(@PathParam("code") final String code) {
        cibleRepo.deleteCible(code);
        return Response.status(200).build();
    }
}
