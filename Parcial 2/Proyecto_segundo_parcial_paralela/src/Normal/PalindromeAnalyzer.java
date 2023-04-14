package Normal;

import java.util.ArrayList;
import java.util.List;

class PalindromeAnalyzer {

    public List<String> getPalindromeWords(List<String> words) {
        // Analiza las palabras y devuelve una lista de palabras palíndromas
        List<String> palindromeWords = new ArrayList<>();
        for (String word : words) {
            if (isPalindrome(word)) {
                palindromeWords.add(word);
            }
        }
        return palindromeWords;
    }

    private boolean isPalindrome(String word) {
        // Elimina los espacios en blanco y convierte la palabra a minúsculas
        word = word.replaceAll("\\s+", "").toLowerCase();
        // Verifica si la palabra es igual a su reverso
        if (word.length() < 2) {
            return false;
        }

        return word.equals(new StringBuilder(word).reverse().toString());
    }

}
