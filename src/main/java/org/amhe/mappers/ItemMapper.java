package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.ItemDto;
import org.amhe.models.entites.Item;
import org.amhe.repos.ItemRepo;

import java.util.*;

@ApplicationScoped
public class ItemMapper {
    @Inject
    ItemRepo repo;

    /**
     * DTO COMPLET
     */

    public List<Item> listeDtoVersBase(final List<ItemDto> itemsDto) {
        List<Item> items = new ArrayList<>();
        itemsDto.forEach(itemDto ->
                items.add(this.dtoVersBase(itemDto))
        );
        return items;
    }

    public List<ItemDto> listeBaseVersDto(final List<Item> items) {
        List<ItemDto> itemDto = new ArrayList<>();
        items.forEach(ruleset ->
                itemDto.add(this.baseVersDto(ruleset))
        );
        return itemDto;
    }

    public Item dtoVersBase(final ItemDto itemDto) {
        if (Objects.isNull(itemDto)) {
            return null;
        }
        Item ruleset = new Item();
        ruleset.setId(itemDto.getId());
        ruleset.setCode(itemDto.getCode());
        ruleset.setType(itemDto.getType());
        ruleset.setLibelle(itemDto.getLibelle());
        return ruleset;
    }

    public ItemDto baseVersDto(final Item item) {
        if (Objects.isNull(item)) {
            return null;
        }
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setCode(item.getCode());
        itemDto.setType(item.getType());
        itemDto.setLibelle(item.getLibelle());
        return itemDto;
    }

    public Set<ItemDto> recupererItems(final Set<Long> ids) {
        if (Objects.isNull(ids))
            return new HashSet<>();
        return new HashSet<>(this.recupererItems(new ArrayList<>(ids)));
    }


    public List<ItemDto> recupererItems(final List<Long> ids) {
        if (Objects.isNull(ids))
            return new ArrayList<>();
        return ids.stream()
                .map(id -> repo.getItemById(id))
                .map(this::baseVersDto)
                .toList();
    }

    public Set<Long> recupererIds(final Set<ItemDto> items) {
        if (Objects.isNull(items))
            return new HashSet<>();
        return new HashSet<>(this.recupererIds(new ArrayList<>(items)));
    }

    public List<Long> recupererIds(final List<ItemDto> items) {
        if (Objects.isNull(items))
            return new ArrayList<>();
        return items.stream()
                .map(ItemDto::getId)
                .toList();
    }
}

