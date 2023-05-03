package ExecuteService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Analisis_texto_executeService {
    private final String texto;
    private List<Analisis_palindromos_executeService> lista_analisis_pal;

    public Analisis_texto_executeService(String texto) {
        this.texto = texto;
    }

    public List<Analisis_palindromos_executeService> getTareasPalindromos() {
        return lista_analisis_pal;
    }

    // Crea un método que buscará palíndromos en el texto y los almacenará en la lista de tareas 'lista_analisis_pal'.
    public void calcularPalindromos() {
        // Crea una nueva lista vacía para almacenar las tareas que buscarán palíndromos.
        lista_analisis_pal = new ArrayList<>();

        // Divide el texto en palabras y las va separando por espacios.
        String[] palabras = texto.split("\\s+");

        // Se crea un bucle para ir iterando por el arreglo de palabras que creamos anteriormente.
        for (String palabra : palabras) {
            Analisis_palindromos_executeService tarea = new Analisis_palindromos_executeService(palabra);
            lista_analisis_pal.add(tarea);
        }

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Invoca cada tarea de la lista 'lista_analisis_pal' con un límite de tiempo de 10 segundos.
        try {
            executor.invokeAll(lista_analisis_pal, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Apaga el ExecutorService una vez que todas las tareas han sido completadas.
        executor.shutdown();
    }
}
