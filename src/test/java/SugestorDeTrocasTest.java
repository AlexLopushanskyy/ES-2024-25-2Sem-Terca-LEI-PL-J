import junit.framework.TestCase;

import java.util.*;

/**
 * The type Sugestor de trocas test.
 */
public class SugestorDeTrocasTest extends TestCase {

    private SugestorDeTrocas sugestorDeTrocas;
    private Map<Propriedade, Set<Propriedade>> grafoPropriedades;

    public void setUp() throws Exception {
        super.setUp();
        grafoPropriedades = new HashMap<>();

        Propriedade p1 = new Propriedade(1, 123.45, "P123", 100.5, 200.75, "geometryData", 10, "Freguesia1", "Municipio1", "Ilha1");
        Propriedade p2 = new Propriedade(2, 124.45, "P124", 110.5, 205.75, "geometryData", 20, "Freguesia1", "Municipio1", "Ilha1");
        Propriedade p3 = new Propriedade(3, 125.45, "P125", 120.5, 195.75, "geometryData", 30, "Freguesia2", "Municipio2", "Ilha2");

        grafoPropriedades.put(p1, new HashSet<>(List.of(p2)));
        grafoPropriedades.put(p2, new HashSet<>(List.of(p1)));
        grafoPropriedades.put(p3, new HashSet<>()); //

        sugestorDeTrocas = new SugestorDeTrocas(grafoPropriedades);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        sugestorDeTrocas = null;
        grafoPropriedades = null;
    }

    /**
     * Test sugerir trocas.
     */
    public void testSugerirTrocas() {
        List<SugestorDeTrocas.TrocaSugerida> trocas = sugestorDeTrocas.sugerirTrocas("freguesia", "Freguesia1");
        assertNotNull(trocas);
        assertEquals(1, trocas.size());

        SugestorDeTrocas.TrocaSugerida troca = trocas.get(0);
        assertEquals(10, troca.getP1().getOWNER());
        assertEquals(20, troca.getP2().getOWNER());
    }

    /**
     * Test aplicar trocas.
     */
    public void testAplicarTrocas() {
        List<SugestorDeTrocas.TrocaSugerida> trocas = sugestorDeTrocas.sugerirTrocas("freguesia", "Freguesia1");
        sugestorDeTrocas.aplicarTrocas(trocas);

        Propriedade p1 = trocas.get(0).getP1();
        Propriedade p2 = trocas.get(0).getP2();

        assertEquals(20, p1.getOWNER());
        assertEquals(10, p2.getOWNER());
    }

    /**
     * Test get grafo propriedades.
     */
    public void testGetGrafoPropriedades() {
        Map<Propriedade, Set<Propriedade>> grafo = sugestorDeTrocas.getGrafoPropriedades();
        assertNotNull(grafo);
        assertEquals(3, grafo.size());
    }
}