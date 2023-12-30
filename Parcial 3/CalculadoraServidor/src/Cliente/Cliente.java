package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente extends JFrame implements ActionListener {

    private JButton[] numerosBtn;
    private JButton sumarBtn, restarBtn, multiplicarBtn, dividirBtn, guardarBtn; // Se eliminó el botón de resultado
    private JTextField numeroTextField;
    private JLabel resultadoLabel;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private static Socket socket;
    private StringBuilder numeroActual;
    private int opcionSeleccionada; // Variable para almacenar la opción de operación seleccionada

    public Cliente() {
        opcionSeleccionada = 0; // Inicializar la variable en 0 (ninguna operación seleccionada)
        // Configurar la ventana
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new GridLayout(6, 3));

        // Crear el JTextField para mostrar los números ingresados
        numeroTextField = new JTextField(20);
        numeroTextField.setEditable(false);
        add(numeroTextField);

        // Crear los botones de números
        numerosBtn = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numerosBtn[i] = new JButton(String.valueOf(i));
            numerosBtn[i].addActionListener(this);
            add(numerosBtn[i]);
        }

        // Crear los botones de operaciones
        sumarBtn = new JButton("+");
        sumarBtn.addActionListener(this);
        add(sumarBtn);

        restarBtn = new JButton("-");
        restarBtn.addActionListener(this);
        add(restarBtn);

        multiplicarBtn = new JButton("*");
        multiplicarBtn.addActionListener(this);
        add(multiplicarBtn);

        dividirBtn = new JButton("/");
        dividirBtn.addActionListener(this);
        add(dividirBtn);

        // Crear el JLabel de resultado
        resultadoLabel = new JLabel();
        add(resultadoLabel);

        numeroActual = new StringBuilder();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());

            JButton boton = (JButton) e.getSource();
            String textoBoton = boton.getText();

            if (esNumero(textoBoton)) {
                numeroActual.append(textoBoton);
                numeroTextField.setText(numeroActual.toString());
            } else {
                int numero = obtenerNumeroGuardado();
                salida.writeInt(numero);
                opcionSeleccionada = obtenerOpcion(textoBoton); // Almacenar la nueva opción seleccionada
                System.out.println("1.- "+opcionSeleccionada);
                salida.writeInt(opcionSeleccionada);
                String resultado = realizarOperacion(opcionSeleccionada, numero); // Realizar la operación
                resultadoLabel.setText("Operación: " + resultado);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int obtenerOpcion(String textoBoton) {
        System.out.println(textoBoton);
        if (textoBoton.equals("+")) {
            return 1;
        } else if (textoBoton.equals("-")) {
            return 2;
        } else if (textoBoton.equals("*")) {
            return 3;
        } else if (textoBoton.equals("/")) {
            return 4;
        } else {
            return 0;
        }
    }

    private boolean esNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int obtenerNumeroGuardado() {
        if (numeroActual.length() > 0) {
            return Integer.parseInt(numeroActual.toString());
        } else {
            return 0; // Retorna 0 si no hay número ingresado
        }
    }

    private String realizarOperacion(int opcion, int numero) {
        try {
            salida.writeInt(numero);
            salida.writeInt(opcion);
            return entrada.readUTF();
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Error";
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