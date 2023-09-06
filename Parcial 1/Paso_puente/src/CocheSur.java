
import java.awt.Color;
import javafx.scene.shape.Circle;

public class CocheSur extends Thread {
    private int posicion;
    private Puente puente;
    private PuenteUI puenteUI;

    public CocheSur(int posicion, Puente puente, PuenteUI puenteUI) {
        this.posicion = posicion;
        this.puente = puente;
        this.puenteUI = puenteUI;
    }

    public void run() {
        puente.llegarSur();
        puente.cruzarSur(posicion);
        System.out.println("Coche Sur en posición " + posicion + " ha cruzado el puente");
        
        // Agregar un círculo rojo en la interfaz gráfica y moverlo hacia la izquierda
        carro circle = new carro(10, 180, 20, Color.RED);
        puenteUI.addCircle(circle);
        while (circle.getX() > -20) {
            circle.moveRight(1);
            puenteUI.repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

