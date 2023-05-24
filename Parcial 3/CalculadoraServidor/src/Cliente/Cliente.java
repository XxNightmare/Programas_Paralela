package Cliente;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Cliente extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton guardarBtn, sumarBtn, restarBtn, multiplicarBtn, dividirBtn;
    private JLabel resultadoLabel;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private static Socket socket;

    public Cliente() {
        // Configurar la ventana
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        // Crear el JLabel inicial
        JLabel tituloLabel = new JLabel("Calculadora");
        add(tituloLabel);

        // Crear el JTextField
        textField = new JTextField(10);
        add(textField);

        // Crear los botones
        guardarBtn = new JButton("Guardar");
        guardarBtn.addActionListener(this);
        add(guardarBtn);

        sumarBtn = new JButton("Sumar");
        sumarBtn.addActionListener(this);
        add(sumarBtn);

        restarBtn = new JButton("Restar");
        restarBtn.addActionListener(this);
        add(restarBtn);

        multiplicarBtn = new JButton("Multiplicar");
        multiplicarBtn.addActionListener(this);
        add(multiplicarBtn);

        dividirBtn = new JButton("Dividir");
        dividirBtn.addActionListener(this);
        add(dividirBtn);

        // Crear el JLabel de resultado
        resultadoLabel = new JLabel();
        add(resultadoLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());

            if (e.getSource() == guardarBtn) {
                int numero = Integer.parseInt(textField.getText().toString());
                salida.writeInt(numero);
            } else if (e.getSource() == sumarBtn) {
                salida.writeInt(1);
                String resultado = entrada.readUTF();
                resultadoLabel.setText("Operacion: " + resultado);
            } else if (e.getSource() == restarBtn) {
                salida.writeInt(2);
                String resultado = entrada.readUTF();
                resultadoLabel.setText("Operacion: " + resultado);
            } else if (e.getSource() == multiplicarBtn) {
                salida.writeInt(3);
                String resultado = entrada.readUTF();
                resultadoLabel.setText("Operacion: " + resultado);
            } else if (e.getSource() == dividirBtn) {
                salida.writeInt(4);
                String resultado = entrada.readUTF();
                resultadoLabel.setText("Operacion: " + resultado);
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 1025);
            System.out.println("Conectado al servidor.");

            SwingUtilities.invokeLater(() -> {
                new Cliente();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
