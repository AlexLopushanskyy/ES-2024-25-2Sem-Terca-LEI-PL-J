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
        GrafoPropriedades grafo = new GrafoPropriedades(propriedades);
        grafo.printGrafo(grafo.getGrafo());
        //g.verificarAdjacencias(g.getGrafo(),24700);

        GrafoProprietarios grafoProprietarios = new GrafoProprietarios(grafo.getGrafo());
        grafoProprietarios.printGrafo();

        System.out.println("Vizinhos do proprietário 1000: " + grafoProprietarios.getVizinhos(1000));

        CalculosPropriedades calculos = new CalculosPropriedades(propriedades);
        double areaMedia = calculos.calcularAreaMedia("freguesia", "calheta");
        System.out.println("Área média da freguesia de Calheta: " + areaMedia + "m²");

        double media = calculos.calcularAreaMediaAgrupada("freguesia", "calheta", grafo.getGrafo());
        System.out.println("Área média da freguesia de Calheta (propriedades adjacentes): " + media + "m²");




        // 5. Gerar sugestões de troca (ponto 6 e 7), com otimização via grafo de proprietários
        CalculadoraTrocas trocas = new CalculadoraTrocas(
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
        }


        //for (Propriedade prop : propriedades) {
            //if(prop.getID()==10)
              //  System.out.println(prop);
        //}
    }
}
