import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The type Cyclomatic complexity calculator.
 */
public class CyclomaticComplexityCalculator {

    /**
     * The entry point of application.
     * Calcula a complexidade ciclomática de um arquivo Java.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String filePath = "src/main/java/LeitorCSV.java";
        try {
            int complexity = calculateCyclomaticComplexity(filePath);
            System.out.println("Complexidade ciclomática: " + complexity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate cyclomatic complexity int.
     *
     * @param filePath the file path
     * @return the int
     * @throws IOException the io exception
     */
    public static int calculateCyclomaticComplexity(String filePath) throws IOException {
        int complexity = 1; // Complexidade inicial é 1
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("if") || line.startsWith("for") || line.startsWith("while") ||
                        line.startsWith("case") || line.startsWith("catch") || line.startsWith("&&") || line.startsWith("||")) {
                    complexity++;
                }
            }
        }
        return complexity;
    }
}