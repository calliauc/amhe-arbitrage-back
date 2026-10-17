package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.MatchDto;
import org.amhe.models.entites.Match;
import org.amhe.repos.TagRepo;

import java.util.*;

@ApplicationScoped
public class MatchMapper {
    @Inject
    InscritMapper inscritMapper;

    @Inject
    RulesetMapper rulesetMapper;

    @Inject
    TagRepo tagRepo;

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
        match.setInscritA(inscritMapper.dtoVersBase(matchDto.getInscritA()));
        match.setInscritB(inscritMapper.dtoVersBase(matchDto.getInscritB()));
        match.setScoreA(matchDto.getScoreA());
        match.setScoreB(matchDto.getScoreB());
        match.setTimer(matchDto.getTimer());
        match.setDateCreation(matchDto.getDateCreation());
        match.setDateDebut(matchDto.getDateDebut());
        match.setDateFin(matchDto.getDateFin());
        match.setStatut(matchDto.getStatut());
//        match.setTags(matchDto.getTags().stream().map(Tag::getId).toList()); TODO
//        match.setRuleset(this.rulesetMapper.expoVersBase(matchDto.getRuleset()));
        return match;
    }

    public MatchDto baseVersDto(final Match match) {
        MatchDto matchDto = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setInscritA(inscritMapper.baseVersDto(match.getInscritA()));
        matchDto.setInscritB(inscritMapper.baseVersDto(match.getInscritB()));
        matchDto.setScoreA(match.getScoreA());
        matchDto.setScoreB(match.getScoreB());
        matchDto.setDateCreation(match.getDateCreation());
        matchDto.setDateDebut(match.getDateDebut());
        matchDto.setDateFin(match.getDateFin());
        matchDto.setStatut(match.getStatut());
        matchDto.setTimer(match.getTimer());
//        matchDto.setTags(tagRepo.getListTags(match.getTags()));   TODO
//        matchDto.setRuleset(this.rulesetMapper.baseVersDto(match.getRuleset()));
        return matchDto;
    }

}

