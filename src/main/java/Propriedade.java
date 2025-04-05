public class Propriedade {
    private int ID;
    private double PAR_ID;
    private int PAR_NUM;
    private double
    private int OWNER;
    private String freguesia;
    private String municipio;
    private String ilha;
    private double area;
    private double geometry;

    public Propriedade(String id, int owner, String freguesia, String municipio, String ilha, double area, double geometry) {
        this.ID = id;
        this.OWNER = owner;
        this.freguesia = freguesia;
        this.municipio = municipio;
        this.ilha = ilha;
        this.area = area;
        this.geometry = geometry;
    }


}
