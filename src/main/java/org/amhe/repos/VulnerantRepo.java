package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Vulnerant;

import java.util.List;

@ApplicationScoped
public class VulnerantRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Vulnerant> getVulnerants() {
        return em.createQuery("from Vulnerant", Vulnerant.class).getResultList();
    }

    @Transactional
    public Vulnerant getVulnerantByCode(final String code) {
        return em.find(Vulnerant.class, code);
    }

    @Transactional
    public List<Vulnerant> getListVulnerantsRuleset(final List<String> codes) {
        return em.createQuery("from Vulnerant where code IN (:codes)", Vulnerant.class)
                .setParameter("codes", codes).getResultList();
    }

    @Transactional
    public Vulnerant createVulnerant(final Vulnerant nouveauVulnerant) {
        return em.merge(nouveauVulnerant);
    }

    @Transactional
    public Vulnerant editVulnerant(final Vulnerant nouveauVulnerant) {
        return em.merge(nouveauVulnerant);
    }

    @Transactional
    public void deleteVulnerant(final String code) {
        Vulnerant vulnerantASpprimer = this.getVulnerantByCode(code);
        em.remove(vulnerantASpprimer);
    }

}
