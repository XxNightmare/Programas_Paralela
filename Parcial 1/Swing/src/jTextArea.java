
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class jTextArea extends JFrame implements ActionListener {
    JPanel panel;

    jTextArea(){
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.panel=new JPanel();
        this.panel.setLayout(null);
        this.panel.setBounds(0,0,250,250);
        add(panel);

        JTextArea area;
        area = new JTextArea("Escribe tu opinion");
        area.setBounds(10,30, 200,200);
        this.panel.add(area);
        
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        jTextArea m = new jTextArea();
        m.setBounds(50,50,300,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}