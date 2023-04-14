import java.util.Random;

public class main {
    public static void main(String[] args) {
        Puente puente = new Puente();
        Random random = new Random();

        while (true) {
            int numero = random.nextInt(10) + 1;
            if (numero >= 1 && numero <= 5) {
                CocheNorte coche = new CocheNorte(numero, puente);
                coche.start();
            } else if (numero >= 6 && numero <= 10) {
                CocheSur coche = new CocheSur(numero, puente);
                coche.start();
            }
        }
    }
}
