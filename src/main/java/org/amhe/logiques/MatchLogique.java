package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.amhe.mappers.InscritMapper;
import org.amhe.models.dtos.MatchDto;
import org.amhe.models.dtos.MatchPartialUpdateDto;
import org.amhe.models.entites.Echange;
import org.amhe.models.entites.Inscrit;
import org.amhe.models.entites.Match;
import org.amhe.models.entites.Poule;
import org.amhe.models.trier.CriteresRechercheMatchs;
import org.amhe.repos.MatchRepo;
import org.amhe.repos.PouleRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@ApplicationScoped
public class MatchLogique {

    @Inject
    MatchRepo repo;
    @Inject
    PouleRepo pouleRepo;

    @Inject
    InscritLogique inscritLogique;
    @Inject
    PouleLogique pouleLogique;
    @Inject
    EchangeLogique echangeLogique;

    @Inject
    InscritMapper inscritMapper;

    // OBTENTION
    public List<Match> getMatchs() {
        return repo.getMatchs();
    }

    public Match getMatchById(final Long id) {
        return repo.getMatchById(id);
    }

    public List<Match> getMatchsByEvent(final Long eventId) {
        return repo.getMatchsByEvent(eventId);
    }


    public List<Match> rechercheMatchsParCriteres(final CriteresRechercheMatchs criteres) {
        List<Match> matchs = repo.rechercheMatchsParCriteres(criteres);
        return matchs;
    }

    // CREATION
    public Match creerMatch(final Match nouveauMatch) {
        nouveauMatch.setDateCreation(LocalDateTime.now());
        nouveauMatch.setStatut("nouveau");
        return repo.createMatch(nouveauMatch);
    }

    public List<Match> genererMatchsPoule(final Long idPoule) {
        Integer numCode = 1;
        Poule poule = pouleLogique.getPouleById(idPoule);
        List<Inscrit> inscrits = new ArrayList<>(poule.getInscrits());
        List<Match> matchs = new ArrayList<>();
        final Match base = creerMatchAuto(poule);
        for (int i = 1; i < inscrits.size() + inscrits.size() % 2; ++i) {
            List<Inscrit> inscritsTour = new ArrayList<>(inscrits);
            while (inscritsTour.size() >= 2) {
                Match match = base.copy();
                match.setCode(poule.getPrefixe().concat("-").concat(numCode.toString()));
                match.setInscritA(inscritsTour.removeFirst());
                match.setInscritB(inscritsTour.removeLast());
                match = creerMatch(match);
                matchs.add(match);
                numCode++;
            }
            inscrits = inscritLogique.rotationInscrits(inscrits);
        }
        if (Objects.nonNull(poule.getMatchs()))
            poule.getMatchs().forEach(match -> supprimerMatch(match.getId()));
        poule.setMatchs(new ArrayList<>(matchs));
        pouleLogique.modifierPoule(poule);
        return matchs;
    }

    private Match creerMatchAuto(final Poule poule) {
        Match match = new Match();
        match.setEvenement(poule.getEvenement());
        match.setPoule(poule);
        match.setRuleset(poule.getEvenement().getRuleset());
        match.setTags(poule.getTags());
        return match;
    }

    // MODIFICATION
    public Match modifierMatch(final Match matchExistant) {
        return repo.editMatch(matchExistant);
    }

    public Match updatePartialMatch(MatchPartialUpdateDto update) {
        Match match = repo.getMatchById(update.getId());
        if (Objects.nonNull(update.getScoreA())) {
            match.setScoreA(update.getScoreA());
        }
        if (Objects.nonNull(update.getScoreB())) {
            match.setScoreB(update.getScoreB());
        }
        if (Objects.nonNull(update.getTimer())) {
            match.setTimer(update.getTimer());
        }
        if (Objects.nonNull(update.getStatut())) {
            match.setStatut(update.getStatut());
        }
        if (Objects.nonNull(update.getDateDebut())) {
            match.setDateDebut(LocalDateTime.parse(update.getDateDebut()));
        }
        if (Objects.nonNull(update.getDateFin())) {
            match.setDateFin(LocalDateTime.parse(update.getDateFin()));
        }
        return repo.editMatch(match);
    }

    public void enregistrerFinMatch(final MatchDto matchDto) {
        Match match = repo.getMatchById(matchDto.getId());
        match.setMatchNull(matchDto.getMatchNull());
        if (Objects.nonNull(matchDto.getVainqueur()))
            match.setVainqueur(inscritLogique.getInscritById(matchDto.getVainqueur().getId()));
        else {
            match.setVainqueur(null);
        }
        match.setDateFin(LocalDateTime.now());
        repo.editMatch(match);
    }

    // SUPPRESSION
    public void supprimerMatch(final Long id) {
        Match match = getMatchById(id);
        if (Objects.nonNull(match.getPoule()) && Objects.nonNull(match.getPoule().getMatchs())) {
            match.getPoule().getMatchs().remove(match);
            pouleRepo.editPoule(match.getPoule());
        }

        List<Echange> echanges = echangeLogique.getEchangeByMatchId(id);
        if (Objects.nonNull(echanges)) {
            echanges.forEach(e -> echangeLogique.supprimerEchange(e.getId()));
        }

        repo.deleteMatch(match.getId());
    }


    public List<String[]> getMatchsCsvReady() {
        List<String[]> matchsCsvReady = new ArrayList<>();
        List<Match> matchs = repo.getMatchs();
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

    public List<Match> getMatchsParInscrit(final Inscrit inscrit) {
        CriteresRechercheMatchs criteres = new CriteresRechercheMatchs();
        criteres.setIdEvenement(inscrit.getEvenement().getId());
        criteres.setIdInscrit(inscrit.getId());
        return repo.rechercheMatchsParCriteres(criteres);
    }


}