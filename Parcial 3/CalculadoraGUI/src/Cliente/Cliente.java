package Cliente;

import Servidor.CalculadoraRemota;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Cliente extends JFrame implements ActionListener {

    private JButton[] numerosBtn;
    private JButton sumarBtn, restarBtn, multiplicarBtn, dividirBtn;
    private JTextField numeroTextField;
    private JLabel resultadoLabel;
    private CalculadoraRemota calculadora;

    private StringBuilder numeroActual;
    private int opcionSeleccionada;
    private boolean usuarioConectado;

    public Cliente() {
        opcionSeleccionada = 0;
        usuarioConectado = false;

        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLayout(new GridLayout(6, 3));

        numeroTextField = new JTextField(20);
        numeroTextField.setEditable(false);
        add(numeroTextField);

        numerosBtn = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numerosBtn[i] = new JButton(String.valueOf(i));
            numerosBtn[i].addActionListener(this);
            add(numerosBtn[i]);
        }

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

        resultadoLabel = new JLabel();
        add(resultadoLabel);

        numeroActual = new StringBuilder();

        setVisible(true);

        try {
            calculadora = (CalculadoraRemota) Naming.lookup("rmi://localhost/Calculadora");
            System.out.println("Conectado al servidor.");
            usuarioConectado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        String textoBoton = boton.getText();

        if (!usuarioConectado) {
            System.out.println("El usuario no se ha conectado al servidor.");
            return;
        }

        if (esNumero(textoBoton)) {
            numeroActual.append(textoBoton);
            numeroTextField.setText(numeroActual.toString());
        } else {
            if (opcionSeleccionada == 0) {
                System.out.println("Seleccione una operación antes de realizar cálculos.");
                return;
            }

            int numero = obtenerNumeroGuardado();
            int resultado = realizarOperacion(opcionSeleccionada, numero);
            resultadoLabel.setText("Operación: " + resultado);
        }
    }

    private int obtenerOpcion(String textoBoton) {
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
            return 0;
        }
    }

    private int realizarOperacion(int opcion, int numero) {
        try {
            switch (opcion) {
                case 1:
                    return calculadora.sumar(numero, numero);
                case 2:
                    return calculadora.restar(numero, numero);
                case 3:
                    return calculadora.multiplicar(numero, numero);
                case 4:
                    return calculadora.dividir(numero, numero);
                default:
                    return 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Cliente();
        });
    }
}
