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
    public Cible getCibleByCode(final String code) {
        return em.find(Cible.class, code);
    }

    @Transactional
    public List<Cible> getListCibleRuleset(final List<String> codes) {
        return em.createQuery("from Cible where code IN (:codes)", Cible.class)
                .setParameter("codes", codes).getResultList();
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
    public void deleteCible(final String code) {
        Cible cibleASpprimer = this.getCibleByCode(code);
        em.remove(cibleASpprimer);
    }

}
