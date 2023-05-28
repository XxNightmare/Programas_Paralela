package ForJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Analisis_palindromos_forkjoin {

    public List<String> getPalindromeWords(List<String> words) {
        ForkJoinPool pool = new ForkJoinPool();
        PalindromeTask task = new PalindromeTask(words);
        return pool.invoke(task);
    }

    public Analisis_palindromos_forkjoin() {
    }

    // es un método privado que recibe una palabra y devuelve true si la palabra es un palíndromo, de lo contrario devuelve false
    public boolean isPalindrome(String word) {
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
