import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.*;

/**
 * The type Grafo.
 */
public class GrafoPropriedades {

    private Map<Propriedade, Set<Propriedade>> grafo = new HashMap<>(); // set para retirar duplicados

    /**
     * Instantiates a new Grafo.
     *
     * @param propriedades the propriedades
     */
    public GrafoPropriedades(List<Propriedade> propriedades) {
        construirGrafo(propriedades);
    }


    /**
     * Construir grafo.
     * Constrói o grafo a partir de uma lista de propriedades.
     * Cada propriedade é um nó no grafo, e as arestas são formadas
     * quando duas propriedades se tocam.
     *
     * @param propriedades the propriedades
     */
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



    /**
     * Gets grafo.
     *
     * @return the grafo
     */
    public Map<Propriedade, Set<Propriedade>> getGrafo() {
        return grafo;
    }

    /**
     * Print grafo.
     *
     * @param grafo the grafo
     */
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

    /**
     * Verificar adjacencias.
     * Verifica as adjacências de uma propriedade específica no grafo.
     *
     * @param grafo         the grafo
     * @param idPropriedade the id propriedade
     */
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

}
