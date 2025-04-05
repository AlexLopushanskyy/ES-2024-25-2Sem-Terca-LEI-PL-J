import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<Vertice, List<Vertice>> adjVertices;

    void addVertice(Propriedade propriedade) {
        adjVertices.putIfAbsent(new Vertice(propriedade), new ArrayList<>());
    }

    void removeVertex(Propriedade propriedade) {
        Vertice v = new Vertice(propriedade);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertice(propriedade));
    }

    void addEdge(Propriedade propriedade1, Propriedade propriedade2) {
        Vertice v1 = new Vertice(propriedade1);
        Vertice v2 = new Vertice(propriedade2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    void removeEdge(Propriedade propriedade1, Propriedade propriedade2) {
        Vertice v1 = new Vertice(propriedade1);
        Vertice v2 = new Vertice(propriedade2);
        List<Vertice> eV1 = adjVertices.get(v1);
        List<Vertice> eV2 = adjVertices.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    List<Vertice> getAdjVertices(Propriedade propriedade) {
        return adjVertices.get(new Vertice(propriedade));
    }


}
