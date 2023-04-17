package ExecuteService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PalindromeCounter {

    private final String texto;
    private List<PalindromeTask> tareasPalindromos;

    public PalindromeCounter(String texto) {
        this.texto = texto;
    }

    public List<PalindromeTask> getTareasPalindromos() {
        return tareasPalindromos;
    }

    public void calcularPalindromos() {
        tareasPalindromos = new ArrayList<>();
        String[] palabras = texto.split("\\s+");
        for (String palabra : palabras) {
            PalindromeTask tarea = new PalindromeTask(palabra);
            tareasPalindromos.add(tarea);
        }
        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            executor.invokeAll(tareasPalindromos, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
