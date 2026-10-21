package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.entites.Item;

import java.util.List;

@ApplicationScoped
public class ItemRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Item> getItems() {
        return em.createQuery("from ITEM", Item.class).getResultList();
    }

    @Transactional
    public Item getItemById(final Long id) {
        return em.find(Item.class, id);
    }

    @Transactional
    public List<Item> getListParCodesEtType(final List<String> codes, final String type) {
        return em.createQuery("from ITEM where code IN (:codes) and type = (:type)", Item.class)
                .setParameter("codes", codes)
                .setParameter("type", type).getResultList();
    }

    @Transactional
    public Item createItem(final Item nouveauItem) {
        return em.merge(nouveauItem);
    }

    @Transactional
    public Item editItem(final Item nouveauItem) {
        return em.merge(nouveauItem);
    }

    @Transactional
    public void deleteItem(final Long id) {
        Item itemASpprimer = this.getItemById(id);
        em.remove(itemASpprimer);
    }

}
