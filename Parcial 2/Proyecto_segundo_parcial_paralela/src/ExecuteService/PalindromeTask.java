package ExecuteService;

import java.util.concurrent.Callable;

public class PalindromeTask implements Callable<String> {

    private final String palabra;
    private boolean esPalindromo;

    public PalindromeTask(String palabra) {
        this.palabra = palabra;
        this.esPalindromo = false;
        if (palabra.length() < 2) {
            this.esPalindromo = false;
        }
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder(palabra);
        if(palabra.length() > 2){
            esPalindromo = sb.reverse().toString().equalsIgnoreCase(palabra);
        }
        return "";
    }

    public boolean esPalindromo() {
        return esPalindromo;
    }

    public String getPalabra() {
        return palabra;
    }
}
