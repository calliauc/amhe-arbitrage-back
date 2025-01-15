package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Cible;

import java.util.List;

@ApplicationScoped
public class CibleRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Cible> getCibles() {
        return em.createQuery("from Cible", Cible.class).getResultList();
    }

    @Transactional
    public Cible getCibleById(final Long id) {
        return em.find(Cible.class, id);
    }

    @Transactional
    public List<Cible> getListCibleRuleset(final List<Long> ids) {
        return em.createQuery("from Cible where id IN (:ids)", Cible.class)
                .setParameter("ids", ids).getResultList();
    }
    
    @Transactional
    public Cible createCible(final Cible nouveauCible) {
        return em.merge(nouveauCible);
    }

    @Transactional
    public Cible editCible(final Cible nouveauCible) {
        return em.merge(nouveauCible);
    }

    @Transactional
    public void deleteCible(final Long id) {
        Cible cibleASpprimer = this.getCibleById(id);
        em.remove(cibleASpprimer);
    }

}
