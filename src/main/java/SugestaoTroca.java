public class SugestaoTroca {
    public final Propriedade p1;
    public final Propriedade p2;
    public final int potencial;
    public final double novaMedia;

    public SugestaoTroca(Propriedade p1, Propriedade p2, int potencial, double novaMedia) {
        this.p1 = p1;
        this.p2 = p2;
        this.potencial = potencial;
        this.novaMedia = novaMedia;
    }

    @Override
    public String toString() {
        return String.format(
                "Troca sugerida: Propriedade %d (%.2f m²) <-> Propriedade %d (%.2f m²) | Potencial: %d%% | Nova média: %.2f m²",
                p1.getID(), p1.getShape_area(),
                p2.getID(), p2.getShape_area(),
                potencial, novaMedia
        );
    }
}
