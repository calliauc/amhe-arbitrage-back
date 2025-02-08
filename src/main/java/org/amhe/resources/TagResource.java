package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.models.Tag;
import org.amhe.repos.TagRepo;

import java.util.List;

@Path("/tags")
public class TagResource {
    @Inject
    TagRepo tagRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTags() {
        List<Tag> tags = tagRepo.getTags();
        if (tags.isEmpty()) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(tags).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagById(@PathParam("id") final Long id) {
        Tag tag = tagRepo.getTagById(id);
        if (null == tag) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(tag).build();
    }

    @GET
    @Path("/ids")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagsByIds(final List<Long> ids) {
        List<Tag> tags = tagRepo.getListTags(ids);
        if (null == tags) {
            return Response.status(204).build();
        }
        return Response.status(200).entity(tags).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTag(final Tag nouveauTag) {
        Tag tagCree = tagRepo.createTag(nouveauTag);
        return Response.status(201).entity(tagCree).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editTag(@PathParam("id") final Long id, final Tag tag) {
        Tag tagCree = tagRepo.editTag(tag);
        return Response.status(200).entity(tagCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTag(@PathParam("id") final Long id) {
        try {
            tagRepo.deleteTag(id);
            return Response.status(200).build();
        } catch (Exception e) {
            Exception err = new Exception("Impossible de supprimer le tag.");
            return Response.status(500).entity(err.getMessage()).build();
        }
    }
}
