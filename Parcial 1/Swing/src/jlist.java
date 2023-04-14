
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JList;

public class jlist extends JFrame implements ActionListener {

    JPanel panel;

    jlist() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 250, 250);
        add(panel);

        DefaultListModel<String> lista = new DefaultListModel<>();
        lista.addElement("Lunes");
        lista.addElement("Martes");
        lista.addElement("Miercoles");
        lista.addElement("Jueves");
        lista.addElement("Viernes");
        lista.addElement("Sabado");
        lista.addElement("Domingo");
        JList<String> list = new JList<>(lista);
        list.setBounds(50, 50, 150, 150);
        this.panel.add(list);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jlist m = new jlist();
        m.setBounds(50, 50, 300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
