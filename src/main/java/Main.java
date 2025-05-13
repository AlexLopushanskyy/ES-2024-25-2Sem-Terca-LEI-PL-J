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
        //g.verificarAdjacencias(g.getGrafo(),24700);

        GrafoProprietarios grafoProprietarios = new GrafoProprietarios(grafoAdj.getGrafo());
        grafoProprietarios.printGrafo();

        System.out.println("Vizinhos do proprietário 1000: " + grafoProprietarios.getVizinhos(1000));

        CalculosPropriedades calculos = new CalculosPropriedades(propriedades);
        double areaMedia = calculos.calcularAreaMedia("ilha", "ilha da madeira (madeira)");
        System.out.println("Área média da da ilha da madeira: " + areaMedia + "m²");

        double media = calculos.calcularAreaMediaAgrupada("ilha", "ilha da madeira (madeira)", grafoAdj.getGrafo());
        System.out.println("Área média da ilha da madeira (propriedades adjacentes): " + media + "m²");


        // 5. Gerar sugestões de troca (ponto 6 e 7), com otimização via grafo de proprietários
        /*CalculadoraTrocas trocas = new CalculadoraTrocas(
                propriedades,
                grafo.getGrafo(),
                grafoProprietarios.getGrafo()
        );

        List<SugestaoTroca> sugestoes = trocas.gerarSugestoesTroca("freguesia", "calheta");

        // 6. Mostrar sugestões (ordenadas por melhoria da média)
        System.out.println("\n--- Sugestões de Troca (zona: Calheta) ---");
        if (sugestoes.isEmpty()) {
            System.out.println("Nenhuma sugestão encontrada.");
        } else {
            for (SugestaoTroca s : sugestoes) {
                System.out.println(s);
            }
        }*/
        SugestorDeTrocas sugestor = new SugestorDeTrocas(grafoAdj.getGrafo());
        List<SugestorDeTrocas.TrocaSugerida> trocas = sugestor.sugerirTrocas();
        trocas.forEach(System.out::println);
        sugestor.aplicarTrocas(trocas);

        double media2 = calculos.calcularAreaMediaAgrupada("ilha", "ilha da madeira (madeira)", sugestor.getGrafoPropriedades());
        System.out.println("Área média da ilha da madeira (propriedades adjacentes): " + media2 + "m²");
    }
}
