package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.entites.Coup;

import java.util.List;

@ApplicationScoped
public class CoupRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Coup> getCoups() {
        return em.createQuery("from Coup", Coup.class).getResultList();
    }

    @Transactional
    public Coup getCoupById(final Long id) {
        return em.find(Coup.class, id);
    }

    @Transactional
    public Coup createCoup(final Coup nouveauCoup) {
        return em.merge(nouveauCoup);
    }

    @Transactional
    public Coup editCoup(final Coup nouveauCoup) {
        return em.merge(nouveauCoup);
    }

    @Transactional
    public void deleteCoup(final Long id) {
        Coup coupASpprimer = this.getCoupById(id);
        em.remove(coupASpprimer);
    }

}
