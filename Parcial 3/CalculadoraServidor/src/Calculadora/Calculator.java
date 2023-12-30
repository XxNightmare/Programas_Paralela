package Calculadora;

import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Calculator extends JFrame {
    private JTextArea displayArea;
    private String operator;
    private double currentValue;

    private DataInputStream input;
    private DataOutputStream output;
    private Socket socket;

    public Calculator(String serverAddress, int port) {
        setTitle("Calculadora :D");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(displayArea, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "√", "^", "+",
            "%", "C", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

        connectToServer(serverAddress, port);
    }

    private void connectToServer(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo conectar al servidor: " + e.getMessage(),
                                          "Error de conexión", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.matches("[0-9]") || command.equals(".")) {
                displayArea.append(command);
            } else if (command.equals("C")) {
                clear();
            } else if (command.equals("=")) {
                sendEqual();
            } else {
                sendOperation(command);
            }
        }
    }

    private void clear() {
        displayArea.setText("");
        operator = "";
        currentValue = 0;
    }

    private void sendOperation(String oper) {
        operator = oper;
        try {
            currentValue = Double.parseDouble(displayArea.getText());
            output.writeDouble(currentValue);
            output.writeUTF(operator);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al enviar la operación al servidor.", "Error de red", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendEqual() {
        try {
            double secondValue = Double.parseDouble(displayArea.getText());
            output.writeDouble(secondValue);
            output.writeUTF("=");
            //displayArea.setText("");

            double result = input.readDouble();
            displayArea.setText(String.valueOf(result));
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener el resultado del servidor.",
                                          "Error de red", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Calculator calculator = new Calculator("localhost", 12345); // Reemplace "localhost" con la dirección IP del servidor si es necesario
                calculator.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
