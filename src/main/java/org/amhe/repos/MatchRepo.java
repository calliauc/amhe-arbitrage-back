package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.amhe.models.Match;

import java.util.List;

@Slf4j
@ApplicationScoped
public class MatchRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Match> getMatchs() {
        return em.createQuery("from Match", Match.class).getResultList();
    }

    @Transactional
    public Match getMatchById(final Long id) {
        return em.find(Match.class, id);
    }

    @Transactional
    public Match createMatch(final Match nouveauMatch) {
        return em.merge(nouveauMatch);
    }

    @Transactional
    public Match editMatch(final Long id, final Match matchModifie) {
        log.info("Passage repo Match");
        return em.merge(matchModifie);
    }

    @Transactional
    public void deleteMatch(final Long id) {
        Match matchASpprimer = this.getMatchById(id);
        em.remove(matchASpprimer);
    }

}
