package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.entites.Evenement;

import java.util.List;

@ApplicationScoped
public class EvenementRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Evenement> getEvenements() {
        return em.createQuery("from Evenement", Evenement.class).getResultList();
    }

    @Transactional
    public Evenement getEvenementById(final Long id) {
        return em.find(Evenement.class, id);
    }

    @Transactional
    public Evenement createEvenement(final Evenement nouveauEvenement) {
        return em.merge(nouveauEvenement);
    }

    @Transactional
    public Evenement editEvenement(final Evenement evenementEdit) {
        return em.merge(evenementEdit);
    }

    @Transactional
    public void deleteEvenement(final Long id) {
        Evenement evenementASpprimer = this.getEvenementById(id);
        em.remove(evenementASpprimer);
    }

}
