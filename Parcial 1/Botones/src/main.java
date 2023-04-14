import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class main {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Botones");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();

    JButton hiButton = new JButton("HI");
    hiButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Hola");
      }
    });
    panel.add(hiButton);

    JButton byeButton = new JButton("BYE");
    byeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Adios");
      }
    });
    panel.add(byeButton);

    frame.add(panel);
    frame.pack();
    frame.setLocationRelativeTo(null); // Centrar ventana en la pantalla
    frame.setVisible(true);
  }
}
