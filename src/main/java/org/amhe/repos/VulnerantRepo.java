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
    public Vulnerant getVulnerantById(final Long id) {
        return em.find(Vulnerant.class, id);
    }

    @Transactional
    public List<Vulnerant> getListVulnerantsRuleset(final List<Long> ids) {
        return em.createQuery("from Vulnerant where id IN (:ids)", Vulnerant.class)
                .setParameter("ids", ids).getResultList();
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
    public void deleteVulnerant(final Long id) {
        Vulnerant vulnerantASpprimer = this.getVulnerantById(id);
        em.remove(vulnerantASpprimer);
    }

}
