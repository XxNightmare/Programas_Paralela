package Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InteraccionUsuario implements ActionListener {
    private JTextField display;
    private Calculadora calculadora;

    public InteraccionUsuario(JTextField display, Calculadora calculadora) {
        this.display = display;
        this.calculadora = calculadora;
    }

    public void configurarBotones(JPanel panelBotones) {
        Component[] buttons = panelBotones.getComponents();
        for (Component button : buttons) {
            if (button instanceof JButton) {
                JButton btn = (JButton) button;
                btn.addActionListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                display.setText(display.getText() + comando);
                break;

            case "+":
            case "-":
            case "*":
            case "/":
                calculadora.setOperacion(comando.charAt(0));
                calculadora.setNumero1(Double.parseDouble(display.getText()));
                display.setText("");
                break;

            case "=":
                double numero2 = Double.parseDouble(display.getText());
                double resultado = calculadora.calcularResultado(numero2);
                display.setText(Double.toString(resultado));
                break;

            case ".":
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }
                break;

            case "CE":
                display.setText("");
                break;
        }
    }
}
