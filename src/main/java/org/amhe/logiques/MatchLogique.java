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
        if (Objects.nonNull(matchAModifier.getBleu())) {
            matchBase.setBleu(matchAModifier.getBleu());
        }
        if (Objects.nonNull(matchAModifier.getRouge())) {
            matchBase.setRouge(matchAModifier.getRouge());
        }
        if (Objects.nonNull(matchAModifier.getTimerReverse())) {
            matchBase.setTimerReverse(matchAModifier.getTimerReverse());
        }
        matchBase.setScoreBleu(matchAModifier.getScoreBleu());
        matchBase.setScoreRouge(matchAModifier.getScoreRouge());
        matchBase.setTimerStart(matchAModifier.getTimerStart());
        matchBase.setTimerEnd(matchAModifier.getTimerEnd());
        return matchBase;
    }
}
