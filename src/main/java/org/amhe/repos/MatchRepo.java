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
import org.amhe.models.CriteresRechercheMatchs;
import org.amhe.models.Match;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
    public List<Match> rechercheMatchsParCriteres(final CriteresRechercheMatchs criteres) {

        List<Match> matchs;
        if (Objects.nonNull(criteres.getIdCombattant())) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Match> query = cb.createQuery(Match.class);
            Root<Match> root = query.from(Match.class);

            Predicate aPredicate = cb.equal(root.get("infosA").get("id"), criteres.getIdCombattant());
            Predicate bPredicate = cb.equal(root.get("infosB").get("id"), criteres.getIdCombattant());
            Predicate cpredicate = cb.or(aPredicate, bPredicate);
            query.select(root).where(cpredicate);

            matchs = em.createQuery(query).getResultList();
        } else {
            matchs = getMatchs();
        }
        if (Objects.nonNull(criteres.getIdTags()) && !criteres.getIdTags().isEmpty()) {
            Set<Long> tagsId = new HashSet<>(criteres.getIdTags());
            matchs = matchs.stream()
                    .filter(match -> {
                        Set<Long> tagsMId = new HashSet<>(match.getTags());
                        return tagsMId.containsAll(tagsId);
                    })
                    .toList();
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
