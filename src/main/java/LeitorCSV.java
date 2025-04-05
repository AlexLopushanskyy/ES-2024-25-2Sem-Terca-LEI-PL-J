import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LeitorCSV {

    public static void lerComOpenCSV(String caminhoDoArquivo) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(caminhoDoArquivo))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {
            List<String[]> linhas = reader.readAll();
            for (String[] linha : linhas) {
                for (String valor : linha) {
                    System.out.print(valor + " ");
                }
                System.out.println();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String caminho = "src/main/Madeira-Moodle-1.1.csv";
        lerComOpenCSV(caminho);
    }
}
