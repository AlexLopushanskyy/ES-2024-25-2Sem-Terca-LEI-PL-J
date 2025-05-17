import junit.framework.TestCase;

import java.util.*;

/**
 * The type Calculos propriedades test.
 */
public class CalculosPropriedadesTest extends TestCase {

    private CalculosPropriedades calculosPropriedades;
    private List<Propriedade> propriedades;
    private Map<Propriedade, Set<Propriedade>> grafo;

    public void setUp() throws Exception {
        super.setUp();
        propriedades = new ArrayList<>();
        propriedades.add(new Propriedade(1, 123.45, "P123", 100.5, 200.75, "geometryData", 10, "Freguesia1", "Municipio1", "Ilha1"));
        propriedades.add(new Propriedade(2, 124.45, "P124", 110.5, 205.75, "geometryData", 10, "Freguesia1", "Municipio1", "Ilha1"));
        propriedades.add(new Propriedade(3, 125.45, "P125", 120.5, 195.75, "geometryData", 20, "Freguesia2", "Municipio2", "Ilha2"));

        grafo = new HashMap<>();
        grafo.put(propriedades.get(0), Set.of(propriedades.get(1)));
        grafo.put(propriedades.get(1), Set.of(propriedades.get(0)));
        grafo.put(propriedades.get(2), Set.of());

        calculosPropriedades = new CalculosPropriedades(propriedades);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        calculosPropriedades = null;
        propriedades = null;
        grafo = null;
    }

    /**
     * Test calcular area media.
     */
    public void testCalcularAreaMedia() {
        double areaMedia = calculosPropriedades.calcularAreaMedia("freguesia", "Freguesia1");
        assertEquals(203.25, areaMedia);

        areaMedia = calculosPropriedades.calcularAreaMedia("municipio", "Municipio2");
        assertEquals(195.75, areaMedia);

        areaMedia = calculosPropriedades.calcularAreaMedia("ilha", "Ilha3");
        assertEquals(-1.0, areaMedia);
    }

    /**
     * Test calcular area media agrupada.
     */
    public void testCalcularAreaMediaAgrupada() {
        double areaMediaAgrupada = calculosPropriedades.calcularAreaMediaAgrupada("freguesia", "Freguesia1", grafo);
        assertEquals(406.5, areaMediaAgrupada);

        areaMediaAgrupada = calculosPropriedades.calcularAreaMediaAgrupada("municipio", "Municipio2", grafo);
        assertEquals(195.75, areaMediaAgrupada);

        areaMediaAgrupada = calculosPropriedades.calcularAreaMediaAgrupada("ilha", "Ilha3", grafo);
        assertEquals(-1.0, areaMediaAgrupada);
    }
}