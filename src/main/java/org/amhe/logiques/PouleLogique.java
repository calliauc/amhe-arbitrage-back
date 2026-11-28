package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.mappers.InscritMapper;
import org.amhe.models.entites.Evenement;
import org.amhe.models.entites.Inscrit;
import org.amhe.models.entites.Match;
import org.amhe.models.entites.Poule;
import org.amhe.repos.EvenementRepo;
import org.amhe.repos.InscritRepo;
import org.amhe.repos.PouleRepo;

import java.util.*;

@ApplicationScoped
public class PouleLogique {
    @Inject
    PouleRepo repo;

    @Inject
    EvenementRepo evenementRepo;

    @Inject
    InscritRepo inscritRepo;

    @Inject
    InscritMapper inscritMapper;

    public List<Poule> getPoules() {
        return repo.getPoules();
    }

    public Poule getPouleById(final Long id) {
        return repo.getPouleById(id);
    }

    public List<Poule> getPouleByEvent(final Long id) {
        return repo.getPoulesByEvent(id);
    }

    public Poule creerPoule(final Poule nouvellePoule) {
        return repo.createPoule(nouvellePoule);
    }

    public Poule modifierPoule(final Poule poule) {
        return repo.editPoule(poule);
    }

    public void supprimerPoule(final Long id) {
        repo.deletePoule(id);
    }

    public Poule inscrireALaPoule(final Long id, final List<Long> inscritsId) {
        Poule poule = repo.getPouleById(id);
        Evenement event = evenementRepo.getEvenementById(poule.getEvenement().getId());
        poule.getInscrits().addAll(inscritsId.stream()
                .map(inscritId -> inscritRepo.getInscritById(inscritId))
                .filter(inscrit -> Objects.nonNull(inscrit) && Objects.equals(inscrit.getEvenement().getId(), event.getId()))
                .toList());
        return repo.editPoule(poule);
    }

    public List<Poule> updateInscritsPoules(final List<Poule> poulesInput) {
        List<Poule> poulesOutput = new ArrayList<>();
        poulesInput.forEach(poule -> {
            Poule p = repo.getPouleById(poule.getId());
            if (Objects.nonNull(poule.getInscrits())) {
                p.setInscrits(poule.getInscrits());
            } else {
                p.setInscrits(new ArrayList<>());
            }
            repo.editPoule(p);
            poulesOutput.add(p);
        });
        return poulesOutput;
    }

    public List<Poule> getPoulesByEvent(final Long eventId) {
        return repo.getPoulesByEvent(eventId);
    }

    public List<Map.Entry<String, Integer>> getScores(final Long pouleId) {
        List<Match> matchs = repo.getPouleById(pouleId).getMatchs();
        Map<Inscrit, Integer> scoreCompute = new HashMap<>();
        try {
            matchs.stream().filter(match -> "fini".equalsIgnoreCase(match.getStatut())).forEach(match -> {
                scoreCompute.merge(match.getInscritA(), match.getScoreA(), (v, vv) -> v + vv);
                scoreCompute.merge(match.getInscritB(), match.getScoreB(), (v, vv) -> v + vv);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Map<String, Integer> scores = new HashMap<>();
        List<Map.Entry<String, Integer>> test = new ArrayList<>();
        if (!scoreCompute.isEmpty()) {
            scoreCompute.forEach((key, value) -> scores.put(extractNom(key), value));
            test.addAll(scores.entrySet());
            test.sort(Comparator.comparingInt(Map.Entry::getValue));
        }
        return test;
    }

    private String extractNom(Inscrit i) {
        return i.getIndividu().getPrenom() + " " + i.getIndividu().getNom();
    }
}
