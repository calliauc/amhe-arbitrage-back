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

import java.util.HashMap;
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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Match> query = cb.createQuery(Match.class);
        Root<Match> root = query.from(Match.class);
        HashMap<String, Predicate> predicates = new HashMap<>();
        Predicate ePredicate = cb.equal(root.get("evenement").get("id"), criteres.getIdEvenement());
        predicates.put("event", ePredicate);

        if (Objects.nonNull(criteres.getIdPoule())) {
            Predicate pPredicate = cb.equal(root.get("poule").get("id"), criteres.getIdPoule());
            predicates.put("poule", pPredicate);
        }

        if (Objects.nonNull(criteres.getIdInscrit())) {
            Predicate iaPredicate = cb.equal(root.get("inscritA").get("id"), criteres.getIdInscrit());
            Predicate ibPredicate = cb.equal(root.get("inscritB").get("id"), criteres.getIdInscrit());
            Predicate iPredicate = cb.or(iaPredicate, ibPredicate);
            predicates.put("inscrit", iPredicate);
        }
        Predicate predicate = predicates.get("event");
        if (predicates.containsKey("inscrit")) {
            predicate = cb.and(predicate, predicates.get("inscrit"));
        }
        if (predicates.containsKey("poule")) {
            predicate = cb.and(predicate, predicates.get("poule"));
        }

        query.select(root).where(predicate);
        matchs = em.createQuery(query).getResultList();
        return matchs;
    }

    @Transactional
    public Match createMatch(final Match nouveauMatch) {
        return em.merge(nouveauMatch);
    }

    @Transactional
    public Match editMatch(final Match matchModifie) {
        return em.merge(matchModifie);
    }

    @Transactional
    public void deleteMatch(final Long matchId) {
        Match linkedMatch = getMatchById(matchId);
        em.remove(linkedMatch);
    }

}
