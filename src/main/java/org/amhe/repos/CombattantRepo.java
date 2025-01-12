package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Combattant;

import java.util.List;

@ApplicationScoped
public class CombattantRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Combattant> getCombattants() {
        return em.createQuery("from Combattant", Combattant.class).getResultList();
    }

    @Transactional
    public Combattant getCombattantById(final Long id) {
        return em.find(Combattant.class, id);
    }

    @Transactional
    public Combattant createCombattant(final Combattant nouveauCombattant) {
        return em.merge(nouveauCombattant);
    }

    @Transactional
    public Combattant editCombattant(final Combattant nouveauCombattant) {
        return em.merge(nouveauCombattant);
    }

    @Transactional
    public void deleteCombattant(final Long id) {
        Combattant combattantASpprimer = this.getCombattantById(id);
        em.remove(combattantASpprimer);
    }

}
