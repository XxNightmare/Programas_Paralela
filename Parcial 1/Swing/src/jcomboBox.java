import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JList;
import javax.swing.JPanel;

public class jcomboBox extends JFrame implements ActionListener {

    JPanel panel;

    jcomboBox() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 250, 250);
        add(panel);

        String nombres[] = {"Rafael", "Isai", "Otero", "Cabrero"};
        JComboBox combo = new JComboBox(nombres);
        combo.setBounds(100, 100, 100, 20);
        this.panel.add(combo);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jcomboBox m = new jcomboBox();
        m.setBounds(50, 50, 300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
