package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.amhe.models.entites.Inscrit;
import org.amhe.models.entites.Match;
import org.amhe.models.entites.Poule;
import org.amhe.models.trier.CriteresRechercheMatchs;
import org.amhe.models.trier.TagsFiltres;
import org.amhe.repos.MatchRepo;

import java.util.*;

@Slf4j
@ApplicationScoped
public class MatchLogique {
    @Inject
    MatchRepo matchRepo;

    @Inject
    PouleLogique pouleLogique;

    @Inject
    InscritLogique inscritLogique;

    // OBTENTION
    public List<Match> getMatchs() {
        return matchRepo.getMatchs();
    }

    public Match getMatchById(final Long id) {
        return matchRepo.getMatchById(id);
    }

    public List<Match> getMatchsByEvent(final Long eventId) {
        return matchRepo.getMatchsByEvent(eventId);
    }


    public List<Match> rechercheMatchsParCriteres(final CriteresRechercheMatchs criteres) {
        List<Match> matchs = matchRepo.rechercheMatchsParCriteres(criteres);
        if (Objects.nonNull(criteres.getIdTags()) && !criteres.getIdTags().isEmpty()) {
            Set<Long> tagsId = new HashSet<>(criteres.getIdTags());
            matchs = matchs.stream()
                    .filter(match -> {
                        Set<Long> tagsMId = new HashSet<>(match.getTags());
                        return tagsMId.containsAll(tagsId);
                    })
                    .toList();
        }
        return matchs;
    }

    // CREATION
    public Match creerMatch(final Match nouveauMatch) {
        nouveauMatch.setStatut("nouveau");
        return matchRepo.createMatch(nouveauMatch);
    }

    public List<Match> genererMatchsPoule(final Long idPoule) {
        Poule poule = pouleLogique.getPouleById(idPoule);
        pouleLogique.modifierPoule(poule);
        List<Inscrit> inscrits = new ArrayList<>(poule.getInscrits());
        List<Match> matchs = new ArrayList<>();
        for (int i = 1; i < inscrits.size() + inscrits.size() % 2; ++i) {
            List<Inscrit> inscritsTour = new ArrayList<>(inscrits);
            while (inscritsTour.size() >= 2) {
                Match match = new Match();
                match.setInscritA(inscritsTour.removeFirst());
                match.setInscritB(inscritsTour.removeLast());
                match = matchRepo.createMatch(match);
                matchs.add(match);
            }
            inscrits = inscritLogique.rotationInscrits(inscrits);
        }
        if (Objects.nonNull(poule.getMatchs()))
            poule.getMatchs().forEach(match -> matchRepo.deleteMatch(match.getId()));
        poule.setMatchs(new HashSet<>(matchs));
        pouleLogique.modifierPoule(poule);
        return matchs;
    }

    // MODIFICATION
    public Match modifierMatch(final Long id, final Match matchExistant) {
        return matchRepo.editMatch(id, matchExistant);
    }

    // SUPPRESSION
    public void supprimerMatch(final Long id) {
        matchRepo.deleteMatch(id);
    }


    public List<String[]> getMatchsCsvReady() {
        List<String[]> matchsCsvReady = new ArrayList<>();
        List<Match> matchs = matchRepo.getMatchs();
        matchsCsvReady.add(new String[]{"Fighter 1", "Fighter 2", "Fighter 1 result", "Fighter 2 result", "Round"});
        matchs.stream()
                .filter(match -> Objects.equals(match.getStatut(), "fini"))
                .map(match -> match)
                .map(this::creerResultatMatch)
                .forEach(matchsCsvReady::add);
        return matchsCsvReady;
    }

    protected String[] creerResultatMatch(Match match) {
        String a = match.getInscritA().getIndividu().getNom() + " " + match.getInscritA().getIndividu().getPrenom();
        String b = match.getInscritB().getIndividu().getNom() + " " + match.getInscritB().getIndividu().getPrenom();
        String resultA;
        String resultB;
        if (match.getScoreA() == match.getScoreB()) {
            resultA = "Draw";
            resultB = "Draw";
        } else {
            resultA = match.getScoreA() > match.getScoreB() ? "Win" : "Loss";
            resultB = match.getScoreA() < match.getScoreB() ? "Win" : "Loss";
        }
//        String tags = match.getTags().stream().map(Tag::getCode).collect(Collectors.joining(","));
//        return new String[]{a, b, resultA, resultB, tags};
        return new String[]{a, b, resultA, resultB};
    }

    public List<Match> getMatchsParInscritIdEtTagsFiltres(final Long id, final TagsFiltres tagsFiltres) {
        List<Match> matchs = getMatchs();
//        matchs = filtrerMatchsParTagsFiltres(tagsFiltres, matchs);
//        matchs = filtrerMatchsParInscritId(id, matchs);
        return matchs;
    }

    public List<Match> filtrerMatchsParTagsFiltres(final TagsFiltres tagsFiltres, final List<Match> matchs) {
        // Filtrage des tags exclu
        // Filtrage des tags obligatoires
        // Filtrage au moins un des tags optionnels
        return matchs.stream().filter(match -> tagsFiltres.getTagsExclus().isEmpty() || Collections.disjoint(match.getTags(), tagsFiltres.getTagsExclus()))
                .filter(match -> tagsFiltres.getTagsRequis().isEmpty() || new HashSet<>(match.getTags()).containsAll(tagsFiltres.getTagsRequis()))
                .filter(match -> tagsFiltres.getTagsOptions().isEmpty() || !Collections.disjoint(match.getTags(), tagsFiltres.getTagsOptions()))
                .toList();
    }

    public List<Match> filtrerMatchsParInscritId(final Long id, final List<Match> matchs) {
        return matchs.stream()
                .filter(match ->
                        Objects.equals(match.getInscritA().getId(), id) || Objects.equals(match.getInscritB().getId(), id))
                .toList();
    }


}