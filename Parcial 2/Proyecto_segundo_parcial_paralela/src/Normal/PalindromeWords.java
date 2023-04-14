package Normal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PalindromeWords {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Pide al usuario que ingrese el texto
        System.out.print("Ingrese un texto: ");
        String text = input.nextLine();

        // Separa el texto en palabras y las almacena en una lista
        TextAnalyzer analyzer = new TextAnalyzer();
        List<String> words = analyzer.getWords(text);

        // Analiza las palabras y cuenta las palíndromas
        PalindromeAnalyzer palindromeAnalyzer = new PalindromeAnalyzer();
        List<String> palindromeWords = palindromeAnalyzer.getPalindromeWords(words);

        // Imprime los resultados
        ResultPrinter resultPrinter = new ResultPrinter();
        resultPrinter.printResults(palindromeWords.size(), palindromeWords);
    }

}
