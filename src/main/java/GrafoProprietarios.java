import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The type Grafo proprietarios.
 */
public class GrafoProprietarios {

    private Map<Integer, Set<Integer>> grafo = new HashMap<>(); // OwnerID -> Set de OwnerIDs vizinhos

    /**
     * Instantiates a new Grafo proprietarios.
     *
     * @param grafoPropriedades the grafo propriedades
     */
    public GrafoProprietarios(Map<Propriedade, Set<Propriedade>> grafoPropriedades) {
        construirGrafo(grafoPropriedades);
    }

    private void construirGrafo(Map<Propriedade, Set<Propriedade>> grafoPropriedades) {
        for (Map.Entry<Propriedade, Set<Propriedade>> entry : grafoPropriedades.entrySet()) {
            Propriedade origem = entry.getKey();
            int donoOrigem = origem.getOWNER();

            grafo.putIfAbsent(donoOrigem, new HashSet<>());

            for (Propriedade vizinha : entry.getValue()) {
                int donoVizinho = vizinha.getOWNER();
                if (donoOrigem != donoVizinho) {
                    grafo.get(donoOrigem).add(donoVizinho);
                    grafo.putIfAbsent(donoVizinho, new HashSet<>());
                    grafo.get(donoVizinho).add(donoOrigem);
                }
            }
        }
    }

    /**
     * Print grafo.
     */
    public void printGrafo() {
        for (Map.Entry<Integer, Set<Integer>> entry : grafo.entrySet()) {
            System.out.print("Proprietário " + entry.getKey() + " -> ");
            for (Integer vizinho : entry.getValue()) {
                System.out.print(vizinho + "; ");
            }
            System.out.println();
        }
    }

    /**
     * Gets grafo.
     *
     * @return the grafo
     */
    public Map<Integer, Set<Integer>> getGrafo() {
        return grafo;
    }

    /**
     * Gets vizinhos.
     *
     * @param ownerId the owner id
     * @return the vizinhos
     */
    public Set<Integer> getVizinhos(int ownerId) {
        return grafo.getOrDefault(ownerId, new HashSet<>());
    }

}
