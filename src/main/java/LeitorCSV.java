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

public class LeitorCSV {

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

            for (Propriedade prop : propriedades) {
                if(prop.getID()==10)
                    System.out.println(prop);
            }

        } catch (IOException | CsvException | NumberFormatException e) {
            e.printStackTrace();
        }

        Map<Propriedade, Set<Propriedade>> grafo = Grafo.construirGrafo(propriedades);
        verificarAdjacencias(grafo, 27748);
        printGrafo(grafo);

        return propriedades;
    }

    public static void printGrafo(Map<Propriedade, Set<Propriedade>> grafo) {
        for (Map.Entry<Propriedade, Set<Propriedade>> entry : grafo.entrySet()) {
            Propriedade chave = entry.getKey();
            Set<Propriedade> adjacentes = entry.getValue();

            System.out.print("ID " + chave.getID() + " -> ");
            if (adjacentes.isEmpty()) {
                System.out.println("Nenhuma adjacência");
            } else {
                for (Propriedade adjacente : adjacentes) {
                    System.out.print("ID " + adjacente.getID() + ";");
                }
                System.out.println();
            }
        }
    }

    public static void verificarAdjacencias(Map<Propriedade, Set<Propriedade>> grafo, int idPropriedade) {
        // Procurar a Propriedade com o ID especificado
        Propriedade propriedadeAlvo = null;
        for (Propriedade p : grafo.keySet()) {
            if (p.getID() == idPropriedade) {
                propriedadeAlvo = p;
                break;
            }
        }

        if (propriedadeAlvo != null) {
            Set<Propriedade> adjacencias = grafo.get(propriedadeAlvo);
            System.out.println("Propriedade com ID " + idPropriedade + " tem as seguintes adjacências:");
            for (Propriedade adj : adjacencias) {
                System.out.println("Propriedade adjacente com ID: " + adj.getID());
            }
        } else {
            System.out.println("Propriedade com ID " + idPropriedade + " não encontrada.");
        }
    }

    public static void main(String[] args) {
        String caminho = "src/main/Madeira-Moodle-1.1.csv";
        lerComOpenCSV(caminho);
    }
}
