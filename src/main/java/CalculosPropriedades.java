import java.util.List;


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
                case "concelho":
                    corresponde = p.getMunicipio().equalsIgnoreCase(nome);
                    break;
                case "distrito":
                    corresponde = p.getIlha().equalsIgnoreCase(nome);
                    break;
                default:
                    System.out.println("Tipo inválido. Use: freguesia, concelho ou distrito.");
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
}

