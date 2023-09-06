import java.util.Random;

public class main {
    public static void main(String[] args) {
        Puente puente = new Puente();
        PuenteUI puenteUI = new PuenteUI();
        puenteUI.setVisible(true);

        Random random = new Random();

        while (true) {
            int numero = random.nextInt(10) + 1;
            if (numero >= 1 && numero <= 5) {
                CocheNorte coche = new CocheNorte(numero, puente, puenteUI);
                coche.start();
            } else if (numero >= 6 && numero <= 10) {
                CocheSur coche = new CocheSur(numero, puente, puenteUI);
                coche.start();
            }
        }
    }
}
