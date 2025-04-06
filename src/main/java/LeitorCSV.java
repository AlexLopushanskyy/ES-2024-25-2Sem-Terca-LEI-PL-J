import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Leitor csv.
 */
public class LeitorCSV {

    /**
     * Ler com open csv array list.
     *
     * @param caminhoDoArquivo the caminho do arquivo
     * @return the array list
     */
    public static ArrayList<Propriedade> lerComOpenCSV(String caminhoDoArquivo) {
        ArrayList<Propriedade> propriedades = new ArrayList<>();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(caminhoDoArquivo))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
                .build()) {

            List<String[]> linhas = reader.readAll();

            if (!linhas.isEmpty()) {
                linhas.remove(0); // Cabeçalho
            }

            for (String[] linha : linhas) {
                int ID = Integer.parseInt(linha[0]);                // OBJECTID
                double parId = Double.parseDouble(linha[1]);        // PAR_ID
                String parNum = linha[2];                           // PAR_NUM
                double shapeLength = Double.parseDouble(linha[3]);  // Shape_Length
                double shapeArea = Double.parseDouble(linha[4]);    // Shape_Area
                String geometry = linha[5];                         // geometry
                int owner = Integer.parseInt(linha[6]);             // OWNER
                String freguesia = linha[7];                        // Freguesia
                String municipio = linha[8];                        // Municipio
                String ilha = linha[9];                             // Ilha

                Propriedade propriedade = new Propriedade(
                        ID, parId, parNum, shapeLength, shapeArea, geometry,
                        owner, freguesia, municipio, ilha
                );

                propriedades.add(propriedade);
            }

        } catch (IOException | CsvException | NumberFormatException e) {
            e.printStackTrace();
        }

        return propriedades;
    }

}
