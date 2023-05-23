package Cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Cliente {
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private JFrame frame;
    private JTextField numeroTextField;
    private JButton sumaButton;
    private JButton restaButton;
    private JButton multiplicacionButton;
    private JButton divisionButton;
    private JLabel resultadoLabel;

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.iniciar();
    }

    public void iniciar() {
        // Crear la interfaz gráfica
        frame = new JFrame("Cliente");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Panel superior para el número
        JPanel numeroPanel = new JPanel();
        numeroPanel.setLayout(new FlowLayout());
        JLabel numeroLabel = new JLabel("Ingresa un número:");
        numeroTextField = new JTextField(10);
        numeroPanel.add(numeroLabel);
        numeroPanel.add(numeroTextField);

        // Panel central para los botones de operación
        JPanel operacionesPanel = new JPanel();
        operacionesPanel.setLayout(new FlowLayout());
        sumaButton = new JButton("Suma");
        restaButton = new JButton("Resta");
        multiplicacionButton = new JButton("Multiplicación");
        divisionButton = new JButton("División");
        operacionesPanel.add(sumaButton);
        operacionesPanel.add(restaButton);
        operacionesPanel.add(multiplicacionButton);
        operacionesPanel.add(divisionButton);

        // Panel inferior para el resultado
        JPanel resultadoPanel = new JPanel();
        resultadoPanel.setLayout(new FlowLayout());
        resultadoLabel = new JLabel("Resultado:");
        resultadoPanel.add(resultadoLabel);

        // Agregar los paneles al frame
        frame.add(numeroPanel, BorderLayout.NORTH);
        frame.add(operacionesPanel, BorderLayout.CENTER);
        frame.add(resultadoPanel, BorderLayout.SOUTH);

        // Registrar los ActionListeners para los botones de operación
        sumaButton.addActionListener(new OperacionButtonListener("+"));
        restaButton.addActionListener(new OperacionButtonListener("-"));
        multiplicacionButton.addActionListener(new OperacionButtonListener("*"));
        divisionButton.addActionListener(new OperacionButtonListener("/"));

        // Mostrar el frame
        frame.setVisible(true);

        try {
            socket = new Socket("localhost", 1025);
            System.out.println("Conectado al servidor.");

            // Establecer los flujos de entrada/salida
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class OperacionButtonListener implements ActionListener {
        private String operacion;

        public OperacionButtonListener(String operacion) {
            this.operacion = operacion;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Obtener el número ingresado por el usuario
                int numero = Integer.parseInt(numeroTextField.getText());

                // Enviar el número al servidor
                salida.writeInt(numero);

                // Enviar la operación seleccionada al servidor
                salida.writeUTF(operacion);

                // Recibir el resultado de la operación
                String resultado = entrada.readUTF();
                resultadoLabel.setText("Resultado: " + resultado);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
