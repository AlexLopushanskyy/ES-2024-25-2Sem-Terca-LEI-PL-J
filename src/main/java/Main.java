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
        GrafoPropriedades g = new GrafoPropriedades(propriedades);
        g.printGrafo(g.getGrafo());
        g.verificarAdjacencias(g.getGrafo(),24700);

        GrafoProprietarios grafoProprietarios = new GrafoProprietarios(g.getGrafo());
        grafoProprietarios.printGrafo();

        System.out.println("Vizinhos do proprietário 1000: " + grafoProprietarios.getVizinhos(1000));

        CalculosPropriedades calculos = new CalculosPropriedades(propriedades);
        double areaMediaFreguesia = calculos.calcularAreaMedia("freguesia", "São Martinho");
        System.out.println("Área média da freguesia de São Martinho: " + areaMediaFreguesia + "m²");


        //for (Propriedade prop : propriedades) {
            //if(prop.getID()==10)
              //  System.out.println(prop);
        //}
    }
}
