package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.logiques.CombattantLogique;
import org.amhe.mappers.PouleMapper;
import org.amhe.models.PouleExpo;
import org.amhe.repos.PouleRepo;

import java.util.List;

@Path("/poules")
public class PouleResource {
    @Inject
    PouleRepo pouleRepo;

    @Inject
    PouleMapper pouleMapper;

    @Inject
    CombattantLogique combattantLogique;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPoules() {
        List<PouleExpo> poules = pouleMapper.listeBaseVersExpo(pouleRepo.getPoules());
        if (poules.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(poules).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPouleById(@PathParam("id") final Long id) {
        PouleExpo poule = pouleMapper.baseVersExpo(pouleRepo.getPouleById(id));
        if (null == poule) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(poule).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPoule(final PouleExpo nouvellePoule) {
        PouleExpo pouleCree = pouleMapper.baseVersExpo(
                pouleRepo.createPoule(
                        pouleMapper.expoVersBase(nouvellePoule)
                )
        );
        return Response.status(201).entity(pouleCree).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPoule(@PathParam("id") final Long id, final PouleExpo poule) {
        PouleExpo pouleCree = pouleMapper.baseVersExpo(pouleRepo.editPoule(pouleMapper.expoVersBase(poule)));
        return Response.status(200).entity(pouleCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePoule(@PathParam("id") final Long id) {
        try {
            pouleRepo.deletePoule(id);
            return Response.status(200).build();
        } catch (Exception e) {
            Exception err = new Exception("Impossible de supprimer le poule.");
            return Response.status(500).entity(err.getMessage()).build();
        }
    }
}
