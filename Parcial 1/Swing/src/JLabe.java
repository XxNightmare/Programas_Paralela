import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JLabe extends JFrame implements ActionListener {

    JPanel panel;

    JLabe(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel=new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0,0,250,250);
        add(panel);

        JLabel nombre;
        nombre = new JLabel("HOLA MUNDO");
        nombre.setBounds(50,100,100,10);
        this.panel.add(nombre);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        JLabe m = new JLabe();
        m.setBounds(50,50,300,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}