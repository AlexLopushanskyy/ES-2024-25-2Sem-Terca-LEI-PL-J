import java.util.*;

public class SugestorDeTrocas {

    private Map<Propriedade, Set<Propriedade>> grafoPropriedades;
    private double limitePercentualArea = 0.10; // 10%

    public SugestorDeTrocas(Map<Propriedade, Set<Propriedade>> grafoPropriedades) {
        this.grafoPropriedades = grafoPropriedades;
    }

    public List<TrocaSugerida> sugerirTrocas() {
        List<TrocaSugerida> trocas = new ArrayList<>();

        Set<String> trocasJaSugeridas = new HashSet<>();

        for (Map.Entry<Propriedade, Set<Propriedade>> entry : grafoPropriedades.entrySet()) {
            Propriedade p1 = entry.getKey();

            for (Propriedade p2 : entry.getValue()) {
                if (p1.getOWNER() != p2.getOWNER()) {
                    double area1 = p1.getShape_area();
                    double area2 = p2.getShape_area();

                    double diferenca = Math.abs(area1 - area2) / Math.max(area1, area2);

                    if (diferenca <= limitePercentualArea) {
                        // Garante que troca não seja duplicada (A<->B e B<->A)
                        String idTroca = Math.min(p1.getID(), p2.getID()) + "-" + Math.max(p1.getID(), p2.getID());

                        if (!trocasJaSugeridas.contains(idTroca)) {
                            trocas.add(new TrocaSugerida(p1, p2));
                            trocasJaSugeridas.add(idTroca);
                        }
                    }
                }
            }
        }

        return trocas;
    }



    public class TrocaSugerida {
        private Propriedade p1;
        private Propriedade p2;

        public TrocaSugerida(Propriedade p1, Propriedade p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public Propriedade getP1() { return p1; }
        public Propriedade getP2() { return p2; }

        @Override
        public String toString() {
            return "Sugerir troca entre Propriedade ID " + p1.getID() +
                    " (dono: " + p1.getOWNER() + ", área: " + p1.getShape_area() + ") e " +
                    "Propriedade ID " + p2.getID() +
                    " (dono: " + p2.getOWNER() + ", área: " + p2.getShape_area() + ")";
        }
    }
}
