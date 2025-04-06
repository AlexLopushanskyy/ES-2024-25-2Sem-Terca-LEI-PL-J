import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * The type Leitor csv test.
 */
public class LeitorCSVTest extends TestCase {

    private static final String CAMINHO_DO_ARQUIVO = "src/main/Madeira-Moodle-1.1.csv";


    public void setUp() throws Exception {
        super.setUp();
    }


    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test ler com open csv.
     */
    public void testLerComOpenCSV() {
        ArrayList<Propriedade> propriedades = LeitorCSV.lerComOpenCSV(CAMINHO_DO_ARQUIVO);
        assertNotNull(propriedades);
        assertFalse(propriedades.isEmpty());
        assertEquals(35045, propriedades.size());
    }


}