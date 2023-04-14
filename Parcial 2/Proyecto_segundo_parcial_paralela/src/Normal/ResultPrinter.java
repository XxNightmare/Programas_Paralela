package Normal;

import java.util.List;

class ResultPrinter {

    public void printResults(int palindromeCount, List<String> palindromeWords) {
        // Imprime los resultados
        System.out.println("\nRESULTADOS");
        System.out.println("Palabras palíndromas: " + palindromeCount);
        System.out.print("Palabras palíndromas encontradas: ");
        if (palindromeWords.isEmpty()) {
            System.out.println("ninguna");
        } else {
            System.out.println(String.join(", ", palindromeWords));
        }
    }
}
