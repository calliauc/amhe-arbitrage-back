package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.amhe.models.entites.Evenement;
import org.amhe.models.entites.Match;
import org.amhe.models.trier.CriteresRechercheMatchs;

import java.util.List;
import java.util.Objects;

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
    public List<Match> getMatchsByEvent(final Long eventId) {
        Evenement event = new Evenement();
        event.setId(eventId);
        return em.createQuery("FROM Match WHERE evenement = :event", Match.class)
                .setParameter("event", event).getResultList();
    }

    @Transactional
    public List<Match> rechercheMatchsParCriteres(final CriteresRechercheMatchs criteres) {

        List<Match> matchs;
        if (Objects.nonNull(criteres.getIdInscrit())) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Match> query = cb.createQuery(Match.class);
            Root<Match> root = query.from(Match.class);

            Predicate aPredicate = cb.equal(root.get("infosA").get("id"), criteres.getIdInscrit());
            Predicate bPredicate = cb.equal(root.get("infosB").get("id"), criteres.getIdInscrit());
            Predicate cpredicate = cb.or(aPredicate, bPredicate);
            query.select(root).where(cpredicate);

            matchs = em.createQuery(query).getResultList();
        } else {
            matchs = getMatchs();
        }
        return matchs;
    }

    @Transactional
    public Match createMatch(final Match nouveauMatch) {
        return em.merge(nouveauMatch);
    }

    @Transactional
    public Match editMatch(final Long id, final Match matchModifie) {
        return em.merge(matchModifie);
    }

    @Transactional
    public void deleteMatch(final Long id) {
        Match matchASpprimer = this.getMatchById(id);
        em.remove(matchASpprimer);
    }

}
