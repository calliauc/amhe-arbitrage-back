package org.amhe.models.dtos;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchPartialUpdateDto {
    @PathParam("id")
    private Long id;

    @QueryParam("scoreA")
    private Integer scoreA;

    @QueryParam("scoreB")
    private Integer scoreB;

    @QueryParam("timer")
    private Integer timer;

    @QueryParam("dateDebut")
    private String dateDebut;

    @QueryParam("dateFin")
    private String dateFin;

    @QueryParam("statut")
    private String statut;
}

