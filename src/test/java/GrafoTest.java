import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * The type Grafo test.
 */
public class GrafoTest extends TestCase {

    /**
     * The Propriedades.
     */
    private ArrayList<Propriedade> propriedades;
    /**
     * The Grafo.
     */
    private GrafoPropriedades grafo;

    /**
     * The constant CAMINHO_DO_ARQUIVO.
     */
    public void setUp() throws Exception {
        super.setUp();
        super.setUp();
        propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(1, 1, "1", 10.0, 20.0, "POINT (1 1)", 1, "Freguesia1", "Municipio1", "Ilha1"));
        propriedades.add(new Propriedade(2, 2, "2", 15.0, 25.0, "POINT (2 2)", 2, "Freguesia2", "Municipio2", "Ilha2"));
        propriedades.add(new Propriedade(3, 3, "3", 20.0, 30.0, "POINT (3 3)", 3, "Freguesia3", "Municipio3", "Ilha3"));
        grafo = new GrafoPropriedades(propriedades);
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    public void tearDown() throws Exception {
        super.tearDown();
        propriedades = null;
        grafo = null;
    }

    /**
     * Test construir grafo.
     */
    public void testConstruirGrafo() {
        Map<Propriedade, Set<Propriedade>> grafoMap = grafo.getGrafo();
        assertNotNull(grafoMap);
        assertEquals(3, grafoMap.size());
    }

    /**
     * Test get grafo.
     */
    public void testGetGrafo() {
        Map<Propriedade, Set<Propriedade>> grafoMap = grafo.getGrafo();
        assertNotNull(grafoMap);
        assertEquals(3, grafoMap.size());
    }

    /**
     * Test print grafo.
     */
    public void testPrintGrafo() {
        grafo.printGrafo(grafo.getGrafo());
        // Verifique a saída manualmente ou redirecione a saída do console para um stream e verifique
    }

    /**
     * Test verificar adjacencias.
     */
    public void testVerificarAdjacencias() {
        grafo.verificarAdjacencias(grafo.getGrafo(), 1);
        // Verifique a saída manualmente ou redirecione a saída do console para um stream e verifique
    }
}