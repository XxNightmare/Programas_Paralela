
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class jmenuBar extends JFrame implements ActionListener {

    JPanel panel;

    jmenuBar() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 250, 250);
        add(panel);

        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu();
        JMenuItem opc1 = new JMenuItem();
        JMenuItem opc2 = new JMenuItem();
        menu=new JMenu("Menu");
        opc1 =new JMenuItem("Rojo");
        opc2 =new JMenuItem("Amarillo");
        menu.add(opc1);
        menu.addSeparator();
        menu.add(opc2);
        barra.add(menu);
        setJMenuBar(barra);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jmenuBar m = new jmenuBar();
        m.setBounds(50, 50, 300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
