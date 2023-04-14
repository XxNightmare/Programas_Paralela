
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JButton;

public class jButton extends JFrame implements ActionListener {
    JPanel panel;

    jButton(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel=new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0,0,250,250);
        add(panel);

        JButton guardar = new JButton("Guardar");
        guardar.setBounds(100,100,100,50);
        this.panel.add(guardar);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jButton m = new jButton();
        m.setBounds(50,50,300,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}