package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.mappers.MatchMapper;
import org.amhe.models.Match;
import org.amhe.models.MatchExpo;
import org.amhe.models.Tag;
import org.amhe.repos.MatchRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class MatchLogique {
    @Inject
    MatchRepo matchRepo;

    @Inject
    MatchMapper matchMapper;

    public List<String[]> getMatchsCsvReady() {
        List<String[]> matchsCsvReady = new ArrayList<>();
        List<Match> matchs = matchRepo.getMatchs();
        matchsCsvReady.add(new String[]{"Fighter 1", "Fighter 2", "Fighter 1 result", "Fighter 2 result", "Round"});
        matchs.stream()
                .filter(match -> Objects.equals(match.getStatut(), "fini"))
                .map(match -> matchMapper.baseVersExpo(match))
                .map(this::creerResultatMatch)
                .forEach(matchsCsvReady::add);
        return matchsCsvReady;
    }

    protected String[] creerResultatMatch(MatchExpo match) {
        String a = match.getInfosA().getPrenom() + " " + match.getInfosA().getNom();
        String b = match.getInfosB().getPrenom() + " " + match.getInfosB().getNom();
        String resultA;
        String resultB;
        if (match.getScoreA() == match.getScoreB()) {
            resultA = "Draw";
            resultB = "Draw";
        } else {
            resultA = match.getScoreA() > match.getScoreB() ? "Win" : "Loss";
            resultB = match.getScoreA() < match.getScoreB() ? "Win" : "Loss";
        }
        String tags = match.getTags().stream().map(Tag::getCode).collect(Collectors.joining(","));
        return new String[]{a, b, resultA, resultB, tags};
    }
}
