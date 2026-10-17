package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.amhe.models.entites.Individu;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class IndividuRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Individu> getIndividus() {
        return em.createQuery("from Individu", Individu.class).getResultList();
    }

    @Transactional
    public Individu getIndividuById(final Long id) {
        return em.find(Individu.class, id);
    }

    @Transactional
    public List<Individu> chercherIndividusParNom(final String nom) {
        if (Objects.isNull(nom)) {
            return this.getIndividus();
        }
        String likeNom = "%" + nom.toLowerCase() + "%";
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Individu> query = cb.createQuery(Individu.class);
        Root<Individu> root = query.from(Individu.class);

        Predicate prenomPredicate = cb.like(cb.lower(root.get("prenom")), likeNom);
        Predicate nomPredicate = cb.like(cb.lower(root.get("nom")), likeNom);
        Predicate predicate = cb.or(prenomPredicate, nomPredicate);
        query.select(root).where(predicate);

        return em.createQuery(query).getResultList();
    }

    @Transactional
    public Individu createIndividu(final Individu nouveauIndividu) {
        return em.merge(nouveauIndividu);
    }

    @Transactional
    public Individu editIndividu(final Individu nouveauIndividu) {
        return em.merge(nouveauIndividu);
    }

    @Transactional
    public void deleteIndividu(final Long id) {
        Individu individuASpprimer = this.getIndividuById(id);
        em.remove(individuASpprimer);
    }

}
