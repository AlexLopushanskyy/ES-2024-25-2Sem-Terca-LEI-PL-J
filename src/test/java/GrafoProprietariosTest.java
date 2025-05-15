import junit.framework.TestCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The type Grafo proprietarios test.
 */
public class GrafoProprietariosTest extends TestCase {

    private GrafoProprietarios grafoProprietarios;
    private Map<Propriedade, Set<Propriedade>> grafoPropriedades;

    public void setUp() throws Exception {
        super.setUp();
        grafoPropriedades = new HashMap<>();

        Propriedade p1 = new Propriedade(1, 123.45, "P123", 100.5, 200.75, "geometryData", 10, "Freguesia1", "Municipio1", "Ilha1");
        Propriedade p2 = new Propriedade(2, 124.45, "P124", 150.5, 300.75, "geometryData", 20, "Freguesia1", "Municipio1", "Ilha1");
        Propriedade p3 = new Propriedade(3, 125.45, "P125", 200.5, 400.75, "geometryData", 30, "Freguesia2", "Municipio2", "Ilha2");

        grafoPropriedades.put(p1, Set.of(p2));
        grafoPropriedades.put(p2, Set.of(p1));
        grafoPropriedades.put(p3, Set.of());

        grafoProprietarios = new GrafoProprietarios(grafoPropriedades);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        grafoProprietarios = null;
        grafoPropriedades = null;
    }

    /**
     * Test print grafo.
     */
    public void testPrintGrafo() {
        grafoProprietarios.printGrafo();
        // Verifique visualmente a saída no console.
    }

    /**
     * Test get grafo.
     */
    public void testGetGrafo() {
        Map<Integer, Set<Integer>> grafo = grafoProprietarios.getGrafo();
        assertNotNull(grafo);
        assertEquals(3, grafo.size());
        assertTrue(grafo.containsKey(10));
        assertTrue(grafo.containsKey(20));
        assertTrue(grafo.containsKey(30));
    }

    /**
     * Test get vizinhos.
     */
    public void testGetVizinhos() {
        Set<Integer> vizinhos = grafoProprietarios.getVizinhos(10);
        assertNotNull(vizinhos);
        assertTrue(vizinhos.contains(20));
        assertFalse(vizinhos.contains(30));

        vizinhos = grafoProprietarios.getVizinhos(30);
        assertNotNull(vizinhos);
        assertTrue(vizinhos.isEmpty());
    }
}