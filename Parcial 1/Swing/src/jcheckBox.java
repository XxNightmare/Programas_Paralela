
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;


public class jcheckBox extends JFrame implements ActionListener {
    JPanel panel;

    jcheckBox(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel=new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0,0,250,250);
        add(panel);

        JCheckBox boxUno = new JCheckBox("Femenino");
        boxUno.setBounds(100,25,100,25);
        JCheckBox boxDos = new JCheckBox("Masculino");
        boxDos.setBounds(100,75,100,75);
        this.panel.add(boxUno);
        this.panel.add(boxDos);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jcheckBox m = new jcheckBox();
        m.setBounds(50,50,300,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}