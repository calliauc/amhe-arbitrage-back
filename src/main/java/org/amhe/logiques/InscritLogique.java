package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.MatchDto;
import org.amhe.models.entites.Coup;
import org.amhe.models.entites.Inscrit;
import org.amhe.models.trier.DetailsCoupsListeElem;
import org.amhe.models.trier.InscritDetails;
import org.amhe.models.trier.InscritDetailsCoups;
import org.amhe.models.trier.TagsFiltres;
import org.amhe.repos.CoupRepo;
import org.amhe.repos.InscritRepo;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class InscritLogique {
    @Inject
    InscritRepo inscritRepo;

    @Inject
    MatchLogique matchLogique;

    @Inject
    CoupRepo coupRepo;


    public List<Inscrit> getInscrits() {
        return inscritRepo.getInscrits();
    }

    public Inscrit getInscritById(final Long id) {
        return inscritRepo.getInscritById(id);
    }


    public List<Inscrit> chercherInscritsParNom(final String nom) {
        return inscritRepo.chercherInscritsParNom(nom);
    }

    public InscritDetails getDetailsInscrit(final Long id, final TagsFiltres tagsFiltres) {
        InscritDetails details = getDatasDetailsInscrit(id, tagsFiltres);
        details = calculerScoresInscrit(details);
        details = calculerCoupsInscrit(details);
        return details;
    }

    public Set<Inscrit> getInscritsByMatchTags(final List<Long> tags) {
        return matchLogique.getMatchs().stream()
                .filter(match -> new HashSet<>(match.getTags()).containsAll(tags))
                .map(match -> Arrays.asList(match.getInscritA(), match.getInscritB()))
                .flatMap(Collection::stream).collect(Collectors.toSet());
    }

    public Inscrit creerInscrit(final Inscrit nouveauInscrit) {
        return inscritRepo.createInscrit(nouveauInscrit);
    }

    public Inscrit modifierInscrit(final Inscrit inscrit) {
        return inscritRepo.editInscrit(inscrit);
    }

    public void supprimerInscrit(final Long id) {
        inscritRepo.deleteInscrit(id);
    }

    public List<Inscrit> rotationInscrits(List<Inscrit> inscrits) {
        Inscrit i = inscrits.removeLast();
        inscrits.add(inscrits.size() % 2, i);
        return inscrits;
    }


    // INTERNES

    protected InscritDetails getDatasDetailsInscrit(final Long id, final TagsFiltres tagsFiltres) {
        InscritDetails details = new InscritDetails();
        details.setInscrit(inscritRepo.getInscritById(id));
        details.setTags(tagsFiltres);
//        details.setMatchs(matchLogique.getMatchsParInscritIdEtTagsFiltres(id, tagsFiltres));
        return details;
    }

    protected InscritDetails calculerScoresInscrit(InscritDetails details) {
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

    protected InscritDetails calculerCoupsInscrit(InscritDetails details) {
        List<Coup> coups = new ArrayList<>();
        details.getMatchs().forEach(match -> coups.addAll(coupRepo.getCoupsByMatchId(match.getId())));
        details.setCoupsMarques(calculerCoupsMarques(details, coups));
        details.setCoupsSubis(calculerCoupsSubis(details, coups));
        return details;
    }

    protected InscritDetailsCoups calculerCoupsMarques(final InscritDetails details, final List<Coup> coups) {
        List<Coup> coupsMarques = coups.stream()
                .filter(coup -> Objects.equals(coup.getAttaquant().getId(), details.getInscrit().getId()))
                .toList();
        InscritDetailsCoups detailsCoupsMarques = new InscritDetailsCoups();
        detailsCoupsMarques.setTotal(coupsMarques.size());

        Set<String> setCiblesString = coupsMarques.stream().map(coup -> coup.getCible().getCode()).collect(Collectors.toSet());
        detailsCoupsMarques.setCibles(calculerDetailCoupCibleElem(setCiblesString, coupsMarques, detailsCoupsMarques.getTotal()));

        Set<String> setVulnerantsString = coupsMarques.stream().map(coup -> coup.getVulnerant().getCode()).collect(Collectors.toSet());
        detailsCoupsMarques.setVulnerants(calculerDetailCoupVulnerantElem(setVulnerantsString, coupsMarques, detailsCoupsMarques.getTotal()));

        detailsCoupsMarques.setDetails(calculerDetailCoupDetailsElem(details, coupsMarques, detailsCoupsMarques.getTotal()));
        return detailsCoupsMarques;
    }

    protected InscritDetailsCoups calculerCoupsSubis(final InscritDetails details, final List<Coup> coups) {
        List<Coup> coupsSubis = coups.stream()
                .filter(coup -> !Objects.equals(coup.getAttaquant().getId(), details.getInscrit().getId()))
                .toList();
        InscritDetailsCoups detailsCoupsSubis = new InscritDetailsCoups();
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
            final InscritDetails details,
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

    protected boolean isA(final InscritDetails details, final MatchDto match) {
        return Objects.equals(details.getInscrit().getId(), match.getInscritA().getId());
    }

    protected float pourcent(final float total, final float partion) {
        return (partion / total) * 100F;
    }
}
