
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class jtable extends JFrame implements ActionListener {

    JPanel panel;

    jtable() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 500, 500);
        add(panel);

        String data[][] = {
            {"14110265", "Rosa", "89.9"},
            {"14112323", "Alonso", "96.6"},
            {"14565676", "Dani", "79.9"}
        };
        String column[] = {"Registro", "Nombre", "Promedio"};
        JTable jt = new JTable(data, column);
        jt.setBounds(0, 0, 200, 300);
        JScrollPane sp = new JScrollPane(jt);
        this.panel.add(jt);
        this.panel.add(sp);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jtable m = new jtable();
        m.setBounds(50, 50, 500, 500);
        m.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
