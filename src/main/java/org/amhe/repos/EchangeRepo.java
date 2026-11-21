package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.amhe.models.entites.Echange;
import org.amhe.models.entites.Inscrit;
import org.amhe.models.entites.Match;

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
    public List<Echange> getEchangesByMatchId(final Match match) {
        return em.createQuery("FROM Echange WHERE match = :match", Echange.class)
                .setParameter("match", match).getResultList();
    }

    @Transactional
    public List<Echange> getEchangesByInscritId(final Long inscritId) {
        Inscrit inscrit = new Inscrit();
        inscrit.setId(inscritId);
        return em.createQuery("FROM Coup WHERE inscrit_a_id = :inscrit OR inscrit_b_id = :inscrit", Echange.class)
                .setParameter("inscrit", inscrit).getResultList();
    }

    @Transactional
    public List<Echange> getEchangesByIdA(final Long inscritA_id) {
        Inscrit inscritA = new Inscrit();
        inscritA.setId(inscritA_id);
        return em.createQuery("FROM Echange WHERE inscrit_a_id = :inscritA", Echange.class)
                .setParameter("inscritA", inscritA).getResultList();
    }

    @Transactional
    public List<Echange> getEchangesByIdB(final Long inscritB_id) {
        Inscrit inscritB = new Inscrit();
        inscritB.setId(inscritB_id);
        return em.createQuery("FROM Echange WHERE inscrit_b_id = :inscritB", Echange.class)
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
        Echange echangeASupprimer = this.getEchangeById(id);
        em.remove(echangeASupprimer);
    }

    @Transactional
    public void supprimerEchangesMatch(final Match match) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Echange> criteriaDelete = cb.createCriteriaDelete(Echange.class);
        Root<Echange> root = criteriaDelete.from(Echange.class);
        criteriaDelete.where(cb.equal(root.get("match"), match));
        em.createQuery(criteriaDelete);
    }

}
