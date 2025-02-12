package org.amhe.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.amhe.logiques.CsvLogique;
import org.amhe.logiques.MatchLogique;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Path("/hema-rating")
public class HemaRatingResource {
    @Inject
    CsvLogique csvLogique;

    @Inject
    MatchLogique matchLogique;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCible() {
        List<String[]> lines = matchLogique.getMatchsCsvReady();
        log.info("Ressource");
        log.info("Lignes : " + Arrays.toString(lines.get(0)));
        try {
            csvLogique.writeLineByLine(lines);
        } catch (Exception e) {
            return Response.status(500).build();
        }
        return Response.status(200).build();
    }


}
