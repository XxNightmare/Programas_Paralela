package ExecuteService;

import java.util.concurrent.Callable;

public class Analisis_palindromos_executeService implements Callable<String> {
    private final String palabra;
    private boolean esPalindromo;

    public Analisis_palindromos_executeService(String palabra) {
        this.palabra = palabra;
        this.esPalindromo = false;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder(palabra);
        if(palabra.length() > 2){
            esPalindromo = sb.reverse().toString().equalsIgnoreCase(palabra);
        }
        return "";
    }

    // Crea un método getter para obtener el estado de 'esPalindromo'.
    public boolean esPalindromo() {
        return esPalindromo;
    }

    // Crea un método getter para obtener la palabra.
    public String getPalabra() {
        return palabra;
    }
}
