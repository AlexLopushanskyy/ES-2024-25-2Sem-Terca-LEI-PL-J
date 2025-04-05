import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorCSV {

    public static void lerComOpenCSV(String caminhoDoArquivo) {
        List<Propriedade> propriedades = new ArrayList<>(); // Lista para armazenar as propriedades

        try (CSVReader reader = new CSVReader(new FileReader(caminhoDoArquivo))) {
            List<String[]> linhas = reader.readAll();
            for (String[] linha : linhas) {
                // Mapeando os dados da linha para os atributos da classe Propriedade
                int ID = Integer.parseInt(linha[0]);             // ID da propriedade
                double parId = Double.parseDouble(linha[1]);     // PAR_ID
                int parNum = Integer.parseInt(linha[2]);         // PAR_NUM
                double shapeLength = Double.parseDouble(linha[3]);  // Shape_Length
                double shapeArea = Double.parseDouble(linha[4]);    // Shape_Area
                String geometry = linha[5];                       // Geometry (provavelmente WKT)
                int owner = Integer.parseInt(linha[6]);           // OWNER
                String freguesia = linha[7];                      // Freguesia
                String municipio = linha[8];                      // Municipio
                String ilha = linha[9];                           // Ilha

                // Criando o objeto Propriedade
                Propriedade propriedade = new Propriedade(ID, parId, parNum, shapeLength, shapeArea, geometry, owner, freguesia, municipio, ilha);

                // Adicionando a propriedade à lista
                propriedades.add(propriedade);
            }

            // Exibindo as propriedades lidas
            for (Propriedade prop : propriedades) {
                System.out.println(prop);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String caminho = "C:\\Users\\joaol\\Downloads\\Madeira-Moodle-1.1.csv";
        lerComOpenCSV(caminho);
    }
}
