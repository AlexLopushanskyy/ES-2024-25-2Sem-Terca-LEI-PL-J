import java.util.*;

/**
 * The type Sugestor de trocas.
 */
public class SugestorDeTrocas {

    private Map<Propriedade, Set<Propriedade>> grafoPropriedades;
    private int limitePercentualTroca = 80; // 10%

    /**
     * Instantiates a new Sugestor de trocas.
     *
     * @param grafoPropriedades the grafo propriedades
     */
    public SugestorDeTrocas(Map<Propriedade, Set<Propriedade>> grafoPropriedades) {
        this.grafoPropriedades = grafoPropriedades;
    }

    /**
     * Sugerir trocas list.
     *
     * @param tipoZona the tipo zona
     * @param nomeZona the nome zona
     * @return the list
     */
    public List<TrocaSugerida> sugerirTrocas(String tipoZona, String nomeZona) {
        List<TrocaSugerida> trocas = new ArrayList<>();

        Set<String> trocasJaSugeridas = new HashSet<>();

        for (Map.Entry<Propriedade, Set<Propriedade>> entry : grafoPropriedades.entrySet()) {
            Propriedade p1 = entry.getKey();

            for (Propriedade p2 : entry.getValue()) {
                if (p1.getOWNER() != p2.getOWNER()) {

                    // Verificar se estão na mesma zona geográfica
                    boolean mesmaZona = switch (tipoZona.toLowerCase()) {
                        case "freguesia" -> p1.getFreguesia().equalsIgnoreCase(nomeZona)
                                && p2.getFreguesia().equalsIgnoreCase(nomeZona);
                        case "municipio" -> p1.getMunicipio().equalsIgnoreCase(nomeZona)
                                && p2.getMunicipio().equalsIgnoreCase(nomeZona);
                        case "ilha" -> p1.getIlha().equalsIgnoreCase(nomeZona)
                                && p2.getIlha().equalsIgnoreCase(nomeZona);
                        default -> false;
                    };

                    if (!mesmaZona) {
                        continue; // Pular se não estiverem na mesma zona
                    }

                    // Calcular diferenças normalizadas
                    double areaDiff = Math.abs(p1.getShape_area() - p2.getShape_area()) /
                            Math.max(p1.getShape_area(), p2.getShape_area());

                    double lengthDiff = Math.abs(p1.getShape_length() - p2.getShape_length()) /
                            Math.max(p1.getShape_length(), p2.getShape_length());

                    double peso = 0.6 * areaDiff + 0.4 * lengthDiff;

                    int potencial = (int) ((1 - peso) * 100);
                    potencial = Math.max(0, Math.min(100, potencial));

                    if (potencial >= limitePercentualTroca) {
                        // Garante que troca não seja duplicada (A<->B e B<->A)
                        String idTroca = Math.min(p1.getID(), p2.getID()) + "-" + Math.max(p1.getID(), p2.getID());

                        if (!trocasJaSugeridas.contains(idTroca)) {
                            trocas.add(new TrocaSugerida(p1, p2, potencial));
                            trocasJaSugeridas.add(idTroca);
                        }
                    }
                }
            }
        }
        return trocas;
    }

    /**
     * Aplicar trocas.
     *
     * @param trocas the trocas
     */
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

    /**
     * Gets grafo propriedades.
     *
     * @return the grafo propriedades
     */
    public Map<Propriedade, Set<Propriedade>> getGrafoPropriedades() {
        return grafoPropriedades;
    }

    /**
     * The type Troca sugerida.
     */
    public static class TrocaSugerida {
        private Propriedade p1;
        private Propriedade p2;
        private int potencial;

        /**
         * Instantiates a new Troca sugerida.
         *
         * @param p1        the p 1
         * @param p2        the p 2
         * @param potencial the potencial
         */
        public TrocaSugerida(Propriedade p1, Propriedade p2, int potencial) {
            this.p1 = p1;
            this.p2 = p2;
            this.potencial = potencial;
        }

        /**
         * Gets p 1.
         *
         * @return the p 1
         */
        public Propriedade getP1() { return p1; }

        /**
         * Gets p 2.
         *
         * @return the p 2
         */
        public Propriedade getP2() { return p2; }

        /**
         * Gets potencial.
         *
         * @return the potencial
         */
        public int getPotencial() { return potencial; }


        @Override
        public String toString() {
            return "Troca sugerida: Propriedade " + p1.getID() + " (dono: " + p1.getOWNER() +
                    ", área: " + String.format("%.2f", p1.getShape_area()) + " m²)" +
                    " <-> Propriedade " + p2.getID() + " (dono: " + p2.getOWNER() +
                    ", área: " + String.format("%.2f", p2.getShape_area()) + " m²)" +
                    " | Potencial: " + potencial + "%";
        }
    }
}
