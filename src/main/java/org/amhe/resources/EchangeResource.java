package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.amhe.logiques.EchangeLogique;
import org.amhe.mappers.EchangeMapper;
import org.amhe.models.dtos.EchangeDto;
import org.amhe.models.dtos.EchangeNouveauDto;
import org.amhe.repos.EchangeRepo;

import java.util.List;

@Slf4j
@Path("/echanges")
public class EchangeResource {
    @Inject
    EchangeMapper echangeMapper;

    @Inject
    EchangeLogique echangeLogique;

    @Inject
    EchangeRepo echangeRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEchanges() {
        List<EchangeDto> echanges = echangeMapper.listeBaseVersDto(echangeRepo.getEchanges());
        if (echanges.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(echanges).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEchangeById(@PathParam("id") final Long id) {
        EchangeDto echange = echangeMapper.baseVersDto(echangeRepo.getEchangeById(id));
        if (null == echange) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(echange).build();
    }

    @GET
    @Path("/match/{match_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEchangeByMatchId(@PathParam("match_id") final Long match_id) {
        List<EchangeDto> echanges = echangeMapper.listeBaseVersDto(echangeRepo.getEchangesByMatchId(match_id));
        if (null == echanges) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(echanges).build();
    }

    @GET
    @Path("/inscrit/{inscrit_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEchangeByAttaquantId(@PathParam("inscrit_id") final Long inscritId) {
        List<EchangeDto> echanges = echangeMapper.listeBaseVersDto(echangeRepo.getEchangesByInscritId(inscritId));
        if (null == echanges) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(echanges).build();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEchange(final EchangeNouveauDto nouveauEchange) {
        EchangeDto echangeCree = echangeMapper.baseVersDto(echangeLogique.creerEchange(nouveauEchange));
        return Response.status(201).entity(echangeCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEchange(@PathParam("id") final Long id) {
        try {
            echangeRepo.supprimerEchange(id);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
