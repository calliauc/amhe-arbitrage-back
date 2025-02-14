package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.amhe.mappers.MatchMapper;
import org.amhe.models.Match;
import org.amhe.models.MatchExpo;
import org.amhe.models.Tag;
import org.amhe.models.TagsFiltres;
import org.amhe.repos.MatchRepo;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
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

//    public List<MatchExpo> getMatchsParTagsFiltres(final TagsFiltres tagsFiltres) {
//        List<MatchExpo> matchs = matchMapper.listeBaseVersExpo(matchRepo.getMatchs());
//        // Filtrage aucun tag exclu
//        // Filtrage tout les tags obligatoires
//        // Filtrage au moins un des tags optionnels
//        matchs = matchs.stream().filter(match -> Collections.disjoint(match.getTags(), tagsFiltres.getTagsExclus()))
//                .filter(match -> match.getTags().containsAll(tagsFiltres.getTagsRequis()))
//                .filter(match -> !Collections.disjoint(match.getTags(), tagsFiltres.getTagsOptions()))
//                .toList();
//        return matchs;
//    }

    public List<MatchExpo> getMatchsExpo() {
        return matchMapper.listeBaseVersExpo(matchRepo.getMatchs());
    }

    public List<MatchExpo> getMatchsParCombattantIdEtTagsFiltres(final Long id, final TagsFiltres tagsFiltres) {
        List<MatchExpo> matchs = getMatchsExpo();
        matchs = filtrerMatchsParTagsFiltres(tagsFiltres, matchs);
        matchs = filtrerMatchsParCombattantId(id, matchs);
        return matchs;
    }

    public List<MatchExpo> filtrerMatchsParTagsFiltres(final TagsFiltres tagsFiltres, final List<MatchExpo> matchs) {
        // Filtrage des tags exclu
        // Filtrage des tags obligatoires
        // Filtrage au moins un des tags optionnels
        return matchs.stream().filter(match -> tagsFiltres.getTagsExclus().isEmpty() || Collections.disjoint(match.getTags(), tagsFiltres.getTagsExclus()))
                .filter(match -> tagsFiltres.getTagsRequis().isEmpty() || new HashSet<>(match.getTags()).containsAll(tagsFiltres.getTagsRequis()))
                .filter(match -> tagsFiltres.getTagsOptions().isEmpty() || !Collections.disjoint(match.getTags(), tagsFiltres.getTagsOptions()))
                .toList();
    }

    public List<MatchExpo> filtrerMatchsParCombattantId(final Long id, final List<MatchExpo> matchs) {
        return matchs.stream()
                .filter(match ->
                        Objects.equals(match.getInfosA().getId(), id) || Objects.equals(match.getInfosB().getId(), id))
                .toList();
    }
}