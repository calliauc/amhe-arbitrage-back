package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.logiques.InscritLogique;
import org.amhe.mappers.InscritMapper;
import org.amhe.models.dtos.InscritDto;
import org.amhe.models.entites.Inscrit;

import java.util.List;

@Path("/inscrits")
public class InscritResource {
    @Inject
    InscritLogique inscritLogique;

    @Inject
    InscritMapper inscritMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInscrits() {
        List<InscritDto> inscrits = inscritMapper.listeBaseVersDto(inscritLogique.getInscrits());
        if (inscrits.isEmpty()) {
            return Response.status(204).header("Message", "Pas de inscrit").build();
        }
        return Response.status(200).entity(inscrits).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInscritById(@PathParam("id") final Long id) {
        InscritDto inscrit = inscritMapper.baseVersDto(inscritLogique.getInscritById(id));
        if (null == inscrit) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(inscrit).build();
    }

    @GET
    @Path("/event/{idEvent}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInscritByEvent(@PathParam("idEvent") final Long idEvent) {
        List<InscritDto> inscrit = inscritMapper.listeBaseVersDto(inscritLogique.getInscritsByEvent(idEvent));
        if (null == inscrit) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(inscrit).build();
    }

    @POST
    @Path("/recherche/nom")
    @Produces(MediaType.APPLICATION_JSON)
    public Response chercherInscritByNom(final String nom) {
        List<InscritDto> inscrits = inscritMapper.listeBaseVersDto(inscritLogique.chercherInscritsParNom(nom));
        if (null == inscrits) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(inscrits).build();
    }

//    @POST
//    @Path("/tags")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getInscritsByMatchTags(final List<Long> tags) {
//        Set<Inscrit> inscrits = inscritLogique.getInscritsByMatchTags(tags);
//        if (inscrits.isEmpty()) {
//            return Response.status(204).tag("tag").build();
//        }
//        return Response.status(200).entity(inscrits).build();
//    }

//    @POST
//    @Path("/details/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response getInscritsByMatchTags(@PathParam("id") final Long id, final TagsFiltres tagsFiltres) {
//        InscritDetails details = inscritLogique.getDetailsInscrit(id, tagsFiltres);
//        if (Objects.isNull(details)) {
//            return Response.status(204).build();
//        }
//        return Response.status(200).entity(details).build();
//    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createInscrit(final Inscrit nouveauInscrit) {
        Inscrit inscritCree = inscritLogique.creerInscrit(nouveauInscrit);
        return Response.status(201).entity(inscritCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editInscrit(final Inscrit inscrit) {
        Inscrit inscritCree = inscritLogique.modifierInscrit(inscrit);
        return Response.status(200).entity(inscritCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteInscrit(@PathParam("id") final Long id) {
        inscritLogique.supprimerInscrit(id);
        return Response.status(200).build();
    }

    @POST
    @Path("/rotationListe")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rotationInscrits(final List<Inscrit> inscrits) {
        List<Inscrit> result = inscritLogique.rotationInscrits(inscrits);
        return Response.status(200).entity(result).build();
    }
}
