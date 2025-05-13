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
        GrafoPropriedades grafo = new GrafoPropriedades(propriedades);
        grafo.printGrafo(grafo.getGrafo());
        //g.verificarAdjacencias(g.getGrafo(),24700);

        GrafoProprietarios grafoProprietarios = new GrafoProprietarios(grafo.getGrafo());
        grafoProprietarios.printGrafo();

        System.out.println("Vizinhos do proprietário 1000: " + grafoProprietarios.getVizinhos(1000));

        CalculosPropriedades calculos = new CalculosPropriedades(propriedades);
        double areaMedia = calculos.calcularAreaMedia("freguesia", "calheta");
        System.out.println("Área média de propriedades da freguesia de Calheta: " + areaMedia + "m²");

        double media = calculos.calcularAreaMediaAgrupada("freguesia", "calheta", grafo.getGrafo());
        System.out.println("Área média de propriedades da freguesia de Calheta (propriedades adjacentes): " + media + "m²");


        //for (Propriedade prop : propriedades) {
            //if(prop.getID()==10)
              //  System.out.println(prop);
        //}
    }
}
