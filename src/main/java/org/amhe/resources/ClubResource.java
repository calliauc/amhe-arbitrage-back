package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.models.Club;
import org.amhe.repos.ClubRepo;

import java.util.List;

@Path("/clubs")
public class ClubResource {
    @Inject
    ClubRepo clubRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClubs() {
        List<Club> clubs = clubRepo.getClubs();
        if (clubs.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(clubs).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClubById(@PathParam("id") final Long id) {
        Club club = clubRepo.getClubById(id);
        if (null == club) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(club).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClub(final Club nouveauClub) {
        Club clubCree = clubRepo.createClub(nouveauClub);
        return Response.status(201).entity(clubCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editClub(final Club club) {
        Club clubCree = clubRepo.editClub(club);
        return Response.status(200).entity(clubCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteClub(@PathParam("id") final Long id) {
        try {
            clubRepo.deleteClub(id);
            return Response.status(200).build();
        } catch (Exception e) {
            Exception err = new Exception("Impossible de supprimer le club.");
            return Response.status(500).entity(err.getMessage()).build();
        }
    }
}
