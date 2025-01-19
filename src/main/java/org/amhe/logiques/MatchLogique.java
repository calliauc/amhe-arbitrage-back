package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.amhe.models.Match;
import org.amhe.repos.MatchRepo;

import java.util.Objects;

@Slf4j
@ApplicationScoped
public class MatchLogique {
    @Inject
    MatchRepo matchRepo;

    public Match modifiePartiellementMatch(final Long id, final Match matchAModifier) {
        Match matchBase = matchRepo.getMatchById(id);
        Match matchModifie = this.mapPartiellementMatch(matchAModifier, matchBase);
        return matchRepo.editMatch(id, matchModifie);
    }

    protected Match mapPartiellementMatch(final Match matchAModifier, Match matchBase) {
        if (Objects.nonNull(matchAModifier.getInfosA())) {
            matchBase.setInfosB(matchAModifier.getInfosA());
        }
        if (Objects.nonNull(matchAModifier.getInfosB())) {
            matchBase.setInfosB(matchAModifier.getInfosB());
        }
        if (Objects.nonNull(matchAModifier.getRuleset())) {
            matchBase.setRuleset(matchAModifier.getRuleset());
        }
        matchBase.setScoreA(matchAModifier.getScoreA());
        matchBase.setScoreB(matchAModifier.getScoreB());
        matchBase.setTimer(matchAModifier.getTimer());
        return matchBase;
    }
}
