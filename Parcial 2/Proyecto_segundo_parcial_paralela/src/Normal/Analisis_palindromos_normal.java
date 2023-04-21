package Normal;

import java.util.ArrayList;
import java.util.List;

public class Analisis_palindromos_normal {
    // es un método público que recibe como parámetro una lista de Strings "words" y devuelve una lista de palabras palíndromas.
    public List<String> getPalindromeWords(List<String> words) {
        // se inicializa una lista vacía "palindromeWords" que almacenará las palabras palíndromas.
        List<String> palindromeWords = new ArrayList<>();
        // se recorre cada palabra en la lista de palabras "words".
        for (String word : words) {
            // si la palabra es un palíndromo, se agrega a la lista "palindromeWords".
            if (isPalindrome(word)) {
                palindromeWords.add(word);
            }
        }
        return palindromeWords;
    }

    // es un método privado que recibe una palabra y devuelve true si la palabra es un palíndromo, de lo contrario devuelve false
    private boolean isPalindrome(String word) {
        // se eliminan los espacios en blanco y se convierte la palabra a minúsculas.
        word = word.replaceAll("\\s+", "").toLowerCase();
        // si la longitud de la palabra es menor a 2, devuelve false.
        if (word.length() < 2) {
            return false;
        }
        // devuelve true si la palabra es igual a su reverso, es decir, si es un palíndromo.
        return word.equals(new StringBuilder(word).reverse().toString());
    }

}
