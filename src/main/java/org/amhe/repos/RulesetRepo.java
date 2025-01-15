package org.amhe.repos;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.amhe.models.Ruleset;

import java.util.List;

@ApplicationScoped
public class RulesetRepo {
    @Inject
    EntityManager em;

    @Transactional
    public List<Ruleset> getRulesets() {
        return em.createQuery("from Ruleset", Ruleset.class).getResultList();
    }

    @Transactional
    public Ruleset getRulesetById(final Long id) {
        return em.find(Ruleset.class, id);
    }

    @Transactional
    public Ruleset createRuleset(final Ruleset nouveauRuleset) {
        return em.merge(nouveauRuleset);
    }

    @Transactional
    public Ruleset editRuleset(final Ruleset rulesetModifie) {
        return em.merge(rulesetModifie);
    }

    @Transactional
    public void deleteRuleset(final Long id) {
        Ruleset rulesetASpprimer = this.getRulesetById(id);
        em.remove(rulesetASpprimer);
    }

}
