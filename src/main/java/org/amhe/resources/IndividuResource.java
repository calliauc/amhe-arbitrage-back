package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.logiques.IndividuLogique;
import org.amhe.mappers.IndividuMapper;
import org.amhe.models.dtos.IndividuDto;
import org.amhe.models.entites.Individu;

import java.util.List;

@Path("/individus")
public class IndividuResource {
    @Inject
    IndividuLogique individuLogique;

    @Inject
    IndividuMapper individuMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIndividus() {
        List<IndividuDto> individus = individuMapper.listeBaseVersDto(individuLogique.getIndividus());
        if (individus.isEmpty()) {
            return Response.status(204).header("Message", "Pas de individu").build();
        }
        return Response.status(200).entity(individus).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIndividuById(@PathParam("id") final Long id) {
        IndividuDto individu = individuMapper.baseVersDto(individuLogique.getIndividuById(id));
        if (null == individu) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(individu).build();
    }

    @POST
    @Path("/recherche/nom")
    @Produces(MediaType.APPLICATION_JSON)
    public Response chercherIndividuByNom(final String nom) {
        List<IndividuDto> individus = individuMapper.listeBaseVersDto(individuLogique.chercherIndividusParNom(nom));
        if (null == individus) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(individus).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIndividu(final Individu nouveauIndividu) {
        Individu individuCree = individuLogique.creerIndividu(nouveauIndividu);
        return Response.status(201).entity(individuCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editIndividu(final Individu individu) {
        Individu individuCree = individuLogique.modifierIndividu(individu);
        return Response.status(200).entity(individuCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteIndividu(@PathParam("id") final Long id) {
        individuLogique.supprimerIndividu(id);
        return Response.status(200).build();
    }

}
