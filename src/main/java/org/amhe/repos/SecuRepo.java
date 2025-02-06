package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Secu;

@ApplicationScoped
public class SecuRepo {
    @Inject
    EntityManager em;

    @Transactional
    public Secu findSecuByCode(final String code) {
        try {
            return em.createQuery("from Secu where code IN (:code)", Secu.class)
                    .setParameter("code", code).getResultList().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    public Secu createSecu(final Secu secu) {
        return em.merge(secu);
    }

}
