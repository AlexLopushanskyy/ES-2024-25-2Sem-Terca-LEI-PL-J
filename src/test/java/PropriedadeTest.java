import junit.framework.TestCase;

/**
 * The type Propriedade test.
 */
public class PropriedadeTest extends TestCase {

    /**
     * The Propriedade.
     */
    private Propriedade propriedade;

    /**
     * Set up.
     *
     * @throws Exception the exception
     */
    public void setUp() throws Exception {
        super.setUp();
        propriedade = new Propriedade(1, 123.45, "P123", 100.5, 200.75, "geometryData", 10, "Freguesia1", "Municipio1", "Ilha1");
    }

    /**
     * Tear down.
     *
     * @throws Exception the exception
     */
    public void tearDown() throws Exception {
        super.tearDown();
        propriedade = null;
    }

    /**
     * Test to string.
     */
    public void testToString() {
        String expected = "Propriedade{ID= 1, PAR_ID= 123.45, PAR_NUM= P123, shape_length= 100.5, shape_area= 200.75, geometry= 'geometryData', OWNER= 10, freguesia= 'Freguesia1', municipio= 'Municipio1', ilha= 'Ilha1'}";
        assertEquals(expected, propriedade.toString());
    }

    /**
     * Test get id.
     */
    public void testGetID() {
        assertEquals(1, propriedade.getID());
    }

    /**
     * Test get par id.
     */
    public void testGetPAR_ID() {
        assertEquals(123.45, propriedade.getPAR_ID());
    }

    /**
     * Test get par num.
     */
    public void testGetPAR_NUM() {
        assertEquals("P123", propriedade.getPAR_NUM());
    }

    /**
     * Test get shape length.
     */
    public void testGetShape_length() {
        assertEquals(100.5, propriedade.getShape_length());
    }

    /**
     * Test get shape area.
     */
    public void testGetShape_area() {
        assertEquals(200.75, propriedade.getShape_area());
    }

    /**
     * Test get geometry.
     */
    public void testGetGeometry() {
        assertEquals("geometryData", propriedade.getGeometry());
    }

    /**
     * Test get owner.
     */
    public void testGetOWNER() {
        assertEquals(10, propriedade.getOWNER());
    }

    /**
     * Test get freguesia.
     */
    public void testGetFreguesia() {
        assertEquals("Freguesia1", propriedade.getFreguesia());
    }

    /**
     * Test get municipio.
     */
    public void testGetMunicipio() {
        assertEquals("Municipio1", propriedade.getMunicipio());
    }

    /**
     * Test get ilha.
     */
    public void testGetIlha() {
        assertEquals("Ilha1", propriedade.getIlha());
    }

    /**
     * Test set owner.
     */
    public void testSetOWNER() {
        propriedade.setOWNER(20);
        assertEquals(20, propriedade.getOWNER());
    }


}