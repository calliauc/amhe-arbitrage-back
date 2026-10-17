package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.entites.Evenement;
import org.amhe.models.entites.Poule;

import java.util.List;

@ApplicationScoped
public class PouleRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Poule> getPoules() {
        return em.createQuery("from Poule", Poule.class).getResultList();
    }

    @Transactional
    public Poule getPouleById(final Long id) {
        return em.find(Poule.class, id);
    }

    @Transactional
    public List<Poule> getPoulesByEvent(final Long eventId) {
        Evenement event = new Evenement();
        event.setId(eventId);
        return em.createQuery("FROM Poule WHERE evenement = :event", Poule.class)
                .setParameter("event", event).getResultList();
    }
    
    @Transactional
    public Poule createPoule(final Poule nouveauPoule) {
        return em.merge(nouveauPoule);
    }

    @Transactional
    public Poule editPoule(final Poule nouveauPoule) {
        return em.merge(nouveauPoule);
    }

    @Transactional
    public void deletePoule(final Long id) {
        Poule pouleASpprimer = this.getPouleById(id);
        em.remove(pouleASpprimer);
    }
}
