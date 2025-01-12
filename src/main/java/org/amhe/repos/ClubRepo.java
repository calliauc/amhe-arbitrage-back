package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Club;

import java.util.List;

@ApplicationScoped
public class ClubRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Club> getClubs() {
        return em.createQuery("from Club", Club.class).getResultList();
    }

    @Transactional
    public Club getClubById(final Long id) {
        return em.find(Club.class, id);
    }

    @Transactional
    public Club createClub(final Club nouveauClub) {
        return em.merge(nouveauClub);
    }

    @Transactional
    public Club editClub(final Club nouveauClub) {
        return em.merge(nouveauClub);
    }

    @Transactional
    public void deleteClub(final Long id) {
        Club clubASpprimer = this.getClubById(id);
        em.remove(clubASpprimer);
    }

}
