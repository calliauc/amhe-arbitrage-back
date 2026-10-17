package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.entites.Evenement;
import org.amhe.models.entites.Poule;
import org.amhe.repos.EvenementRepo;
import org.amhe.repos.InscritRepo;
import org.amhe.repos.PouleRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PouleLogique {
    @Inject
    PouleRepo pouleRepo;

    @Inject
    EvenementRepo evenementRepo;

    @Inject
    InscritRepo inscritRepo;

    public List<Poule> getPoules() {
        return pouleRepo.getPoules();
    }

    public Poule getPouleById(final Long id) {
        return pouleRepo.getPouleById(id);
    }

    public List<Poule> getPouleByEvent(final Long id) {
        return pouleRepo.getPoulesByEvent(id);
    }

    public Poule creerPoule(final Poule nouvellePoule) {
        return pouleRepo.createPoule(nouvellePoule);
    }

    public Poule modifierPoule(final Poule poule) {
        return pouleRepo.editPoule(poule);
    }

    public void supprimerPoule(final Long id) {
        pouleRepo.deletePoule(id);
    }


    public Poule inscrireALaPoule(final Long id, final List<Long> inscritsId) {
        Poule poule = pouleRepo.getPouleById(id);
        Evenement event = evenementRepo.getEvenementById(poule.getEvenement().getId());
        poule.getInscrits().addAll(inscritsId.stream()
                .map(inscritId -> inscritRepo.getInscritById(inscritId))
                .filter(inscrit -> Objects.nonNull(inscrit) && Objects.equals(inscrit.getEvenement().getId(), event.getId()))
                .toList());
        return pouleRepo.editPoule(poule);
    }

    public List<Poule> updateInscritsPoules(final List<Poule> poulesInput) {
        List<Poule> poulesOutput = new ArrayList<>();
        poulesInput.forEach(poule -> {
            Poule p = pouleRepo.getPouleById(poule.getId());
            if (Objects.nonNull(poule.getInscrits())) {
                p.setInscrits(poule.getInscrits());
            } else {
                p.setInscrits(new HashSet<>());
            }
            pouleRepo.editPoule(p);
            poulesOutput.add(p);
        });
        return poulesOutput;
    }

    public List<Poule> getPoulesByEvent(final Long eventId) {
        return pouleRepo.getPoulesByEvent(eventId);
    }
}
