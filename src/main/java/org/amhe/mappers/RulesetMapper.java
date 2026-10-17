package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.RulesetApercuDto;
import org.amhe.models.dtos.RulesetDto;
import org.amhe.models.entites.Cible;
import org.amhe.models.entites.Ruleset;
import org.amhe.models.entites.Vulnerant;
import org.amhe.models.trier.RulesetRefExpo;
import org.amhe.repos.CibleRepo;
import org.amhe.repos.VulnerantRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RulesetMapper {
    @Inject
    VulnerantRepo vulnerantRepo;
    @Inject
    CibleRepo cibleRepo;

    /**
     * APERCU
     */
    public List<RulesetApercuDto> listeBaseVersApercuDto(final List<Ruleset> rulesets) {
        List<RulesetApercuDto> rulesetsDto = new ArrayList<>();
        rulesets.forEach(ruleset ->
                rulesetsDto.add(this.baseVersApercuDto(ruleset))
        );
        return rulesetsDto;
    }

    public RulesetApercuDto baseVersApercuDto(final Ruleset ruleset) {
        RulesetApercuDto rulesetDto = new RulesetApercuDto();
        if (Objects.nonNull(ruleset)) {
            rulesetDto.setNom(ruleset.getNom());
            rulesetDto.setDescription(ruleset.getDescription());
            rulesetDto.setTimerLimite(ruleset.getTimerLimite());
            rulesetDto.setTimerReverse(ruleset.getTimerReverse());
            rulesetDto.setVulnerants(ruleset.getVulnerants());
            rulesetDto.setCibles(ruleset.getCibles());
        }
        return rulesetDto;
    }

    /**
     * DTO COMPLET
     */

    public List<Ruleset> listeDtoVersBase(final List<RulesetDto> rulesetsDto) {
        List<Ruleset> rulesets = new ArrayList<>();
        rulesetsDto.forEach(rulesetDto ->
                rulesets.add(this.dtoVersBase(rulesetDto))
        );
        return rulesets;
    }

    public List<RulesetDto> listeBaseVersDto(final List<Ruleset> rulesets) {
        List<RulesetDto> rulesetsDto = new ArrayList<>();
        rulesets.forEach(ruleset ->
                rulesetsDto.add(this.baseVersDto(ruleset))
        );
        return rulesetsDto;
    }

    public Ruleset dtoVersBase(final RulesetDto rulesetDto) {
        Ruleset ruleset = new Ruleset();
        if (Objects.nonNull(rulesetDto)) {
            ruleset.setId(rulesetDto.getId());
            ruleset.setNom(rulesetDto.getNom());
            ruleset.setDescription(rulesetDto.getDescription());
            ruleset.setTimerLimite(rulesetDto.getTimerLimite());
            ruleset.setTimerReverse(rulesetDto.getTimerReverse());
            ruleset.setVulnerants(rulesetDto.getVulnerants().stream().filter(RulesetRefExpo::isChecked).map(RulesetRefExpo::getCode).toList());
            ruleset.setCibles(rulesetDto.getCibles().stream().filter(RulesetRefExpo::isChecked).map(RulesetRefExpo::getCode).toList());
        }
        return ruleset;
    }

    public RulesetDto baseVersDto(final Ruleset ruleset) {
        RulesetDto rulesetDto = new RulesetDto();
        if (Objects.nonNull(ruleset)) {
            rulesetDto.setId(ruleset.getId());
            rulesetDto.setNom(ruleset.getNom());
            rulesetDto.setDescription(ruleset.getDescription());
            rulesetDto.setTimerLimite(ruleset.getTimerLimite());
            rulesetDto.setTimerReverse(ruleset.getTimerReverse());
            rulesetDto.setVulnerants(this.hydraterVulnerants(ruleset.getVulnerants()));
            rulesetDto.setCibles(this.hydraterCibles(ruleset.getCibles()));
        }
        return rulesetDto;
    }


    List<RulesetRefExpo> hydraterVulnerants(final List<String> codes) {
        List<RulesetRefExpo> vulnerantsDto = this.vulnerantsVersDto(vulnerantRepo.getVulnerants());
        vulnerantsDto.forEach(v -> {
            if (codes.contains(v.getCode())) {
                v.setChecked(true);
            }
        });
        return vulnerantsDto;
    }

    List<RulesetRefExpo> hydraterCibles(final List<String> codes) {
        List<RulesetRefExpo> ciblesDto = this.ciblesVersDto(cibleRepo.getCibles());
        ciblesDto.forEach(c -> {
            if (codes.contains(c.getCode())) {
                c.setChecked(true);
            }
        });
        return ciblesDto;
    }

    List<RulesetRefExpo> vulnerantsVersDto(List<Vulnerant> vulnerants) {
        List<RulesetRefExpo> rulesetsRefDto = new ArrayList<>();
        vulnerants.forEach(vulnerant -> {
            RulesetRefExpo rulesetRefDto = new RulesetRefExpo();
            rulesetRefDto.setCode(vulnerant.getCode());
            rulesetRefDto.setLibelle(vulnerant.getLibelle());
            rulesetRefDto.setChecked(false);
            rulesetsRefDto.add(rulesetRefDto);
        });
        return rulesetsRefDto;
    }

    List<RulesetRefExpo> ciblesVersDto(List<Cible> cibles) {
        List<RulesetRefExpo> rulesetsRefDto = new ArrayList<>();
        cibles.forEach(cible -> {
            RulesetRefExpo rulesetRefDto = new RulesetRefExpo();
            rulesetRefDto.setCode(cible.getCode());
            rulesetRefDto.setLibelle(cible.getLibelle());
            rulesetRefDto.setChecked(false);
            rulesetsRefDto.add(rulesetRefDto);
        });
        return rulesetsRefDto;
    }
}

