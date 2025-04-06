import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.index.strtree.STRtree;

import java.util.*;

public class Grafo {


    public static Map<Propriedade, Set<Propriedade>> construirGrafo(List<Propriedade> propriedades) {
        Map<Propriedade, Set<Propriedade>> grafo = new HashMap<>(); // set para retirar duplicados
        STRtree spatialIndex = new STRtree();
        WKTReader reader = new WKTReader();

        // First, load all geometries into the spatial index
        try {
            List<Geometry> geometries = new ArrayList<>();

            // Insert each propriedade into the spatial index
            for (Propriedade p : propriedades) {
                Geometry geometry = reader.read(p.getGeometry());
                geometries.add(geometry);
                spatialIndex.insert(geometry.getEnvelopeInternal(), p); // Insert the property object with its geometry
            }

            // Now, check for neighbors using the spatial index
            for (Propriedade p1 : propriedades) {
                Geometry g1 = reader.read(p1.getGeometry());
                // Query the spatial index for all geometries that might intersect with g1
                List<Propriedade> potentialNeighbors = spatialIndex.query(g1.getEnvelopeInternal());

                for (Propriedade p2 : potentialNeighbors) {
                    if (!p1.equals(p2)) {  // Avoid self-comparison
                        Geometry g2 = reader.read(p2.getGeometry());

                        // Check if the geometries actually touch
                        if (g1.touches(g2)) {
                            // Add the edge to the graph
                            grafo.computeIfAbsent(p1, k -> new HashSet<>()).add(p2);
                            grafo.computeIfAbsent(p2, k -> new HashSet<>()).add(p1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grafo;
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
