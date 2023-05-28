package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cliente extends JFrame implements ActionListener {

    private CalculadoraGUI calculadoraGUI;
    private Calculadora calculadora;

    public Cliente() {
        calculadoraGUI = new CalculadoraGUI(this);
        calculadora = new Calculadora();
        calculadora.setCliente(calculadoraGUI);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        String textoBoton = boton.getText();

        if (calculadoraGUI.esNumero(textoBoton)) {
            calculadoraGUI.agregarNumero(textoBoton);
        } else {
            int numero = calculadoraGUI.obtenerNumeroGuardado();
            calculadora.realizarOperacion(textoBoton, numero);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cliente cliente = new Cliente();
            cliente.calculadoraGUI.setVisible(true);
        });
    }
}
