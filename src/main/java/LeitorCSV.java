import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LeitorCSV {

        public static void lerComOpenCSV(String caminhoDoArquivo) {
            try (CSVReader reader = new CSVReader(new FileReader(caminhoDoArquivo))) {
                List<String[]> propriedades = reader.readAll();
                

            } catch (IOException | CsvException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            String caminho = "C:\\Users\\joaol\\Downloads\\Madeira-Moodle-1.1.csv";
            lerComOpenCSV(caminho);
        }
}
