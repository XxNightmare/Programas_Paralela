package ExecuteService;

public class Main {

    public static void main(String[] args) {
        String texto = "Somos o no somos, Isaac no ronca así, Sé verlas al revés, Amó la paloma, Anita lava la tina, Luz azul, Yo hago yoga hoy, Ana lava lana";
        PalindromeCounter contador = new PalindromeCounter(texto);
        contador.calcularPalindromos();
    }
}
