package org.amhe.logiques;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@ApplicationScoped
public class CsvLogique {

    String RESULTS_ROOT = "results/";

    public void writeLineByLine(List<String[]> lines) throws Exception {
        log.info("Entrée dans le writter");
        try (CSVWriter writer = new CSVWriter(new FileWriter(RESULTS_ROOT + "results.csv"))) {
            for (String[] line : lines) {
                log.info("Ecriture d'une ligne");
                writer.writeNext(line);
            }
            log.info("Sortie du writter");
        }
    }

    public String getCsv() throws IOException, CsvValidationException {
        String file;
        try (CSVReader reader = new CSVReader(new FileReader(RESULTS_ROOT + "results.csv"))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                log.info("Ligne : " + Arrays.toString(nextLine));
//                file+=nextLine;
            }
        }
        return "";
    }
}
