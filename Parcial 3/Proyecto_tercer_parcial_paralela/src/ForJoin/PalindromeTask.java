package ForJoin;

import java.util.ArrayList;
import java.util.List;
import static java.util.concurrent.ForkJoinTask.invokeAll;
import java.util.concurrent.RecursiveTask;

    public class PalindromeTask extends RecursiveTask<List<String>> {
        private final List<String> words;
        private Analisis_palindromos_forkjoin analisis_palindromos_forkjoin = new Analisis_palindromos_forkjoin();

        public PalindromeTask(List<String> words) {
            this.words = words;
        }

        @Override
        protected List<String> compute() {
            if (words.size() <= 10) {
                List<String> palindromeWords = new ArrayList<>();
                for (String word : words) {
                    if (analisis_palindromos_forkjoin.isPalindrome(word)) {
                        System.out.println("");
                        palindromeWords.add(word);
                    }
                }
                return palindromeWords;
            } else {
                // Se divide la lista de palabras en dos sub-listas
                int mid = words.size() / 2;
                PalindromeTask leftTask = new PalindromeTask(words.subList(0, mid));
                PalindromeTask rightTask = new PalindromeTask(words.subList(mid, words.size()));
                // Se ejecutan ambas tareas en paralelo
                invokeAll(leftTask, rightTask);
                // Se combinan los resultados
                List<String> palindromeWords = new ArrayList<>();
                palindromeWords.addAll(leftTask.join());
                palindromeWords.addAll(rightTask.join());
                return palindromeWords;
            }
        }
    }
