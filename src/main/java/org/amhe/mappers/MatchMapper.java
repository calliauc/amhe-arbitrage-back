package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.CreationMatchDto;
import org.amhe.models.dtos.MatchDto;
import org.amhe.models.entites.Match;

import java.util.*;

@ApplicationScoped
public class MatchMapper {
    @Inject
    EvenementMapper evenementMapper;

    @Inject
    PouleMapper pouleMapper;

    @Inject
    InscritMapper inscritMapper;

    @Inject
    RulesetMapper rulesetMapper;

    @Inject
    ItemMapper itemMapper;

    public Set<Match> listeDtoVersBase(final Set<MatchDto> matchsDto) {
        Set<Match> matchs = new HashSet<>();
        if (Objects.nonNull(matchsDto))
            matchsDto.forEach(matchDto -> matchs.add(this.dtoVersBase(matchDto)));
        return matchs;
    }

    public List<Match> listeDtoVersBase(final List<MatchDto> matchsDto) {
        List<Match> matchs = new ArrayList<>();
        if (Objects.nonNull(matchsDto))
            matchsDto.forEach(matchDto -> matchs.add(this.dtoVersBase(matchDto)));
        return matchs;
    }

    public List<MatchDto> listeBaseVersDto(final List<Match> matchs) {
        List<MatchDto> matchsDto = new ArrayList<>();
        if (Objects.nonNull(matchs))
            matchs.forEach(match -> matchsDto.add(this.baseVersDto(match)));
        return matchsDto;
    }

    public Set<MatchDto> listeBaseVersDto(final Set<Match> matchs) {
        Set<MatchDto> matchsDto = new HashSet<>();
        if (Objects.nonNull(matchs))
            matchs.forEach(match -> matchsDto.add(this.baseVersDto(match)));
        return matchsDto;
    }

    public Match dtoVersBase(final MatchDto matchDto) {
        Match match = new Match();
        match.setId(matchDto.getId());
        match.setEvenement(evenementMapper.dtoVersBase(matchDto.getEvenement()));
        match.setPoule(pouleMapper.dtoVersBase(matchDto.getPoule()));
        match.setInscritA(inscritMapper.dtoVersBase(matchDto.getInscritA()));
        match.setInscritB(inscritMapper.dtoVersBase(matchDto.getInscritB()));
        match.setScoreA(matchDto.getScoreA());
        match.setScoreB(matchDto.getScoreB());
        match.setTimer(matchDto.getTimer());
        match.setDateCreation(matchDto.getDateCreation());
        match.setDateDebut(matchDto.getDateDebut());
        match.setDateFin(matchDto.getDateFin());
        match.setStatut(matchDto.getStatut());
        match.setTags(itemMapper.recupererIds(matchDto.getTags()));
        match.setRuleset(this.rulesetMapper.dtoVersBase(matchDto.getRuleset()));
        return match;
    }

    public MatchDto baseVersDto(final Match match) {
        MatchDto matchDto = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setEvenement(evenementMapper.baseVersDto(match.getEvenement()));
        matchDto.setPoule(pouleMapper.baseVersDto(match.getPoule()));
        matchDto.setInscritA(inscritMapper.baseVersDto(match.getInscritA()));
        matchDto.setInscritB(inscritMapper.baseVersDto(match.getInscritB()));
        matchDto.setScoreA(match.getScoreA());
        matchDto.setScoreB(match.getScoreB());
        matchDto.setDateCreation(match.getDateCreation());
        matchDto.setDateDebut(match.getDateDebut());
        matchDto.setDateFin(match.getDateFin());
        matchDto.setStatut(match.getStatut());
        matchDto.setTimer(match.getTimer());
        matchDto.setTags(itemMapper.recupererItems(match.getTags()));
        matchDto.setRuleset(this.rulesetMapper.baseVersDto(match.getRuleset()));
        return matchDto;
    }

    public Match dtoCreationVersBase(final CreationMatchDto matchDto) {
        Match match = new Match();
        match.setInscritA(inscritMapper.dtoVersBaseId(matchDto.getInscritA()));
        match.setInscritB(inscritMapper.dtoVersBaseId(matchDto.getInscritB()));
        match.setEvenement(evenementMapper.dtoVersBase(matchDto.getEvenement()));
        match.setPoule(pouleMapper.dtoVersBaseId(matchDto.getPoule()));
        match.setTimer(matchDto.getTimer());
//        match.setTags(matchDto.getTags().stream().map(Tag::getId).toList()); TODO
        match.setRuleset(this.rulesetMapper.dtoVersBase(matchDto.getRuleset()));
        return match;
    }

}

