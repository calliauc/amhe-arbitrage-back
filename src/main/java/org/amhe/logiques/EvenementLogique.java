package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.mappers.InscritMapper;
import org.amhe.models.dtos.InscritDto;
import org.amhe.models.entites.Evenement;
import org.amhe.models.entites.Inscrit;
import org.amhe.models.entites.Match;
import org.amhe.models.entites.Poule;
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

    @Inject
    InscritMapper inscritMapper;

    public List<Evenement> getEvenements() {
        return evenementRepo.getEvenements();
    }

    public Evenement getEvenementById(final Long id) {
        return evenementRepo.getEvenementById(id);
    }

    public Evenement creerEvenement(final Evenement evenementACreer) {
        return evenementRepo.createEvenement(evenementACreer);
    }

    public Evenement modifierEvenement(final Long id, final Evenement evenementAModifier) {
        Evenement event = evenementRepo.getEvenementById(id);
        event.setNom(evenementAModifier.getNom());
        event.setDescription(evenementAModifier.getDescription());
        event.setDateDebut(evenementAModifier.getDateDebut());
        event.setDateFin(evenementAModifier.getDateFin());
        return evenementRepo.editEvenement(event);
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
            matchLogique.modifierMatch(match);
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

    public Evenement inscrireAEvenement(final Long id, final InscritDto inscriptionDto) {
        Evenement event = evenementRepo.getEvenementById(id);
        Inscrit inscription = inscritMapper.dtoVersBase(inscriptionDto);
        inscription = inscritRepo.createInscrit(inscription);
        event.getInscrits().add(inscription);
//        List<Inscrit> inscrits = individus.stream().map(indId -> {
//            Individu ind = individuRepo.getIndividuById(indId);
//            Inscrit nouvelInscrit = new Inscrit(event, ind);
//            return inscritRepo.createInscrit(nouvelInscrit);
//        }).toList();

        return evenementRepo.editEvenement(event);
    }
}
