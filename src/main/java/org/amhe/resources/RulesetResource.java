package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.models.Ruleset;
import org.amhe.repos.RulesetRepo;

import java.util.List;

@Path("/rulesets")
public class RulesetResource {
    @Inject
    RulesetRepo rulesetRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRulesets() {
        List<Ruleset> rulesets = rulesetRepo.getRulesets();
        if (rulesets.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(rulesets).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRulesetById(@PathParam("id") final Long id) {
        Ruleset ruleset = rulesetRepo.getRulesetById(id);
        if (null == ruleset) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(ruleset).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRuleset(final Ruleset nouveauRuleset) {
        Ruleset rulesetCree = rulesetRepo.createRuleset(nouveauRuleset);
        return Response.status(201).entity(rulesetCree).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRuleset(@PathParam("id") final Long id, final Ruleset ruleset) {
        Ruleset rulesetCree = rulesetRepo.editRuleset(ruleset);
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
