import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        String caminho = "src/main/Madeira-Moodle-1.1.csv";
        ArrayList<Propriedade> propriedades = LeitorCSV.lerComOpenCSV(caminho);
        Grafo g = new Grafo(propriedades);
        g.printGrafo(g.getGrafo());
    }
}
