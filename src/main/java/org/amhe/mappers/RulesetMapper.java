package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.amhe.models.*;
import org.amhe.repos.CibleRepo;
import org.amhe.repos.VulnerantRepo;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationScoped
public class RulesetMapper {
    @Inject
    VulnerantRepo vulnerantRepo;
    @Inject
    CibleRepo cibleRepo;

    public List<Ruleset> listeExpoVersBase(final List<RulesetExpo> rulesetsExpo) {
        List<Ruleset> rulesets = new ArrayList<>();
        rulesetsExpo.forEach(rulesetExpo ->
                rulesets.add(this.expoVersBase(rulesetExpo))
        );
        return rulesets;
    }

    public List<RulesetExpo> listeBaseVersExpo(final List<Ruleset> rulesets) {
        List<RulesetExpo> rulesetsExpo = new ArrayList<>();
        rulesets.forEach(ruleset ->
                rulesetsExpo.add(this.baseVersExpo(ruleset))
        );
        return rulesetsExpo;
    }

    public Ruleset expoVersBase(final RulesetExpo rulesetExpo) {
        Ruleset ruleset = new Ruleset();
        ruleset.setId(rulesetExpo.getId());
        ruleset.setNom(rulesetExpo.getNom());
        ruleset.setDescription(rulesetExpo.getDescription());
        ruleset.setTimerLimite(rulesetExpo.getTimerLimite());
        ruleset.setTimerReverse(rulesetExpo.getTimerReverse());
        ruleset.setVulnerants(rulesetExpo.getVulnerants().stream().filter(RulesetRefExpo::isChecked).map(RulesetRefExpo::getId).toList());
        ruleset.setCibles(rulesetExpo.getCibles().stream().filter(RulesetRefExpo::isChecked).map(RulesetRefExpo::getId).toList());
        return ruleset;
    }

    public RulesetExpo baseVersExpo(final Ruleset ruleset) {
        RulesetExpo rulesetExpo = new RulesetExpo();
        rulesetExpo.setId(ruleset.getId());
        rulesetExpo.setNom(ruleset.getNom());
        rulesetExpo.setDescription(ruleset.getDescription());
        rulesetExpo.setTimerLimite(ruleset.getTimerLimite());
        rulesetExpo.setTimerReverse(ruleset.getTimerReverse());
        rulesetExpo.setVulnerants(this.hydraterVulnerants(ruleset.getVulnerants()));
        rulesetExpo.setCibles(this.hydraterCibles(ruleset.getCibles()));
        return rulesetExpo;
    }

    List<RulesetRefExpo> hydraterVulnerants(final List<Long> ids) {
        List<RulesetRefExpo> vulnerantsExpo = this.vulnerantsVersExpo(vulnerantRepo.getVulnerants());
        vulnerantsExpo.forEach(v -> {
            if (ids.contains(v.getId())) {
                v.setChecked(true);
            }
        });
        return vulnerantsExpo;
    }

    List<RulesetRefExpo> hydraterCibles(final List<Long> ids) {
        List<RulesetRefExpo> ciblesExpo = this.ciblesVersExpo(cibleRepo.getCibles());
        ciblesExpo.forEach(c -> {
            if (ids.contains(c.getId())) {
                c.setChecked(true);
            }
        });
        return ciblesExpo;
    }

    List<RulesetRefExpo> vulnerantsVersExpo(List<Vulnerant> vulnerants) {
        List<RulesetRefExpo> rulesetsRefExpo = new ArrayList<>();
        vulnerants.forEach(vulnerant -> {
            RulesetRefExpo rulesetRefExpo = new RulesetRefExpo();
            rulesetRefExpo.setId(vulnerant.getId());
            rulesetRefExpo.setCode(vulnerant.getCode());
            rulesetRefExpo.setLibelle(vulnerant.getLibelle());
            rulesetRefExpo.setChecked(false);
            rulesetsRefExpo.add(rulesetRefExpo);
        });
        return rulesetsRefExpo;
    }

    List<RulesetRefExpo> ciblesVersExpo(List<Cible> cibles) {
        List<RulesetRefExpo> rulesetsRefExpo = new ArrayList<>();
        cibles.forEach(cible -> {
            RulesetRefExpo rulesetRefExpo = new RulesetRefExpo();
            rulesetRefExpo.setId(cible.getId());
            rulesetRefExpo.setCode(cible.getCode());
            rulesetRefExpo.setLibelle(cible.getLibelle());
            rulesetRefExpo.setChecked(false);
            rulesetsRefExpo.add(rulesetRefExpo);
        });
        return rulesetsRefExpo;
    }
}

