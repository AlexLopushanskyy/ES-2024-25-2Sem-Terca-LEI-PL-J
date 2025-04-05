
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


    public int getID() {
        return ID;
    }


    public double getPAR_ID() {
        return PAR_ID;
    }

    public String getPAR_NUM() {
        return PAR_NUM;
    }

    public double getShape_length() {
        return shape_length;
    }

    public double getShape_area() {
        return shape_area;
    }

    public String getGeometry() {
        return geometry;
    }

    public int getOWNER() {
        return OWNER;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getIlha() {
        return ilha;
    }
}
