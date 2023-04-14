package ExecuteService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PalindromeCounter {

    private final String texto;

    public PalindromeCounter(String texto) {
        this.texto = texto;
    }

    public void calcularPalindromos() {
        List<PalindromeTask> tareasPalindromos = new ArrayList<>();
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
        int contador = 0;
        for (PalindromeTask tarea : tareasPalindromos) {
            if (tarea.esPalindromo()) {
                contador++;
                System.out.println(contador+")" + tarea.getPalabra());
            }
        }
        System.out.println("Se encontraron " + contador + " palindromos en el texto.");
    }
}
