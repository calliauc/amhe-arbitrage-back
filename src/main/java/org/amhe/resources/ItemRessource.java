package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.amhe.mappers.ItemMapper;
import org.amhe.models.dtos.ItemDto;
import org.amhe.models.entites.Item;
import org.amhe.repos.ItemRepo;

import java.util.List;

@Path("/items")
public class ItemRessource {
    @Inject
    ItemMapper itemMapper;
    @Inject
    ItemRepo itemRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem() {
        List<ItemDto> item = itemMapper.listeBaseVersDto(itemRepo.getItems());
        if (item.isEmpty()) {
            return Response.status(204).header("Message", "Aucun item trouvé").build();
        }
        return Response.status(200).entity(item).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemById(@PathParam("id") final Long id) {
        ItemDto item = itemMapper.baseVersDto(itemRepo.getItemById(id));
        if (null == item) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(item).build();
    }

    @POST
    @Path("/ruleset")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemByRuleset(final List<String> codes, final String type) {
        List<ItemDto> items = itemMapper.listeBaseVersDto(itemRepo.getListParCodesEtType(codes, type));
        if (null == items) {
            return Response.status(204).tag("tag").build();
        }
        return Response.status(200).entity(items).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(final Item nouveauItem) {
        ItemDto itemCree = itemMapper.baseVersDto(itemRepo.createItem(nouveauItem));
        return Response.status(201).entity(itemCree).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editItem(final Item item) {
        ItemDto itemCree = itemMapper.baseVersDto(itemRepo.editItem(item));
        return Response.status(200).entity(itemCree).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("id") final Long id) {
        itemRepo.deleteItem(id);
        return Response.status(200).build();
    }
}
