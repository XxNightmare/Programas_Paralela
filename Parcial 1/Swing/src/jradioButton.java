
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class jradioButton extends JFrame implements ActionListener {

    JPanel panel;

    jradioButton() {
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel = new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0, 0, 250, 250);
        add(panel);

        JRadioButton radio1 = new JRadioButton("1) Bancomer");
        JRadioButton radio2 = new JRadioButton("2) Banorte");
        radio1.setBounds(75, 50, 100, 30);
        radio2.setBounds(75, 100, 100, 30);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radio1);
        grupo.add(radio2);
        this.panel.add(radio1);
        this.panel.add(radio2);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jradioButton m = new jradioButton();
        m.setBounds(50, 50, 300, 300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
