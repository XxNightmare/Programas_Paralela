
import java.awt.Color;

public class CocheNorte extends Thread {
    private int posicion;
    private Puente puente;
    private PuenteUI puenteUI;

    public CocheNorte(int posicion, Puente puente, PuenteUI puenteUI) {
        this.posicion = posicion;
        this.puente = puente;
        this.puenteUI = puenteUI;
    }

    public void run() {
        puente.llegarNorte();
        puente.cruzarNorte(posicion);
        System.out.println("Coche Norte en posición " + posicion + " ha cruzado el puente");
        
        // Agregar un círculo azul en la interfaz gráfica y moverlo hacia la derecha
        carro circle = new carro(750, 180, 20, Color.BLUE);
        puenteUI.addCircle(circle);
        while (circle.getX() < 800) {
            circle.moveLeft(1);
            puenteUI.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
