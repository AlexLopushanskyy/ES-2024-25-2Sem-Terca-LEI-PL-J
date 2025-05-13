import java.util.*;

public class CalculadoraTrocas2 {

    private final List<Propriedade> propriedades;
    private final Map<Propriedade, Set<Propriedade>> grafo;
    private final Map<Integer, Set<Integer>> grafoProprietarios;

    public CalculadoraTrocas2(List<Propriedade> propriedades,
                             Map<Propriedade, Set<Propriedade>> grafo,
                             Map<Integer, Set<Integer>> grafoProprietarios) {
        this.propriedades = propriedades;
        this.grafo = grafo;
        this.grafoProprietarios = grafoProprietarios;
    }

    // Método para gerar sugestões de trocas
    public List<SugestaoTroca> sugerirTrocas() {
        List<SugestaoTroca> sugestoes = new ArrayList<>();

        // Itera sobre os proprietários no grafo de proprietários
        for (Map.Entry<Integer, Set<Integer>> entry : grafoProprietarios.entrySet()) {
            int ownerA = entry.getKey();
            Set<Integer> vizinhos = entry.getValue();

            // Obtem as propriedades do proprietário A
            List<Propriedade> propriedadesA = getPropriedadesPorDono(ownerA);

            // Percorrer os vizinhos do proprietário A
            for (int ownerB : vizinhos) {
                if (ownerB <= ownerA) continue; // Evitar comparar pares repetidos

                // Obtem as propriedades do proprietário B
                List<Propriedade> propriedadesB = getPropriedadesPorDono(ownerB);

                // Verifica trocas possíveis entre as propriedades de A e B
                for (Propriedade pA : propriedadesA) {
                    for (Propriedade pB : propriedadesB) {

                        // Verifica se a troca aumenta as adjacências para A sem diminuir para B
                        if (verificarTroca(pA, pB)) {
                            // Cria a sugestão de troca
                            SugestaoTroca sugestao = new SugestaoTroca(pA, pB);
                            sugestoes.add(sugestao);
                        }
                    }
                }
            }
        }

        // Ordenar sugestões pelo aumento de adjacências (por exemplo, ordem decrescente)
        sugestoes.sort((s1, s2) -> Integer.compare(s2.getAumentoAdjacencias(), s1.getAumentoAdjacencias()));

        return sugestoes;
    }

    // Método para obter as propriedades de um determinado proprietário
    private List<Propriedade> getPropriedadesPorDono(int ownerId) {
        List<Propriedade> propriedadesDono = new ArrayList<>();
        for (Propriedade p : propriedades) {
            if (p.getOWNER() == ownerId) {
                propriedadesDono.add(p);
            }
        }
        return propriedadesDono;
    }

    // Método para verificar se a troca de propriedades aumenta as adjacências para o dono A
    private boolean verificarTroca(Propriedade pA, Propriedade pB) {
        Set<Propriedade> adjAOriginal = grafo.getOrDefault(pA, new HashSet<>());
        Set<Propriedade> adjBOriginal = grafo.getOrDefault(pB, new HashSet<>());

        // Simula a troca
        adjAOriginal.add(pB);
        adjBOriginal.add(pA);

        // Verifica se a troca mantém a estrutura de adjacências para ambos os donos
        return (adjAOriginal.size() > grafo.get(pA).size()) && (adjBOriginal.size() > grafo.get(pB).size());
    }

    // Classe para representar a sugestão de troca de propriedades
    public class SugestaoTroca {
        private final Propriedade pA;
        private final Propriedade pB;
        private final int aumentoAdjacencias;

        public SugestaoTroca(Propriedade pA, Propriedade pB) {
            this.pA = pA;
            this.pB = pB;
            // Calcula o aumento de adjacências para A após a troca
            this.aumentoAdjacencias = calcularAumentoAdjacencias(pA, pB);
        }

        public Propriedade getpA() {
            return pA;
        }

        public Propriedade getpB() {
            return pB;
        }

        private int calcularAumentoAdjacencias(Propriedade pA, Propriedade pB) {
            int ownerA = pA.getOWNER();
            int ownerB = pB.getOWNER();

            int gruposAntesA = contarGruposAdjacentes(ownerA);
            int gruposAntesB = contarGruposAdjacentes(ownerB);

            // Simula a troca
            pA.setOWNER(ownerB);
            pB.setOWNER(ownerA);

            int gruposDepoisA = contarGruposAdjacentes(ownerB);
            int gruposDepoisB = contarGruposAdjacentes(ownerA);

            // Reverte a troca
            pA.setOWNER(ownerA);
            pB.setOWNER(ownerB);

            int ganhoA = gruposAntesA - gruposDepoisB;
            int ganhoB = gruposAntesB - gruposDepoisA;

            // Só conta ganhos, sem prejuízo
            if (gruposDepoisA <= gruposAntesB && gruposDepoisB <= gruposAntesA) {
                return Math.max(ganhoA, 0) + Math.max(ganhoB, 0);
            } else {
                return 0; // troca não válida
            }
        }

        private int contarGruposAdjacentes(int ownerId) {
            Set<Propriedade> visitadas = new HashSet<>();
            int grupos = 0;

            for (Propriedade p : propriedades) {
                if (p.getOWNER() == ownerId && !visitadas.contains(p)) {
                    grupos++;
                    Stack<Propriedade> stack = new Stack<>();
                    stack.push(p);
                    visitadas.add(p);

                    while (!stack.isEmpty()) {
                        Propriedade atual = stack.pop();
                        for (Propriedade vizinho : grafo.getOrDefault(atual, Set.of())) {
                            if (!visitadas.contains(vizinho) && vizinho.getOWNER() == ownerId) {
                                visitadas.add(vizinho);
                                stack.push(vizinho);
                            }
                        }
                    }
                }
            }

            return grupos;
        }

        public int getAumentoAdjacencias() {
            return aumentoAdjacencias;
        }
    }
}
