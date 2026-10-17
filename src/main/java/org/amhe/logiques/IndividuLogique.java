package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.entites.Individu;
import org.amhe.repos.CoupRepo;
import org.amhe.repos.IndividuRepo;

import java.util.List;

@ApplicationScoped
public class IndividuLogique {
    @Inject
    IndividuRepo individuRepo;

    @Inject
    MatchLogique matchLogique;

    @Inject
    CoupRepo coupRepo;


    public List<Individu> getIndividus() {
        return individuRepo.getIndividus();
    }

    public Individu getIndividuById(final Long id) {
        return individuRepo.getIndividuById(id);
    }

    public List<Individu> chercherIndividusParNom(final String nom) {
        return individuRepo.chercherIndividusParNom(nom);
    }

    public Individu creerIndividu(final Individu nouveauIndividu) {
        return individuRepo.createIndividu(nouveauIndividu);
    }

    public Individu modifierIndividu(final Individu individu) {
        return individuRepo.editIndividu(individu);
    }

    public void supprimerIndividu(final Long id) {
        individuRepo.deleteIndividu(id);
    }

}