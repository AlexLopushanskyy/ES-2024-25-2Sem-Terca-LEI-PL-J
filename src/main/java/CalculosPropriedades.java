import java.util.*;


public class CalculosPropriedades {

    private List<Propriedade> propriedades;

    public CalculosPropriedades(List<Propriedade> propriedades) {
        this.propriedades = propriedades;
    }

    public double calcularAreaMedia(String tipo, String nome) {
        double soma = 0;
        int contador = 0;

        for (Propriedade p : propriedades) {
            boolean corresponde = false;

            switch (tipo.toLowerCase()) {
                case "freguesia":
                    corresponde = p.getFreguesia().equalsIgnoreCase(nome);
                    break;
                case "municipo":
                    corresponde = p.getMunicipio().equalsIgnoreCase(nome);
                    break;
                case "ilha":
                    corresponde = p.getIlha().equalsIgnoreCase(nome);
                    break;
                default:
                    System.out.println("Tipo inválido. Use: freguesia, municipio ou ilha.");
                    return -1;
            }

            if (corresponde) {
                soma += p.getShape_area();
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("Nenhuma propriedade encontrada para o critério fornecido.");
            return -1;
        }

        return soma / contador;
    }

    public double calcularAreaMediaAgrupada(String tipo, String nome, Map<Propriedade, Set<Propriedade>> grafo) {
        Set<Propriedade> visitadas = new HashSet<>();
        List<Double> areasAgrupadas = new ArrayList<>();

        for (Propriedade p : propriedades) {
            boolean corresponde = switch (tipo.toLowerCase()) {
                case "freguesia" -> p.getFreguesia().equalsIgnoreCase(nome);
                case "municipio" -> p.getMunicipio().equalsIgnoreCase(nome);
                case "ilha" -> p.getIlha().equalsIgnoreCase(nome);
                default -> false;
            };

            if (corresponde && !visitadas.contains(p)) {
                double areaGrupo = dfsAgrupar(p, grafo, visitadas);
                areasAgrupadas.add(areaGrupo);
            }
        }

        if (areasAgrupadas.isEmpty()) {
            System.out.println("Nenhuma propriedade encontrada para o critério fornecido.");
            return -1;
        }

        double soma = 0;
        for (double a : areasAgrupadas) soma += a;

        return soma / areasAgrupadas.size(); // média das áreas agrupadas
    }

    private double dfsAgrupar(Propriedade p, Map<Propriedade, Set<Propriedade>> grafo, Set<Propriedade> visitadas) {
        Stack<Propriedade> stack = new Stack<>();
        stack.push(p);
        visitadas.add(p);
        double areaTotal = 0;

        while (!stack.isEmpty()) {
            Propriedade atual = stack.pop();
            areaTotal += atual.getShape_area();

            for (Propriedade vizinho : grafo.getOrDefault(atual, Set.of())) {
                if (!visitadas.contains(vizinho) && vizinho.getOWNER() == atual.getOWNER()) {
                    visitadas.add(vizinho);
                    stack.push(vizinho);
                }
            }
        }

        return areaTotal;
    }
}

