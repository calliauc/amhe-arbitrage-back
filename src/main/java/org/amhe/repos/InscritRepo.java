package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.amhe.models.entites.Evenement;
import org.amhe.models.entites.Inscrit;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class InscritRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Inscrit> getInscrits() {
        return em.createQuery("from Inscrit", Inscrit.class).getResultList();
    }

    @Transactional
    public Inscrit getInscritById(final Long id) {
        return em.find(Inscrit.class, id);
    }

    @Transactional
    public Inscrit createInscrit(final Inscrit nouveauInscrit) {
        return em.merge(nouveauInscrit);
    }

    @Transactional
    public Inscrit editInscrit(final Inscrit nouveauInscrit) {
        return em.merge(nouveauInscrit);
    }

    @Transactional
    public void deleteInscrit(final Long id) {
        Inscrit inscritASpprimer = this.getInscritById(id);
        em.remove(inscritASpprimer);
    }

    @Transactional
    public List<Inscrit> chercherInscritsParNom(final String nom) {
        if (Objects.isNull(nom)) {
            return this.getInscrits();
        }
        String likeNom = "%" + nom.toLowerCase() + "%";
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Inscrit> query = cb.createQuery(Inscrit.class);
        Root<Inscrit> root = query.from(Inscrit.class);

        Predicate prenomPredicate = cb.like(cb.lower(root.get("individu").get("prenom")), likeNom);
        Predicate nomPredicate = cb.like(cb.lower(root.get("individu").get("nom")), likeNom);
        Predicate predicate = cb.or(prenomPredicate, nomPredicate);
        query.select(root).where(predicate);

        return em.createQuery(query).getResultList();
    }

    @Transactional
    public List<Inscrit> getInscritsByEvent(final Long eventId) {
        Evenement event = new Evenement();
        event.setId(eventId);
        return em.createQuery("FROM Inscrit WHERE evenement = :event", Inscrit.class)
                .setParameter("event", event).getResultList();
    }
}
