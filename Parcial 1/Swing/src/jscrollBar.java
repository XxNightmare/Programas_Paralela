
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class jscrollBar extends JFrame implements ActionListener {

    JPanel panel;

    jscrollBar() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 250, 250);
        add(panel);

        JScrollBar scroll = new JScrollBar();
        scroll.setBounds(75, 50, 50, 100);
        this.panel.add(scroll);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jscrollBar m = new jscrollBar();
        m.setBounds(50, 50, 300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
