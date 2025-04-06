import junit.framework.TestCase;

public class PropriedadeTest extends TestCase {

    private Propriedade propriedade;

    public void setUp() throws Exception {
        super.setUp();
        propriedade = new Propriedade(1, 123.45, "P123", 100.5, 200.75, "geometryData", 10, "Freguesia1", "Municipio1", "Ilha1");
    }

    public void tearDown() throws Exception {
        super.tearDown();
        propriedade = null;
    }

    public void testToString() {
        String expected = "Propriedade{ID= 1, PAR_ID= 123.45, PAR_NUM= P123, shape_length= 100.5, shape_area= 200.75, geometry= 'geometryData', OWNER= 10, freguesia= 'Freguesia1', municipio= 'Municipio1', ilha= 'Ilha1'}";
        assertEquals(expected, propriedade.toString());
    }

    public void testGetID() {
        assertEquals(1, propriedade.getID());
    }

    public void testGetPAR_ID() {
        assertEquals(123.45, propriedade.getPAR_ID());
    }

    public void testGetPAR_NUM() {
        assertEquals("P123", propriedade.getPAR_NUM());
    }

    public void testGetShape_length() {
        assertEquals(100.5, propriedade.getShape_length());
    }

    public void testGetShape_area() {
        assertEquals(200.75, propriedade.getShape_area());
    }

    public void testGetGeometry() {
        assertEquals("geometryData", propriedade.getGeometry());
    }

    public void testGetOWNER() {
        assertEquals(10, propriedade.getOWNER());
    }

    public void testGetFreguesia() {
        assertEquals("Freguesia1", propriedade.getFreguesia());
    }

    public void testGetMunicipio() {
        assertEquals("Municipio1", propriedade.getMunicipio());
    }

    public void testGetIlha() {
        assertEquals("Ilha1", propriedade.getIlha());
    }
}