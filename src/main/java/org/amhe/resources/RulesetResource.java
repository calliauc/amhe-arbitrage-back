package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.amhe.mappers.RulesetMapper;
import org.amhe.models.dtos.RulesetDto;
import org.amhe.repos.RulesetRepo;

import java.util.List;

@Slf4j
@Path("/rulesets")
public class RulesetResource {
    @Inject
    RulesetRepo rulesetRepo;
    @Inject
    RulesetMapper rulesetMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRulesets() {
        List<RulesetDto> rulesets = rulesetMapper.listeBaseVersDto(this.rulesetRepo.getRulesets());
        if (rulesets.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(rulesets).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRulesetById(@PathParam("id") final Long id) {
        RulesetDto ruleset = rulesetMapper.baseVersDto(rulesetRepo.getRulesetById(id));
        if (null == ruleset) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(ruleset).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRuleset(final RulesetDto nouveauRuleset) {
        RulesetDto rulesetCree = rulesetMapper.baseVersDto(rulesetRepo.createRuleset(rulesetMapper.dtoVersBase(nouveauRuleset)));
        return Response.status(201).entity(rulesetCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRuleset(final RulesetDto ruleset) {
        RulesetDto rulesetCree = rulesetMapper.baseVersDto(rulesetRepo.editRuleset(rulesetMapper.dtoVersBase(ruleset)));
        return Response.status(200).entity(rulesetCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteRuleset(@PathParam("id") final Long id) {
        try {
            rulesetRepo.deleteRuleset(id);
            return Response.status(200).build();
        } catch (Exception e) {
            Exception err = new Exception("Impossible de supprimer le ruleset.");
            return Response.status(500).entity(err.getMessage()).build();
        }
    }
}
