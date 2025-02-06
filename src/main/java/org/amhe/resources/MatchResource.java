package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.amhe.mappers.MatchMapper;
import org.amhe.models.Match;
import org.amhe.models.MatchExpo;
import org.amhe.repos.MatchRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Slf4j
@Path("/matchs")
public class MatchResource {
    @Inject
    MatchRepo matchRepo;

    @Inject
    MatchMapper matchMapper;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchs() {
        List<MatchExpo> matchs = matchMapper.listeBaseVersExpo(matchRepo.getMatchs());
        if (matchs.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(matchs).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchById(@PathParam("id") final Long id) {
        MatchExpo match = matchMapper.baseVersExpo(matchRepo.getMatchById(id));
        if (null == match) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(match).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMatch(final MatchExpo nouveauMatch) {
        nouveauMatch.setStatut("nouveau");
        MatchExpo matchCree = matchMapper.baseVersExpo(matchRepo.createMatch(matchMapper.expoVersBase(nouveauMatch)));
        return Response.status(201).entity(matchCree).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editMatch(@PathParam("id") final Long id, final MatchExpo match) {
        MatchExpo matchModifie = matchMapper.baseVersExpo(matchRepo.editMatch(id, matchMapper.expoVersBase(match)));
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
        Match match = matchRepo.getMatchById(id);
        if (Objects.nonNull(scoreA))
            match.setScoreA(scoreA);
        if (Objects.nonNull(scoreB))
            match.setScoreB(scoreB);
        if (Objects.nonNull(timer))
            match.setTimer(timer);
        if (Objects.nonNull(statut))
            match.setStatut(statut);
        if (Objects.nonNull(dateCreation))
            match.setDateCreation(LocalDateTime.parse(dateCreation, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        if (Objects.nonNull(dateDebut) && Objects.isNull(match.getDateDebut()))
            match.setDateDebut(LocalDateTime.parse(dateDebut, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        if (Objects.nonNull(dateFin))
            match.setDateFin(LocalDateTime.parse(dateFin, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        matchRepo.editMatch(id, match);
        return Response.status(200).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteMatch(@PathParam("id") final Long id) {
        try {
            matchRepo.deleteMatch(id);
            return Response.status(200).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
