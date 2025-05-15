import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The type Grafo propriedades test.
 */
public class GrafoPropriedadesTest extends TestCase {

    private GrafoPropriedades grafoPropriedades;
    private List<Propriedade> propriedades;

    public void setUp() throws Exception {
        super.setUp();
        propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(1, 123.45, "P123", 100.5, 200.75, "POLYGON((0 0, 0 1, 1 1, 1 0, 0 0))", 10, "Freguesia1", "Municipio1", "Ilha1"));
        propriedades.add(new Propriedade(2, 124.45, "P124", 150.5, 300.75, "POLYGON((1 0, 1 1, 2 1, 2 0, 1 0))", 20, "Freguesia1", "Municipio1", "Ilha1"));
        propriedades.add(new Propriedade(3, 125.45, "P125", 200.5, 400.75, "POLYGON((3 3, 3 4, 4 4, 4 3, 3 3))", 30, "Freguesia2", "Municipio2", "Ilha2"));

        grafoPropriedades = new GrafoPropriedades(propriedades);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        grafoPropriedades = null;
        propriedades = null;
    }

    /**
     * Test construir grafo.
     */
    public void testConstruirGrafo() {
        Map<Propriedade, Set<Propriedade>> grafo = grafoPropriedades.getGrafo();
        assertNotNull(grafo);
        assertEquals(3, grafo.size());
        assertTrue(grafo.get(propriedades.get(0)).contains(propriedades.get(1)));
        assertFalse(grafo.get(propriedades.get(0)).contains(propriedades.get(2)));
    }

    /**
     * Test get grafo.
     */
    public void testGetGrafo() {
        Map<Propriedade, Set<Propriedade>> grafo = grafoPropriedades.getGrafo();
        assertNotNull(grafo);
        assertEquals(3, grafo.size());
    }

    /**
     * Test print grafo.
     */
    public void testPrintGrafo() {
        grafoPropriedades.printGrafo(grafoPropriedades.getGrafo());
        // Este método imprime no console. Verifique visualmente se a saída está correta.
    }

    /**
     * Test verificar adjacencias.
     */
    public void testVerificarAdjacencias() {
        grafoPropriedades.verificarAdjacencias(grafoPropriedades.getGrafo(), 1);
        // Este método imprime no console. Verifique visualmente se a saída está correta.
    }
}