package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.RulesetApercuDto;
import org.amhe.models.dtos.RulesetDto;
import org.amhe.models.entites.Ruleset;
import org.amhe.repos.ItemRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RulesetMapper {
    @Inject
    ItemMapper itemMapper;

    @Inject
    ItemRepo itemRepo;

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
            rulesetDto.setId(ruleset.getId());
            rulesetDto.setNom(ruleset.getNom());
            rulesetDto.setDescription(ruleset.getDescription());
            rulesetDto.setTimerLimite(ruleset.getTimerLimite());
            rulesetDto.setTimerReverse(ruleset.getTimerReverse());
            rulesetDto.setVulnerants(itemMapper.recupererItems(ruleset.getVulnerants()));
            rulesetDto.setCibles(itemMapper.recupererItems(ruleset.getCibles()));
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
            ruleset.setVulnerants(itemMapper.recupererIds(rulesetDto.getVulnerants()));
            ruleset.setCibles(itemMapper.recupererIds(rulesetDto.getCibles()));
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
            rulesetDto.setVulnerants(itemMapper.recupererItems(ruleset.getVulnerants()));
            rulesetDto.setCibles(itemMapper.recupererItems(ruleset.getCibles()));
        }
        return rulesetDto;
    }
}

