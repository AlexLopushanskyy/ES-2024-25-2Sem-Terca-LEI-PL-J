/**
 * Propriedade class represents a property with various attributes.
 */
public class Propriedade {

    private int ID;
    private double PAR_ID;
    private String PAR_NUM;
    private double shape_length;
    private double shape_area;
    private String geometry;
    private int OWNER;
    private String freguesia;
    private String municipio;
    private String ilha;

    /**
     * Constructor for the Propriedade class.
     * Initializes the properties of the Propriedade object.
     *
     * @param ID          The ID of the property.
     * @param PAR_ID      The PAR_ID of the property.
     * @param PAR_NUM     The PAR_NUM of the property.
     * @param shape_length The length of the shape.
     * @param shape_area   The area of the shape.
     * @param geometry    The geometry data of the property.
     * @param OWNER       The owner ID of the property.
     * @param freguesia   The freguesia (parish) of the property.
     * @param municipio   The municipality of the property.
     * @param ilha        The island where the property is located.
     */
    public Propriedade(int ID, double PAR_ID, String PAR_NUM, double shape_length, double shape_area, String geometry, int OWNER, String freguesia, String municipio, String ilha) {
        this.ID = ID;
        this.PAR_ID = PAR_ID;
        this.PAR_NUM = PAR_NUM;
        this.shape_length = shape_length;
        this.shape_area = shape_area;
        this.geometry = geometry;
        this.OWNER = OWNER;
        this.freguesia = freguesia;
        this.municipio = municipio;
        this.ilha = ilha;
    }

    /**
     * Override the toString method to provide a string representation of the Propriedade object.
     * This is useful for debugging and logging purposes.
     */
    @Override
    public String toString() {
        return "Propriedade{" +
                "ID= " + ID +
                ", PAR_ID= " + PAR_ID +
                ", PAR_NUM= " + PAR_NUM +
                ", shape_length= " + shape_length +
                ", shape_area= " + shape_area +
                ", geometry= '" + geometry + '\'' +
                ", OWNER= " + OWNER +
                ", freguesia= '" + freguesia + '\'' +
                ", municipio= '" + municipio + '\'' +
                ", ilha= '" + ilha + '\'' +
                '}';
    }

    /**
     * getID method returns the ID of the property.
     * @return  int ID
     */

    public int getID() {
        return ID;
    }

    /**
     * getPAR_ID method returns the PAR_ID of the property.
     * @return  double PAR_ID
     */
    public double getPAR_ID() {
        return PAR_ID;
    }

    /**
     * getPAR_NUM method returns the PAR_NUM of the property.
     * @return  String PAR_NUM
     */
    public String getPAR_NUM() {
        return PAR_NUM;
    }

    /**
     * getShape_length method returns the shape length of the property.
     * @return  double shape_length
     */
    public double getShape_length() {
        return shape_length;
    }

    /**
     * getShape_area method returns the shape area of the property.
     * @return  double shape_area
     */
    public double getShape_area() {
        return shape_area;
    }

    /**
     * getGeometry method returns the geometry of the property.
     * @return  String geometry
     */
    public String getGeometry() {
        return geometry;
    }

    /**
     * getOWNER method returns the owner ID of the property.
     * @return  int OWNER
     */
    public int getOWNER() {
        return OWNER;
    }

    /**
     * getFreguesia method returns the freguesia of the property.
     * @return  String freguesia
     */
    public String getFreguesia() {
        return freguesia;
    }

    /**
     * getMunicipio method returns the municipality of the property.
     * @return  String municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * getIlha method returns the island where the property is located.
     * @return  String ilha
     */
    public String getIlha() {
        return ilha;
    }
}
