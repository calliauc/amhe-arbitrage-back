package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.mappers.InscritMapper;
import org.amhe.models.entites.Echange;
import org.amhe.models.entites.Inscrit;
import org.amhe.models.entites.Match;
import org.amhe.models.trier.InscritDetails;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StatsLogique {

    @Inject
    InscritMapper inscritMapper;

    @Inject
    MatchLogique matchLogique;

    @Inject
    EchangeLogique echangeLogique;

    @Inject
    ItemLogique itemLogique;

    public InscritDetails genererStatsInscrit(final Inscrit inscrit) {
        InscritDetails details = new InscritDetails();

        List<Match> matchs = matchLogique.getMatchsParInscrit(inscrit);
        List<Echange> echanges = matchs.stream()
                .flatMap(match -> echangeLogique.getEchangeByMatchId(match.getId())
                        .stream()).toList();
        List<String> noms = genererStatsEchanges(echanges, inscrit);

        details.setInscrit(inscritMapper.baseVersDto(inscrit));
        details.setNbMatchs(matchs.size());

        return new InscritDetails();
    }

    public List<String> genererStatsEchanges(List<Echange> echanges, Inscrit inscrit) {
//        inscrit.getEvenement().getRuleset().getTypesEchange().stream().map(typeId -> itemLogique.getItemById(typeId).getCode()).forEach(type -> );
        return statsDoubleTouches(new ArrayList<>(echanges).stream()
                .filter(echange -> echange.getType().estCode("double"))
                .toList());
    }

    public List<String> statsDoubleTouches(List<Echange> echanges) {
        List<String> test = echanges.stream().map(e -> e.getInscritA().getIndividu().getPrenom()).toList();
        return test;
    }

}
