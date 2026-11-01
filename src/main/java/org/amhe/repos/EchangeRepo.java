package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.entites.Echange;
import org.amhe.models.entites.Inscrit;

import java.util.List;

@ApplicationScoped
public class EchangeRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Echange> getEchanges() {
        return em.createQuery("from Echange", Echange.class).getResultList();
    }

    @Transactional
    public Echange getEchangeById(final Long id) {
        return em.find(Echange.class, id);
    }

    @Transactional
    public List<Echange> getEchangesByMatchId(final Long match_id) {
        return em.createQuery("FROM Echange WHERE matchId = :match_id", Echange.class)
                .setParameter("match_id", match_id).getResultList();
    }

    @Transactional
    public List<Echange> getEchangesByInscritId(final Long inscritId) {
        Inscrit inscrit = new Inscrit();
        inscrit.setId(inscritId);
        return em.createQuery("FROM Coup WHERE inscritA = :inscrit OR inscritB = :inscrit", Echange.class)
                .setParameter("inscrit", inscrit).getResultList();
    }

    @Transactional
    public List<Echange> getEchangesByIdA(final Long inscritA_id) {
        Inscrit inscritA = new Inscrit();
        inscritA.setId(inscritA_id);
        return em.createQuery("FROM Echange WHERE inscritA = :inscritA", Echange.class)
                .setParameter("inscritA", inscritA).getResultList();
    }

    @Transactional
    public List<Echange> getEchangesByIdB(final Long inscritB_id) {
        Inscrit inscritB = new Inscrit();
        inscritB.setId(inscritB_id);
        return em.createQuery("FROM Echange WHERE inscritB = :inscritB", Echange.class)
                .setParameter("inscritB", inscritB).getResultList();
    }

    @Transactional
    public Echange creerEchange(final Echange nouveauEchange) {
        return em.merge(nouveauEchange);
    }

    @Transactional
    public Echange modifierEchange(final Echange echange) {
        return em.merge(echange);
    }

    @Transactional
    public void supprimerEchange(final Long id) {
        Echange echangeASpprimer = this.getEchangeById(id);
        em.remove(echangeASpprimer);
    }

}
