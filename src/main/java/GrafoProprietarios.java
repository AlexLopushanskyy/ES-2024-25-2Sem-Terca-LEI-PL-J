import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrafoProprietarios {

    private Map<Integer, Set<Integer>> grafo = new HashMap<>(); // OwnerID -> Set de OwnerIDs vizinhos

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

    public void printGrafo() {
        for (Map.Entry<Integer, Set<Integer>> entry : grafo.entrySet()) {
            System.out.print("Proprietário " + entry.getKey() + " -> ");
            for (Integer vizinho : entry.getValue()) {
                System.out.print(vizinho + "; ");
            }
            System.out.println();
        }
    }

    public Map<Integer, Set<Integer>> getGrafo() {
        return grafo;
    }

    public Set<Integer> getVizinhos(int ownerId) {
        return grafo.getOrDefault(ownerId, new HashSet<>());
    }

}
