package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Combattant;
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
    public List<Coup> getCoupsByAtkId(final Long atk_id) {
        Combattant attaquant = new Combattant();
        attaquant.setId(atk_id);
        return em.createQuery("FROM Coup WHERE attaquant = :attaquant", Coup.class)
                .setParameter("attaquant", attaquant).getResultList();
    }

    @Transactional
    public List<Coup> getCoupsByDefId(final Long def_id) {
        Combattant attaquant = new Combattant();
        attaquant.setId(def_id);
        return em.createQuery("FROM Coup WHERE attaquant = :attaquant", Coup.class)
                .setParameter("attaquant", attaquant).getResultList();
    }

    @Transactional
    public List<Coup> getCoupsByCombattantId(final Long combattantId) {
        Combattant combattant = new Combattant();
        combattant.setId(combattantId);
        return em.createQuery("FROM Coup WHERE attaquant = :attaquant OR defenseur = :defenseur", Coup.class)
                .setParameter("attaquant", combattant).setParameter("defenseur", combattant).getResultList();
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
