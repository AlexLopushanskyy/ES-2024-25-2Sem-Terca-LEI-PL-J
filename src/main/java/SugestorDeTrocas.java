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

    // Método para aplicar todas as trocas sugeridas
    public void aplicarTrocas(List<TrocaSugerida> trocas) {
        for (TrocaSugerida troca : trocas) {
            Propriedade p1 = troca.getP1();
            Propriedade p2 = troca.getP2();

            // Troca os donos das propriedades
            int dono1Antigo = p1.getOWNER();
            int dono2Antigo = p2.getOWNER();

            p1.setOWNER(dono2Antigo); // A propriedade p1 recebe o dono de p2
            p2.setOWNER(dono1Antigo); // A propriedade p2 recebe o dono de p1

            // Atualizar o grafo de propriedades (se necessário)
            atualizarGrafo(p1, p2); // Atualiza as vizinhanças após a troca
        }
    }

    // Método para atualizar o grafo de propriedades
    private void atualizarGrafo(Propriedade p1, Propriedade p2) {
        // Como a troca de propriedades afeta o dono, precisamos atualizar as relações de vizinhança
        for (Map.Entry<Propriedade, Set<Propriedade>> entry : grafoPropriedades.entrySet()) {
            Propriedade origem = entry.getKey();
            Set<Propriedade> vizinhos = entry.getValue();

            // Remover a vizinhança antiga
            vizinhos.remove(p1);
            vizinhos.remove(p2);

            // Adicionar a nova vizinhança
            if (p1.getOWNER() == origem.getOWNER()) {
                vizinhos.add(p1);
            }
            if (p2.getOWNER() == origem.getOWNER()) {
                vizinhos.add(p2);
            }
        }
    }

    public Map<Propriedade, Set<Propriedade>> getGrafoPropriedades() {
        return grafoPropriedades;
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
