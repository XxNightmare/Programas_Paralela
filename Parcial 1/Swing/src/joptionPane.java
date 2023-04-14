
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class joptionPane {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        JOptionPane.showMessageDialog(null, "Mensaje dentro de la ventana", "Mensaje en la barra de titulo",
                JOptionPane.WARNING_MESSAGE);
        String respuesta = JOptionPane.showInputDialog("Escribe tu nombre");
        System.out.println("Tu nombre es: " +respuesta);
        int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro?");
        System.out.println("Estas seguro: "+ resp);
    }

}
