package org.amhe.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.Match;
import org.amhe.models.MatchExpo;
import org.amhe.models.Tag;
import org.amhe.repos.TagRepo;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MatchMapper {
    @Inject
    RulesetMapper rulesetMapper;

    @Inject
    TagRepo tagRepo;

    public List<Match> listeExpoVersBase(final List<MatchExpo> matchsExpo) {
        List<Match> matchs = new ArrayList<>();
        matchsExpo.forEach(matchExpo ->
                matchs.add(this.expoVersBase(matchExpo))
        );
        return matchs;
    }

    public List<MatchExpo> listeBaseVersExpo(final List<Match> matchs) {
        List<MatchExpo> matchsExpo = new ArrayList<>();
        matchs.forEach(match ->
                matchsExpo.add(this.baseVersExpo(match))
        );
        return matchsExpo;
    }

    public Match expoVersBase(final MatchExpo matchExpo) {
        Match match = new Match();
        match.setId(matchExpo.getId());
        match.setInfosA(matchExpo.getInfosA());
        match.setInfosB(matchExpo.getInfosB());
        match.setCouleurA(matchExpo.getCouleurA());
        match.setCouleurB(matchExpo.getCouleurB());
        match.setScoreA(matchExpo.getScoreA());
        match.setScoreB(matchExpo.getScoreB());
        match.setTimer(matchExpo.getTimer());
        match.setDateCreation(matchExpo.getDateCreation());
        match.setDateDebut(matchExpo.getDateDebut());
        match.setDateFin(matchExpo.getDateFin());
        match.setStatut(matchExpo.getStatut());
        match.setTags(matchExpo.getTags().stream().map(Tag::getId).toList());
        match.setRuleset(this.rulesetMapper.expoVersBase(matchExpo.getRuleset()));
        return match;
    }

    public MatchExpo baseVersExpo(final Match match) {
        MatchExpo matchExpo = new MatchExpo();
        matchExpo.setId(match.getId());
        matchExpo.setInfosA(match.getInfosA());
        matchExpo.setInfosB(match.getInfosB());
        matchExpo.setCouleurA(match.getCouleurA());
        matchExpo.setCouleurB(match.getCouleurB());
        matchExpo.setScoreA(match.getScoreA());
        matchExpo.setScoreB(match.getScoreB());
        matchExpo.setDateCreation(match.getDateCreation());
        matchExpo.setDateDebut(match.getDateDebut());
        matchExpo.setDateFin(match.getDateFin());
        matchExpo.setStatut(match.getStatut());
        matchExpo.setTimer(match.getTimer());
        matchExpo.setTags(tagRepo.getListTags(match.getTags()));
        matchExpo.setRuleset(this.rulesetMapper.baseVersExpo(match.getRuleset()));
        return matchExpo;
    }

}

