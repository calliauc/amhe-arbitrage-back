package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
    public List<Combattant> chercherCombattantsParNom(final String nom) {
        String likeNom = "%" + nom.toLowerCase() + "%";
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Combattant> query = cb.createQuery(Combattant.class);
        Root<Combattant> root = query.from(Combattant.class);

        Predicate prenomPredicate = cb.like(cb.lower(root.get("prenom")), likeNom);
        Predicate nomPredicate = cb.like(cb.lower(root.get("nom")), likeNom);
        Predicate predicate = cb.or(prenomPredicate, nomPredicate);
        query.select(root).where(predicate);

        return em.createQuery(query).getResultList();
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
