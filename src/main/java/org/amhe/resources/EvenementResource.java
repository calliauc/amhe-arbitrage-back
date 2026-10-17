package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.logiques.EvenementLogique;
import org.amhe.mappers.EvenementMapper;
import org.amhe.models.dtos.EvenementDto;
import org.amhe.models.entites.Evenement;

import java.util.List;

@Path("/evenements")
public class EvenementResource {
    @Inject
    EvenementLogique evenementLogique;

    @Inject
    EvenementMapper evenementMapper;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvenements() {
        List<EvenementDto> evenements = evenementMapper.listeBaseVersDto(evenementLogique.getEvenements());
        if (evenements.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(evenements).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvenementById(@PathParam("id") final Long id) {
        EvenementDto evenement = evenementMapper.baseVersDto(evenementLogique.getEvenementById(id));
        if (null == evenement) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(evenement).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvenement(final Evenement nouveauEvenement) {
        EvenementDto evenementCree = evenementMapper.baseVersDto(evenementLogique.creerEvenement(nouveauEvenement));
        return Response.status(201).entity(evenementCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editEvenement(final Evenement evenement) {
        EvenementDto evenementCree = evenementMapper.baseVersDto(evenementLogique.modifierEvenement(evenement));
        return Response.status(200).entity(evenementCree).build();
    }

    @PUT
    @Path("/{id}/inscrire")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inscrireAEvenement(@PathParam("id") final Long id, final List<Long> individus) {
        EvenementDto evenementCree = evenementMapper.baseVersDto(evenementLogique.inscrireAEvenement(id, individus));
        return Response.status(200).entity(evenementCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEvenement(@PathParam("id") final Long id) {
        try {
            evenementLogique.supprimerEvenement(id);
            return Response.status(200).build();
        } catch (Exception e) {
            Exception err = new Exception("Impossible de supprimer le evenement.");
            return Response.status(500).entity(err.getMessage()).build();
        }
    }
}
