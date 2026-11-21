package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.amhe.logiques.MatchLogique;
import org.amhe.mappers.InscritMapper;
import org.amhe.mappers.MatchMapper;
import org.amhe.models.dtos.CreationMatchDto;
import org.amhe.models.dtos.MatchDto;
import org.amhe.models.dtos.MatchPartialUpdateDto;
import org.amhe.models.trier.CriteresRechercheMatchs;

import java.util.List;

@Slf4j
@Path("/matchs")
public class MatchResource {
    @Inject
    MatchLogique logique;

    @Inject
    MatchMapper mapper;

    @Inject
    InscritMapper inscritMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchs() {
        List<MatchDto> matchs = mapper.listeBaseVersDto(logique.getMatchs());
        if (matchs.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(matchs).header("nombre-matchs", matchs.size()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchById(@PathParam("id") final Long id) {
        MatchDto match = mapper.baseVersDto(logique.getMatchById(id));
        if (null == match) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(match).build();
    }

    @POST
    @Path("/recherche")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rechercheMatchsParCriteres(final CriteresRechercheMatchs criteres) {
        List<MatchDto> matchs = mapper.listeBaseVersDto(logique.rechercheMatchsParCriteres(criteres));
        if (matchs.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(matchs).header("nombre-matchs", matchs.size()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMatch(final CreationMatchDto nouveauMatch) {
        MatchDto matchCree = mapper.baseVersDto(logique.creerMatch(mapper.dtoCreationVersBase(nouveauMatch)));
        return Response.status(201).entity(matchCree).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editMatch(@PathParam("id") final Long id, final MatchDto match) {
        MatchDto matchModifie = mapper.baseVersDto(logique.modifierMatch(id, mapper.dtoVersBase(match)));
        return Response.status(200).entity(matchModifie).build();

    }

    @PUT
    @Path("/partial/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePartialMatch(@BeanParam MatchPartialUpdateDto data) {
        logique.updatePartialMatch(data);
        return Response.status(200).build();
    }

    @POST
    @Path("/poule/{id}/generer-matchs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response genererMatchsPoule(@PathParam("id") final Long idPoule) {
        List<MatchDto> matchs = mapper.listeBaseVersDto(logique.genererMatchsPoule(idPoule));
        if (matchs.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(matchs).header("nombre-matchs", matchs.size()).build();
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMatch(@PathParam("id") final Long id) {
        try {
            logique.supprimerMatch(id);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
