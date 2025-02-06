package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.models.Secu;
import org.amhe.repos.SecuRepo;

import java.util.Objects;

@Path("/secu")
public class SecuResource {
    @Inject
    SecuRepo secuRepo;

    @GET
    @Path("/exist/{code}")
    public Response checkSecuExist(@PathParam("code") final String code) {
        if (Objects.isNull(secuRepo.findSecuByCode(code))) {
            return Response.status(204).build();
        }
        return Response.status(200).build();
    }

    @GET
    @Path("/check/{code}")
    public Response checkSecuCorrect(@PathParam("code") final String code, final String secret) {
        try {
            Secu secuBase = secuRepo.findSecuByCode(code);
            if (Objects.equals(secuBase.getSecret(), secret)) {
                return Response.status(200).build();
            } else {
                return Response.status(401).build();
            }
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClub(final Secu nouveauSecu) {
        try {
            if (Objects.isNull(secuRepo.findSecuByCode(nouveauSecu.getCode()))) {
                Secu secu = secuRepo.createSecu(nouveauSecu);
                return Response.status(201).build();
            } else {
                return Response.status(409).build();
            }
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

}
