package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {

    private JButton[] numerosBtn;
    private JButton sumarBtn, restarBtn, multiplicarBtn, dividirBtn, guardarBtn;
    private JTextField numeroTextField;
    private JLabel resultadoLabel;

    private Cliente cliente;
    private StringBuilder numeroActual;

    public CalculadoraGUI(Cliente cliente) {
        this.cliente = cliente;
        numeroActual = new StringBuilder();

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
            numerosBtn[i].setFont(new Font("Arial", Font.PLAIN, 20)); // Aumentar el tamaño de la letra
            numerosBtn[i].addActionListener(cliente);
            add(numerosBtn[i]);
        }

        // Crear los botones de operaciones
        sumarBtn = new JButton("+");
        sumarBtn.setFont(new Font("Arial", Font.PLAIN, 20)); // Aumentar el tamaño de la letra
        sumarBtn.addActionListener(cliente);
        add(sumarBtn);

        restarBtn = new JButton("-");
        restarBtn.setFont(new Font("Arial", Font.PLAIN, 20)); // Aumentar el tamaño de la letra
        restarBtn.addActionListener(cliente);
        add(restarBtn);

        multiplicarBtn = new JButton("*");
        multiplicarBtn.setFont(new Font("Arial", Font.PLAIN, 20)); // Aumentar el tamaño de la letra
        multiplicarBtn.addActionListener(cliente);
        add(multiplicarBtn);

        dividirBtn = new JButton("/");
        dividirBtn.setFont(new Font("Arial", Font.PLAIN, 20)); // Aumentar el tamaño de la letra
        dividirBtn.addActionListener(cliente);
        add(dividirBtn);

        // Crear el JLabel de resultado
        resultadoLabel = new JLabel();
        resultadoLabel.setFont(new Font("Arial", Font.PLAIN, 20)); // Aumentar el tamaño de la letra
        add(resultadoLabel);

        setVisible(false);
    }

    public void agregarNumero(String texto) {
        numeroActual.append(texto);
        numeroTextField.setText(numeroActual.toString());
    }

    public int obtenerNumeroGuardado() {
        if (numeroActual.length() > 0) {
            return Integer.parseInt(numeroActual.toString());
        } else {
            return 0; // Retorna 0 si no hay número ingresado
        }
    }

    public boolean esNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void mostrarResultado(String resultado) {
        resultadoLabel.setText("" + resultado);
    }
}
