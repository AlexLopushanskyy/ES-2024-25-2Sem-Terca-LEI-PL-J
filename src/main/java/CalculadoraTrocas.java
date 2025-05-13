import java.util.*;

public class CalculadoraTrocas {

    private final List<Propriedade> propriedades;
    private final Map<Propriedade, Set<Propriedade>> grafo;
    private final Map<Integer, Set<Integer>> grafoProprietarios;
    private final CalculosPropriedades calculos;

    public CalculadoraTrocas(List<Propriedade> propriedades,
                             Map<Propriedade, Set<Propriedade>> grafo,
                             Map<Integer, Set<Integer>> grafoProprietarios) {
        this.propriedades = propriedades;
        this.grafo = grafo;
        this.grafoProprietarios = grafoProprietarios;
        this.calculos = new CalculosPropriedades(propriedades);
    }

    public List<SugestaoTroca> gerarSugestoesTroca(String tipoZona, String nomeZona) {
        List<SugestaoTroca> sugestoes = new ArrayList<>();
        double mediaOriginal = calculos.calcularAreaMediaAgrupada(tipoZona, nomeZona, grafo);

        Map<Integer, List<Propriedade>> propriedadesPorDono = new HashMap<>();
        for (Propriedade p : propriedades) {
            propriedadesPorDono.computeIfAbsent(p.getOWNER(), k -> new ArrayList<>()).add(p);
        }

        for (Map.Entry<Integer, Set<Integer>> entrada : grafoProprietarios.entrySet()) {
            int ownerA = entrada.getKey();
            Set<Integer> vizinhos = entrada.getValue();

            List<Propriedade> propsA = propriedadesPorDono.getOrDefault(ownerA, List.of());

            for (int ownerB : vizinhos) {
                if (ownerB <= ownerA) continue; // evitar pares repetidos

                List<Propriedade> propsB = propriedadesPorDono.getOrDefault(ownerB, List.of());

                for (Propriedade p1 : propsA) {
                    for (Propriedade p2 : propsB) {

                        boolean mesmaZona = switch (tipoZona.toLowerCase()) {
                            case "freguesia" -> p1.getFreguesia().equalsIgnoreCase(nomeZona) &&
                                    p2.getFreguesia().equalsIgnoreCase(nomeZona);
                            case "municipio" -> p1.getMunicipio().equalsIgnoreCase(nomeZona) &&
                                    p2.getMunicipio().equalsIgnoreCase(nomeZona);
                            case "ilha" -> p1.getIlha().equalsIgnoreCase(nomeZona) &&
                                    p2.getIlha().equalsIgnoreCase(nomeZona);
                            default -> false;
                        };

                        if (!mesmaZona) continue;

                        double areaDiff = Math.abs(p1.getShape_area() - p2.getShape_area()) /
                                Math.max(p1.getShape_area(), p2.getShape_area());
                        double lengthDiff = Math.abs(p1.getShape_length() - p2.getShape_length()) /
                                Math.max(p1.getShape_length(), p2.getShape_length());

                        double peso = 0.6 * areaDiff + 0.4 * lengthDiff;
                        int potencial = (int) ((1 - peso) * 100);
                        potencial = Math.max(0, Math.min(100, potencial));

                        if (potencial >= 80) {
                            // simula troca
                            int tempOwner1 = p1.getOWNER();
                            int tempOwner2 = p2.getOWNER();
                            p1.setOWNER(tempOwner2);
                            p2.setOWNER(tempOwner1);

                            double novaMedia = calculos.calcularAreaMediaAgrupada(tipoZona, nomeZona, grafo);

                            p1.setOWNER(tempOwner1);
                            p2.setOWNER(tempOwner2);

                            if (novaMedia > mediaOriginal) {
                                sugestoes.add(new SugestaoTroca(p1, p2, potencial, novaMedia));
                            }
                        }
                    }
                }
            }
        }

        sugestoes.sort(Comparator.comparingDouble(s -> -s.novaMedia));
        return sugestoes;
    }
}