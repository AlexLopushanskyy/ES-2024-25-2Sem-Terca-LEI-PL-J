
public class Propriedade {
    private int ID;
    private double PAR_ID;
    private int PAR_NUM;
    private double shape_length;
    private double shape_area;
    private String geometry;
    private int OWNER;
    private String freguesia;
    private String municipio;
    private String ilha;

    public void propriedade(int ID, double PAR_ID, int PAR_NUM, double shape_length, double shape_area, String geometry, int OWNER, String freguesia, String municipio, String ilha) {
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

}
