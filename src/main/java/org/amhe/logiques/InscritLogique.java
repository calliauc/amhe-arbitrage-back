package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.models.dtos.MatchDto;
import org.amhe.models.entites.Echange;
import org.amhe.models.entites.Inscrit;
import org.amhe.models.trier.DetailsCoupsListeElem;
import org.amhe.models.trier.InscritDetails;
import org.amhe.models.trier.InscritDetailsCoups;
import org.amhe.models.trier.TagsFiltres;
import org.amhe.repos.EchangeRepo;
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
    EchangeRepo echangeRepo;


    public List<Inscrit> getInscrits() {
        return inscritRepo.getInscrits();
    }

    public Inscrit getInscritById(final Long id) {
        return inscritRepo.getInscritById(id);
    }

    public List<Inscrit> getInscritsByEvent(final Long idEvent) {
        return inscritRepo.getInscritsByEvent(idEvent);
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
        List<Echange> echanges = new ArrayList<>();
        details.getMatchs().forEach(match -> echanges.addAll(echangeRepo.getEchangesByMatchId(match.getId())));
        details.setCoupsMarques(calculerCoupsMarques(details, echanges));
        details.setCoupsSubis(calculerCoupsSubis(details, echanges));
        return details;
    }

    protected InscritDetailsCoups calculerCoupsMarques(final InscritDetails details, final List<Echange> echanges) {
//        List<Echange> coupsMarques = echanges.stream()
//                .filter(echange -> Objects.equals(echange.getInscritA().getId(), details.getInscrit().getId()))
//                .toList();
//        InscritDetailsCoups detailsCoupsMarques = new InscritDetailsCoups();
//        detailsCoupsMarques.setTotal(coupsMarques.size());
//
//        Set<String> setCiblesString = coupsMarques.stream().map(echange -> echange.getCible().getCode()).collect(Collectors.toSet());
//        detailsCoupsMarques.setCibles(calculerDetailCoupCibleElem(setCiblesString, coupsMarques, detailsCoupsMarques.getTotal()));
//
//        Set<String> setVulnerantsString = coupsMarques.stream().map(echange -> echange.getVulnerant().getCode()).collect(Collectors.toSet());
//        detailsCoupsMarques.setVulnerants(calculerDetailCoupVulnerantElem(setVulnerantsString, coupsMarques, detailsCoupsMarques.getTotal()));
//
//        detailsCoupsMarques.setDetails(calculerDetailCoupDetailsElem(details, coupsMarques, detailsCoupsMarques.getTotal()));
//        return detailsCoupsMarques;
        return new InscritDetailsCoups();
    }

    protected InscritDetailsCoups calculerCoupsSubis(final InscritDetails details, final List<Echange> echanges) {
//        List<Echange> coupsSubis = echanges.stream()
//                .filter(echange -> !Objects.equals(echange.getInscritA().getId(), details.getInscrit().getId()))
//                .toList();
//        InscritDetailsCoups detailsCoupsSubis = new InscritDetailsCoups();
//        detailsCoupsSubis.setTotal(coupsSubis.size());
//
//        Set<String> setCiblesString = coupsSubis.stream().map(echange -> echange.getCible().getCode()).collect(Collectors.toSet());
//        detailsCoupsSubis.setCibles(calculerDetailCoupCibleElem(setCiblesString, coupsSubis, detailsCoupsSubis.getTotal()));
//
//        Set<String> setVulnerantsString = coupsSubis.stream().map(echange -> echange.getVulnerant().getCode()).collect(Collectors.toSet());
//        detailsCoupsSubis.setVulnerants(calculerDetailCoupVulnerantElem(setVulnerantsString, coupsSubis, detailsCoupsSubis.getTotal()));
//
//        detailsCoupsSubis.setDetails(calculerDetailCoupDetailsElem(details, coupsSubis, detailsCoupsSubis.getTotal()));
//        return detailsCoupsSubis;
        return new InscritDetailsCoups();
    }


    protected Set<DetailsCoupsListeElem> calculerDetailCoupCibleElem(
            final Set<String> setElemString,
            final List<Echange> echanges,
            final float total
    ) {
//        return setElemString.stream().map(elem -> {
//            long nbElem = echanges.stream().filter(echange -> Objects.equals(echange.getCible().getCode(), elem)).count();
//            return new DetailsCoupsListeElem(
//                    elem,
//                    (int) nbElem,
//                    pourcent(total, (float) nbElem));
//        }).collect(Collectors.toSet());
        return new HashSet<>();
    }

    protected Set<DetailsCoupsListeElem> calculerDetailCoupVulnerantElem(
            final Set<String> setElemString,
            final List<Echange> echanges,
            final float total
    ) {
//        return setElemString.stream().map(elem -> {
//            long nbElem = echanges.stream().filter(echange -> Objects.equals(echange.getVulnerant().getCode(), elem)).count();
//            return new DetailsCoupsListeElem(
//                    elem,
//                    (int) nbElem,
//                    pourcent(total, (float) nbElem));
//        }).collect(Collectors.toSet());
        return new HashSet<>();
    }

    protected Set<DetailsCoupsListeElem> calculerDetailCoupDetailsElem(
            final InscritDetails details,
            final List<Echange> echanges,
            final float total
    ) {
//        long nbSimu = echanges.stream().filter(Echange::isSimultanee).count();
//        DetailsCoupsListeElem elemSimultane = new DetailsCoupsListeElem("simultane", (int) nbSimu, pourcent(total, nbSimu));
//
//        long nbDoubleAtk = echanges.stream().filter(Echange::isDoubleAtk).count();
//        DetailsCoupsListeElem elemDoubleAtk = new DetailsCoupsListeElem("double atk", (int) nbDoubleAtk, pourcent(total, nbDoubleAtk));
//
//        long nbDoubleDef = echanges.stream().filter(Echange::isDoubleDef).count();
//        DetailsCoupsListeElem elemDoubleDef = new DetailsCoupsListeElem("double def", (int) nbDoubleDef, pourcent(total, nbDoubleDef));
//
//        long nbAfterblow = echanges.stream().filter(Echange::isAfterblow).count();
//        DetailsCoupsListeElem elemAfterblow = new DetailsCoupsListeElem("afterblow", (int) nbAfterblow, pourcent(total, nbAfterblow));
//
//        long nbClair = echanges.stream().filter(
//                echange -> !echange.isSimultanee()
//                        && !echange.isDoubleAtk()
//                        && !echange.isDoubleDef()
//                        && !echange.isAfterblow()
//        ).count();
//        DetailsCoupsListeElem elemClair = new DetailsCoupsListeElem("clair", (int) nbClair, pourcent(total, nbClair));
//
//        return new HashSet<>(Arrays.asList(elemSimultane, elemDoubleAtk, elemDoubleDef, elemAfterblow, elemClair));
        return new HashSet<>();
    }

    protected boolean isA(final InscritDetails details, final MatchDto match) {
        return Objects.equals(details.getInscrit().getId(), match.getInscritA().getId());
    }

    protected float pourcent(final float total, final float partion) {
        return (partion / total) * 100F;
    }
}
