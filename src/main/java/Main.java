import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Main.
 */
public class Main {

    /**
     * É um main para testar a classe Grafo.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String caminho = "src/main/Madeira-Moodle-1.1.csv";
        ArrayList<Propriedade> propriedades = LeitorCSV.lerComOpenCSV(caminho);
        GrafoPropriedades grafoAdj = new GrafoPropriedades(propriedades);
        grafoAdj.printGrafo(grafoAdj.getGrafo());

        GrafoProprietarios grafoProprietarios = new GrafoProprietarios(grafoAdj.getGrafo());
        grafoProprietarios.printGrafo();

        System.out.println("Vizinhos do proprietário 1000: " + grafoProprietarios.getVizinhos(1000));

        CalculosPropriedades calculos = new CalculosPropriedades(propriedades);
        double areaMedia = calculos.calcularAreaMedia("ilha", "ilha da madeira (madeira)");
        System.out.println("Área média de propriedades da ilha da madeira: " + areaMedia + "m²");

        double media = calculos.calcularAreaMediaAgrupada("ilha", "ilha da madeira (madeira)", grafoAdj.getGrafo());
        System.out.println("Área média de propriedades da ilha da madeira (propriedades adjacentes): " + media + "m²");


        SugestorDeTrocas sugestor = new SugestorDeTrocas(grafoAdj.getGrafo());
        List<SugestorDeTrocas.TrocaSugerida> trocas = sugestor.sugerirTrocas("ilha", "ilha da madeira (madeira)");
        trocas.forEach(System.out::println);
        sugestor.aplicarTrocas(trocas);
        System.out.println();
        System.out.println("TROCAS APLICADAS");

        double media2 = calculos.calcularAreaMediaAgrupada("ilha", "ilha da madeira (madeira)", sugestor.getGrafoPropriedades()); //as trocas foram feitas no grafo usado nesse objeto, entao nao podemos ir fazer os calculos no grafoAdj criado no inicio.
        System.out.println("Área média de propriedades da ilha da madeira (propriedades adjacentes): " + media2 + "m²");
    }
}
