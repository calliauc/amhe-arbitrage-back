package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.entites.*;
import org.amhe.repos.EvenementRepo;
import org.amhe.repos.IndividuRepo;
import org.amhe.repos.InscritRepo;

import java.util.List;

@ApplicationScoped
public class EvenementLogique {
    @Inject
    EvenementRepo evenementRepo;

    @Inject
    IndividuRepo individuRepo;

    @Inject
    MatchLogique matchLogique;

    @Inject
    InscritRepo inscritRepo;

    @Inject
    PouleLogique pouleLogique;

    public List<Evenement> getEvenements() {
        return evenementRepo.getEvenements();
    }

    public Evenement getEvenementById(final Long id) {
        return evenementRepo.getEvenementById(id);
    }

    public Evenement creerEvenement(final Evenement evenementACreer) {
        return evenementRepo.createEvenement(evenementACreer);
    }

    public Evenement modifierEvenement(final Evenement evenementAModifier) {
        return evenementRepo.editEvenement(evenementAModifier);
    }

    public void supprimerEvenement(final Long id) {
        gererOrphelins(id);
        evenementRepo.deleteEvenement(id);
    }

    private void gererOrphelins(final Long id) {
        Evenement event = evenementRepo.getEvenementById(0L);
        List<Match> matchs = matchLogique.getMatchsByEvent(id);
        matchs.forEach(match -> {
            match.setEvenement(event);
            matchLogique.modifierMatch(match.getId(), match);
        });
        List<Inscrit> inscrits = inscritRepo.getInscritsByEvent(id);
        inscrits.forEach(inscrit -> {
            inscrit.setEvenement(event);
            inscritRepo.editInscrit(inscrit);
        });
        List<Poule> poules = pouleLogique.getPoulesByEvent(id);
        poules.forEach(poule -> {
            poule.setEvenement(event);
            pouleLogique.modifierPoule(poule);
        });
        // Autres ?

    }

    public Evenement inscrireAEvenement(final Long id, final List<Long> individus) {
        Evenement event = evenementRepo.getEvenementById(id);
        List<Inscrit> inscrits = individus.stream().map(indId -> {
            Individu ind = individuRepo.getIndividuById(indId);
            Inscrit nouvelInscrit = new Inscrit(event, ind);
            return inscritRepo.createInscrit(nouvelInscrit);
        }).toList();
        event.getInscrits().addAll(inscrits);

        return evenementRepo.editEvenement(event);
    }
}
