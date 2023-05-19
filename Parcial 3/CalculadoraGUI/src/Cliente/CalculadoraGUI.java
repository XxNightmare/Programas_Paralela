package Cliente;

import javax.swing.*;
import java.awt.*;

public class CalculadoraGUI extends JFrame {
    private JTextField display;
    private Calculadora calculadora;
    private InteraccionUsuario interaccionUsuario;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        panelPrincipal.add(display, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 3));

        String[] labels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 24));
            panelBotones.add(button);
        }

        JButton limpiarButton = new JButton("CE");
        limpiarButton.setFont(new Font("Arial", Font.BOLD, 24));
        panelBotones.add(limpiarButton);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        add(panelPrincipal);

        calculadora = new Calculadora();
        interaccionUsuario = new InteraccionUsuario(display, calculadora);
        interaccionUsuario.configurarBotones(panelBotones);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculadoraGUI();
            }
        });
    }
}
