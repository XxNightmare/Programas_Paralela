
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Circle;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PuenteUI extends JFrame {
    private List<carro> circles;

    public PuenteUI() {
        circles = new ArrayList<>();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setTitle("Puente");

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (carro circle : circles) {
                    circle.draw(g);
                }
            }
        };
        getContentPane().add(panel);
    }

    public void addCircle(carro circle) {
        circles.add(circle);
        repaint();
    }
}
