package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.logiques.MatchLogique;
import org.amhe.mappers.MatchMapper;
import org.amhe.models.MatchExpo;
import org.amhe.repos.MatchRepo;

import java.util.List;

@Path("/matchs")
public class MatchResource {
    @Inject
    MatchRepo matchRepo;
    @Inject
    MatchLogique matchLogique;
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
