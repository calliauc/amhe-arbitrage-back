package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.amhe.models.dtos.ItemDto;
import org.amhe.models.entites.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ItemMapper {
//    /**
//     * APERCU
//     */
//    public List<ItemDto> listeBaseVersApercuDto(final List<Item> items) {
//        List<ItemDto> itemsDto = new ArrayList<>();
//        items.forEach(item ->
//                itemsDto.add(this.baseVersDto(item))
//        );
//        return itemsDto;
//    }

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
        Item ruleset = new Item();
        if (Objects.nonNull(itemDto)) {
            ruleset.setId(itemDto.getId());
            ruleset.setCode(itemDto.getCode());
            ruleset.setType(itemDto.getType());
            ruleset.setLibelle(itemDto.getLibelle());
        }
        return ruleset;
    }

    public ItemDto baseVersDto(final Item ruleset) {
        ItemDto itemDto = new ItemDto();
        if (Objects.nonNull(ruleset)) {
            itemDto.setId(ruleset.getId());
            itemDto.setCode(ruleset.getCode());
            itemDto.setType(ruleset.getType());
            itemDto.setLibelle(ruleset.getLibelle());
        }
        return itemDto;
    }

}

