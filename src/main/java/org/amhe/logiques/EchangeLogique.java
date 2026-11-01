package org.amhe.logiques;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.amhe.mappers.EchangeMapper;
import org.amhe.models.dtos.EchangeNouveauDto;
import org.amhe.models.entites.CoupA;
import org.amhe.models.entites.CoupB;
import org.amhe.models.entites.Echange;
import org.amhe.repos.EchangeRepo;
import org.amhe.repos.ItemRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class EchangeLogique {
    @Inject
    EchangeMapper mapper;

    @Inject
    EchangeRepo echangeRepo;

    @Inject
    ItemRepo itemRepo;


    public List<Echange> getEchanges() {
        return echangeRepo.getEchanges();
    }

    public Echange getEchangeById(final Long id) {
        return echangeRepo.getEchangeById(id);
    }

    public Echange creerEchange(final EchangeNouveauDto echangeNouveauDto) {
        Echange echange = mapper.dtoVersBase(echangeNouveauDto);
        echange = echangeRepo.creerEchange(echange);

        if (Objects.isNull(echange.getTimecode()))
            echange.setTimecode(LocalDateTime.now());
        if (Objects.nonNull(echangeNouveauDto.getType()))
            echange.setType(itemRepo.getItemById(echangeNouveauDto.getType()));
        echange.setCoupA(creerCoupA(echange, echangeNouveauDto));
        echange.setCoupB(creerCoupB(echange, echangeNouveauDto));

        return echangeRepo.modifierEchange(echange);
    }

    public void supprimerEchange(final Long id) {
        echangeRepo.supprimerEchange(id);
    }


    private CoupA creerCoupA(Echange echange, EchangeNouveauDto echangeDto) {
        CoupA coup = new CoupA();
        coup.setEchange(echange);
        coup.setInscrit(echange.getInscritA());
        coup.setOpposant(echange.getInscritB());
        if (Objects.nonNull(echangeDto.getVulnerantA()))
            coup.setVulnerant(itemRepo.getItemById(echangeDto.getVulnerantA()));
        if (Objects.nonNull(echangeDto.getCibleA()))
            coup.setCible(itemRepo.getItemById(echangeDto.getCibleA()));
        if (Objects.nonNull(echangeDto.getPenaliteA()))
            coup.setPenalite(itemRepo.getItemById(echangeDto.getPenaliteA()));
        return coup;
    }

    private CoupB creerCoupB(Echange echange, EchangeNouveauDto echangeDto) {
        CoupB coup = new CoupB();
        coup.setEchange(echange);
        coup.setInscrit(echange.getInscritB());
        coup.setOpposant(echange.getInscritA());
        if (Objects.nonNull(echangeDto.getVulnerantB()))
            coup.setVulnerant(itemRepo.getItemById(echangeDto.getVulnerantB()));
        if (Objects.nonNull(echangeDto.getCibleB()))
            coup.setCible(itemRepo.getItemById(echangeDto.getCibleB()));
        if (Objects.nonNull(echangeDto.getPenaliteB()))
            coup.setPenalite(itemRepo.getItemById(echangeDto.getPenaliteB()));
        return coup;
    }

}