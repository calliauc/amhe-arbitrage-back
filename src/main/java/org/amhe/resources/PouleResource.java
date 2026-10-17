package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.logiques.PouleLogique;
import org.amhe.mappers.PouleMapper;
import org.amhe.models.dtos.PouleDto;

import java.util.List;
import java.util.Objects;

@Path("/poules")
public class PouleResource {
    @Inject
    PouleLogique pouleLogique;

    @Inject
    PouleMapper pouleMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPoules() {
        List<PouleDto> poules = pouleMapper.listeBaseVersDto(pouleLogique.getPoules());
        if (poules.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(poules).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPouleById(@PathParam("id") final Long id) {
        PouleDto poule = pouleMapper.baseVersDto(pouleLogique.getPouleById(id));
        if (null == poule) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(poule).build();
    }

    @GET
    @Path("/event/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPouleByEvent(@PathParam("id") final Long id) {
        List<PouleDto> poules = pouleMapper.listeBaseVersDto(pouleLogique.getPouleByEvent(id));
        if (Objects.isNull(poules) || poules.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(poules).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPoule(final PouleDto nouvellePoule) {
        PouleDto pouleCree = pouleMapper.baseVersDto(pouleLogique.creerPoule(pouleMapper.dtoVersBase(nouvellePoule)));
        return Response.status(201).entity(pouleCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPoule(final PouleDto poule) {
        PouleDto pouleCree = pouleMapper.baseVersDto(pouleLogique.modifierPoule(pouleMapper.dtoVersBase(poule)));
        return Response.status(200).entity(pouleCree).build();
    }

    @PUT
    @Path("/{id}/inscrire")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inscrireALaPoule(@PathParam("id") final Long id, final List<Long> inscrits) {
        PouleDto poule = pouleMapper.baseVersDto(pouleLogique.inscrireALaPoule(id, inscrits));
        return Response.status(200).entity(poule).build();
    }

    @PATCH
    @Path("/update-inscrits")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInscritsPoules(final List<PouleDto> p) {
        List<PouleDto> poules = pouleMapper.listeBaseVersDto(
                pouleLogique.updateInscritsPoules(pouleMapper.listeDtoVersBase(p)));
        return Response.status(200).entity(poules).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePoule(@PathParam("id") final Long id) {
        try {
            pouleLogique.supprimerPoule(id);
            return Response.status(200).build();
        } catch (Exception e) {
            Exception err = new Exception("Impossible de supprimer le poule.");
            return Response.status(500).entity(err.getMessage()).build();
        }
    }
}
