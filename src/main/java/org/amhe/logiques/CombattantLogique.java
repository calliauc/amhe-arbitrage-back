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

        Set<String> setCiblesString = coupsMarques.stream().map(coup -> coup.getCible().getCode()).collect(Collectors.toSet());
        detailsCoupsMarques.setCibles(calculerDetailCoupCibleElem(setCiblesString, coupsMarques, detailsCoupsMarques.getTotal()));

        Set<String> setVulnerantsString = coupsMarques.stream().map(coup -> coup.getVulnerant().getCode()).collect(Collectors.toSet());
        detailsCoupsMarques.setVulnerants(calculerDetailCoupVulnerantElem(setVulnerantsString, coupsMarques, detailsCoupsMarques.getTotal()));

        detailsCoupsMarques.setDetails(calculerDetailCoupDetailsElem(details, coupsMarques, detailsCoupsMarques.getTotal()));
        return detailsCoupsMarques;
    }

    protected CombattantDetailsCoups calculerCoupsSubis(final CombattantDetails details, final List<Coup> coups) {
        List<Coup> coupsSubis = coups.stream()
                .filter(coup -> !Objects.equals(coup.getAttaquant().getId(), details.getCombattant().getId()))
                .toList();
        CombattantDetailsCoups detailsCoupsSubis = new CombattantDetailsCoups();
        detailsCoupsSubis.setTotal(coupsSubis.size());

        Set<String> setCiblesString = coupsSubis.stream().map(coup -> coup.getCible().getCode()).collect(Collectors.toSet());
        detailsCoupsSubis.setCibles(calculerDetailCoupCibleElem(setCiblesString, coupsSubis, detailsCoupsSubis.getTotal()));

        Set<String> setVulnerantsString = coupsSubis.stream().map(coup -> coup.getVulnerant().getCode()).collect(Collectors.toSet());
        detailsCoupsSubis.setVulnerants(calculerDetailCoupVulnerantElem(setVulnerantsString, coupsSubis, detailsCoupsSubis.getTotal()));

        detailsCoupsSubis.setDetails(calculerDetailCoupDetailsElem(details, coupsSubis, detailsCoupsSubis.getTotal()));
        return detailsCoupsSubis;
    }


    protected Set<DetailsCoupsListeElem> calculerDetailCoupCibleElem(
            final Set<String> setElemString,
            final List<Coup> coups,
            final float total
    ) {
        return setElemString.stream().map(elem -> {
            long nbElem = coups.stream().filter(coup -> Objects.equals(coup.getCible().getCode(), elem)).count();
            return new DetailsCoupsListeElem(
                    elem,
                    (int) nbElem,
                    pourcent(total, (float) nbElem));
        }).collect(Collectors.toSet());
    }

    protected Set<DetailsCoupsListeElem> calculerDetailCoupVulnerantElem(
            final Set<String> setElemString,
            final List<Coup> coups,
            final float total
    ) {
        return setElemString.stream().map(elem -> {
            long nbElem = coups.stream().filter(coup -> Objects.equals(coup.getVulnerant().getCode(), elem)).count();
            return new DetailsCoupsListeElem(
                    elem,
                    (int) nbElem,
                    pourcent(total, (float) nbElem));
        }).collect(Collectors.toSet());
    }

    protected Set<DetailsCoupsListeElem> calculerDetailCoupDetailsElem(
            final CombattantDetails details,
            final List<Coup> coups,
            final float total
    ) {
        long nbSimu = coups.stream().filter(Coup::isSimultanee).count();
        DetailsCoupsListeElem elemSimultane = new DetailsCoupsListeElem("simultane", (int) nbSimu, pourcent(total, nbSimu));

        long nbDoubleAtk = coups.stream().filter(Coup::isDoubleAtk).count();
        DetailsCoupsListeElem elemDoubleAtk = new DetailsCoupsListeElem("double atk", (int) nbDoubleAtk, pourcent(total, nbDoubleAtk));

        long nbDoubleDef = coups.stream().filter(Coup::isDoubleDef).count();
        DetailsCoupsListeElem elemDoubleDef = new DetailsCoupsListeElem("double def", (int) nbDoubleDef, pourcent(total, nbDoubleDef));

        long nbAfterblow = coups.stream().filter(Coup::isAfterblow).count();
        DetailsCoupsListeElem elemAfterblow = new DetailsCoupsListeElem("afterblow", (int) nbAfterblow, pourcent(total, nbAfterblow));

        long nbClair = coups.stream().filter(
                coup -> !coup.isSimultanee()
                        && !coup.isDoubleAtk()
                        && !coup.isDoubleDef()
                        && !coup.isAfterblow()
        ).count();
        DetailsCoupsListeElem elemClair = new DetailsCoupsListeElem("clair", (int) nbClair, pourcent(total, nbClair));

        return new HashSet<>(Arrays.asList(elemSimultane, elemDoubleAtk, elemDoubleDef, elemAfterblow, elemClair));
    }

    protected boolean isA(final CombattantDetails details, final MatchExpo match) {
        return Objects.equals(details.getCombattant().getId(), match.getInfosA().getId());
    }

    protected float pourcent(final float total, final float partion) {
        return (partion / total) * 100F;
    }
}
