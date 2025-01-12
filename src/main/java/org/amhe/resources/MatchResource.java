package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.models.Match;
import org.amhe.repos.MatchRepo;

import java.util.List;

@Path("/matchs")
public class MatchResource {
    @Inject
    MatchRepo matchRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchs() {
        List<Match> matchs = matchRepo.getMatchs();
        if (matchs.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(matchs).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMatchById(@PathParam("id") final Long id) {
        Match match = matchRepo.getMatchById(id);
        if (null == match) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(match).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createMatch(final Match nouveauMatch) {
        Match matchCree = matchRepo.createMatch(nouveauMatch);
        return Response.status(201).entity(matchCree).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editMatch(@PathParam("id") final Long id, final Match match) {
        Match matchCree = matchRepo.editMatch(id, match);
        return Response.status(200).entity(matchCree).build();
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
