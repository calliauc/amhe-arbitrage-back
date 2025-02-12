package org.amhe.logiques;

import com.opencsv.CSVWriter;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.util.List;

@Slf4j
@ApplicationScoped
public class CsvLogique {


    public void writeLineByLine(List<String[]> lines) throws Exception {
        log.info("Entrée dans le writter");
        try (CSVWriter writer = new CSVWriter(new FileWriter("test.csv"))) {
            for (String[] line : lines) {
                log.info("Ecriture d'une ligne");
                writer.writeNext(line);
            }
            log.info("Sortie du writter");
        }
    }
}
