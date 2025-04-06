import java.util.ArrayList;

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
        Grafo g = new Grafo(propriedades);
        g.printGrafo(g.getGrafo());
        g.verificarAdjacencias(g.getGrafo(),13);

        for (Propriedade prop : propriedades) {
            if(prop.getID()==10)
                System.out.println(prop);
        }
    }
}
