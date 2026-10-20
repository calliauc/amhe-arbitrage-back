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
import org.amhe.models.trier.CriteresRechercheMatchs;

import java.util.List;

@Slf4j
@Path("/matchs")
public class MatchResource {
    @Inject
    MatchLogique matchLogique;

    @Inject
    MatchMapper matchMapper;

    @Inject
    InscritMapper inscritMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchs() {
        List<MatchDto> matchs = matchMapper.listeBaseVersDto(matchLogique.getMatchs());
        if (matchs.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(matchs).header("nombre-matchs", matchs.size()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchById(@PathParam("id") final Long id) {
        MatchDto match = matchMapper.baseVersDto(matchLogique.getMatchById(id));
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
        List<MatchDto> matchs = matchMapper.listeBaseVersDto(matchLogique.rechercheMatchsParCriteres(criteres));
        if (matchs.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(matchs).header("nombre-matchs", matchs.size()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMatch(final CreationMatchDto nouveauMatch) {
        MatchDto matchCree = matchMapper.baseVersDto(matchLogique.creerMatch(matchMapper.dtoCreationVersBase(nouveauMatch)));
        return Response.status(201).entity(matchCree).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editMatch(@PathParam("id") final Long id, final MatchDto match) {
        MatchDto matchModifie = matchMapper.baseVersDto(matchLogique.modifierMatch(id, matchMapper.dtoVersBase(match)));
        return Response.status(200).entity(matchModifie).build();

    }

    @PUT
    @Path("/partial/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePartialMatch(
            @PathParam("id") final Long id,
            @QueryParam("scoreA") final Integer scoreA,
            @QueryParam("scoreB") final Integer scoreB,
            @QueryParam("timer") final Integer timer,
            @QueryParam("dateCreation") final String dateCreation,
            @QueryParam("dateDebut") final String dateDebut,
            @QueryParam("dateFin") final String dateFin,
            @QueryParam("statut") final String statut
    ) {
        return Response.status(200).build();
    }

    @POST
    @Path("/poule/{id}/generer-matchs")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response genererMatchsPoule(@PathParam("id") final Long idPoule) {
        List<MatchDto> matchs = matchMapper.listeBaseVersDto(matchLogique.genererMatchsPoule(idPoule));
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
            matchLogique.supprimerMatch(id);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
