package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.Match;
import org.amhe.repos.MatchRepo;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MatchLogique {
    @Inject
    MatchRepo matchRepo;

    public List<String[]> getMatchsCsvReady() {
        List<String[]> matchsCsvReady = new ArrayList<>();
        List<Match> matchs = matchRepo.getMatchs();
        matchsCsvReady = matchs.stream().map(match -> {
            String a = match.getInfosA().getNom() + " " + match.getInfosA().getPrenom();
            String b = match.getInfosB().getNom() + " " + match.getInfosB().getPrenom();
            boolean resultA = match.getScoreA() > match.getScoreB();
            boolean resultB = match.getScoreA() < match.getScoreB();
            String[] matchData = {a, b, String.valueOf(resultA), String.valueOf(resultB)};
            return matchData;
        }).toList();
        return matchsCsvReady;
    }
}
