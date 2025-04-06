import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.*;

public class Grafo {

    private Map<Propriedade, Set<Propriedade>> grafo = new HashMap<>(); // set para retirar duplicados

    public Grafo(List<Propriedade> propriedades) {
        construirGrafo(propriedades);
    }


    public void construirGrafo(List<Propriedade> propriedades) {
        STRtree spatialIndex = new STRtree();  // Spatial index para otimizar a busca de vizinhos
        WKTReader reader = new WKTReader();    // Leitor WKT para converter geometria

        // Primeiro, carregue todas as geometrias no índice espacial
        try {
            // Adiciona todas as propriedades ao grafo como nós, incluindo as isoladas
            for (Propriedade p : propriedades) {
                grafo.putIfAbsent(p, new HashSet<>());
                Geometry geometry = reader.read(p.getGeometry());
                spatialIndex.insert(geometry.getEnvelopeInternal(), p); // Adiciona o envelope da geometria ao índice espacial
            }

            // Agora, verifica os vizinhos usando o índice espacial
            for (Propriedade p1 : propriedades) {
                Geometry g1 = reader.read(p1.getGeometry());
                // Consulta o índice espacial para todas as geometrias que podem interagir com g1
                List<Propriedade> potentialNeighbors = spatialIndex.query(g1.getEnvelopeInternal());

                for (Propriedade p2 : potentialNeighbors) {
                    if (!p1.equals(p2)) {  // Evita comparação consigo mesma
                        Geometry g2 = reader.read(p2.getGeometry());

                        // Verifica se as geometrias se tocam
                        if (g1.touches(g2)) {
                            // Adiciona a aresta ao grafo (se tocar, são vizinhos)
                            grafo.get(p1).add(p2);
                            grafo.get(p2).add(p1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Map<Propriedade, Set<Propriedade>> getGrafo() {
        return grafo;
    }

    public void printGrafo(Map<Propriedade, Set<Propriedade>> grafo) {
        for (Map.Entry<Propriedade, Set<Propriedade>> entry : grafo.entrySet()) {
            Propriedade chave = entry.getKey();
            Set<Propriedade> adjacentes = entry.getValue();

            System.out.print("ID " + chave.getID() + " -> ");
            if (adjacentes.isEmpty()) {
                System.out.println("Nenhuma adjacência");
            } else {
                for (Propriedade adjacente : adjacentes) {
                    System.out.print("ID " + adjacente.getID() + ";");
                }
                System.out.println();
            }
        }
    }


    public void verificarAdjacencias(Map<Propriedade, Set<Propriedade>> grafo, int idPropriedade) {
        // Procurar a Propriedade com o ID especificado
        Propriedade propriedadeAlvo = null;
        for (Propriedade p : grafo.keySet()) {
            if (p.getID() == idPropriedade) {
                propriedadeAlvo = p;
                break;
            }
        }

        if (propriedadeAlvo != null) {
            Set<Propriedade> adjacencias = grafo.get(propriedadeAlvo);
            System.out.println("Propriedade com ID " + idPropriedade + " tem as seguintes adjacências:");
            for (Propriedade adj : adjacencias) {
                System.out.println("Propriedade adjacente com ID: " + adj.getID());
            }
        } else {
            System.out.println("Propriedade com ID " + idPropriedade + " não encontrada.");
        }
    }

    /*
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

*/
}
