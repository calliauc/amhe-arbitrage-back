package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.entites.Item;
import org.amhe.repos.ItemRepo;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ItemLogique {
    @Inject
    ItemRepo repo;

    public List<Item> getItems(String type) {
        if (Objects.nonNull(type)) {
            return repo.getItemsParType(type);
        }
        return repo.getItems();
    }

    public Item getItemById(final Long id) {
        return repo.getItemById(id);
    }

    public Item creerItem(final Item nouveauItem) {
        return repo.createItem(nouveauItem);
    }

    public Item modifierItem(final Item item) {
        return repo.editItem(item);
    }

    public void supprimerItem(final Long id) {
        repo.deleteItem(id);
    }

}