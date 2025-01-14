package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Coup;

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
    public List<Coup> getCoupsByMatchId(final Long match_id) {
        return em.createQuery("FROM Coup WHERE matchId = :match_id", Coup.class)
                .setParameter("match_id", match_id).getResultList();
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
