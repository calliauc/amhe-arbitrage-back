package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.*;
import org.amhe.repos.CombattantRepo;
import org.amhe.repos.CoupRepo;
import org.amhe.repos.MatchRepo;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class CombattantLogique {
    @Inject
    MatchRepo matchRepo;

    @Inject
    CoupRepo coupRepo;

    @Inject
    MatchLogique matchLogique;

    @Inject
    CombattantRepo combattantRepo;

    public Set<Combattant> getCombattantsByMatchTags(final List<Long> tags) {
        return matchRepo.getMatchs().stream()
                .filter(match -> new HashSet<>(match.getTags()).containsAll(tags))
                .map(match -> Arrays.asList(match.getInfosA(), match.getInfosB()))
                .flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public CombattantDetails getDetailsCombattant(final Long id, final TagsFiltres tagsFiltres) {
        CombattantDetails details = getDatasDetailsCombattant(id, tagsFiltres);
        details = calculerScoresCombattant(details);
        details = calculerCoupsCombattant(details);
        return details;
    }

    protected CombattantDetails getDatasDetailsCombattant(final Long id, final TagsFiltres tagsFiltres) {
        CombattantDetails details = new CombattantDetails();
        details.setCombattant(combattantRepo.getCombattantById(id));
        details.setTags(tagsFiltres);
        details.setMatchs(matchLogique.getMatchsParCombattantIdEtTagsFiltres(id, tagsFiltres));
        return details;
    }

    protected CombattantDetails calculerScoresCombattant(CombattantDetails details) {
        details.setPointsMarques(0);
        details.setPointsPerdus(0);
        details.getMatchs().forEach(match -> {
            if (isA(details, match)) {
                details.setPointsMarques(details.getPointsMarques() + match.getScoreA());
                details.setPointsPerdus(details.getPointsPerdus() + match.getScoreB());
            } else {
                details.setPointsMarques(details.getPointsMarques() + match.getScoreB());
                details.setPointsPerdus(details.getPointsPerdus() + match.getScoreA());
            }
        });
        return details;
    }

    protected CombattantDetails calculerCoupsCombattant(CombattantDetails details) {
        List<Coup> coups = new ArrayList<>();
        details.getMatchs().forEach(match -> coups.addAll(coupRepo.getCoupsByMatchId(match.getId())));
        details.setCoupsMarques(calculerCoupsMarques(details, coups));
        details.setCoupsSubis(calculerCoupsSubis(details, coups));
        return details;
    }

    protected CombattantDetailsCoups calculerCoupsMarques(final CombattantDetails details, final List<Coup> coups) {
        List<Coup> coupsMarques = coups.stream()
                .filter(coup -> Objects.equals(coup.getAttaquant().getId(), details.getCombattant().getId()))
                .toList();
        CombattantDetailsCoups detailsCoupsMarques = new CombattantDetailsCoups();
        detailsCoupsMarques.setTotal(coupsMarques.size());

        return detailsCoupsMarques;
    }

    protected CombattantDetailsCoups calculerCoupsSubis(CombattantDetails details, List<Coup> coups) {
        List<Coup> coupsSubis = coups.stream()
                .filter(coup -> !Objects.equals(coup.getAttaquant().getId(), details.getCombattant().getId()))
                .toList();
        CombattantDetailsCoups detailsCoupsSubis = new CombattantDetailsCoups();
        detailsCoupsSubis.setTotal(coupsSubis.size());
        return detailsCoupsSubis;
    }


    protected boolean isA(final CombattantDetails details, final MatchExpo match) {
        return Objects.equals(details.getCombattant().getId(), match.getInfosA().getId());
    }
}
