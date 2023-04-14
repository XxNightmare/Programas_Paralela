
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.ForkJoinPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.CompletableFuture;

public class PalindromosMain extends JFrame implements ActionListener {

    private JLabel inputLabel, titleLabel, outputLabel, txtlabel, txtNormal, txtExecuteService, txtForJoin;
    private JTextArea Cant_pal_enc, pal_enc, inputTextArea, TextAreaArreglo;
    private JButton registerButton, executeButton, clearButton, serviceButton, joinButton;

    public PalindromosMain() {
        // Configuración de la ventana
        setTitle("Algoritmo Merge Sort");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configuración del panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 1));

        // Configuración del título
        titleLabel = new JLabel("Identificar los palindromos de una oracion", SwingConstants.CENTER);
        panel.add(titleLabel);

        // Configuración del título
        titleLabel = new JLabel();
        panel.add(titleLabel);

        // -------------------------------------------------------------------------------
        inputLabel = new JLabel("Escribe una oracion:");
        panel.add(inputLabel);

        inputTextArea = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(inputTextArea);
        panel.add(scrollPane1);
        
        // -------------------------------------------------------------------------------
        outputLabel = new JLabel("Cantidad de Palabras Palindromas encontradas:");
        panel.add(outputLabel);

        Cant_pal_enc = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane(Cant_pal_enc);
        panel.add(scrollPane2);
        
        // -------------------------------------------------------------------------------
        outputLabel = new JLabel("Palabras Palindromas encontradas:");
        panel.add(outputLabel);

        // Configuración del text area de salida
        pal_enc = new JTextArea();
        JScrollPane scrollPane3 = new JScrollPane(pal_enc);
        panel.add(scrollPane3);
        // -------------------------------------------------------------------------------

        // Configuración de los botones
        registerButton = new JButton("Registrar");
        registerButton.addActionListener(this);
        panel.add(registerButton);

        executeButton = new JButton("Buscar por metodo normal");
        executeButton.addActionListener(this);
        panel.add(executeButton);

        clearButton = new JButton("Limpiar");
        clearButton.addActionListener(this);
        panel.add(clearButton);

        serviceButton = new JButton("Execute Service");
        serviceButton.addActionListener(this);
        panel.add(serviceButton);

        joinButton = new JButton("For Join");
        joinButton.addActionListener(this);
        panel.add(joinButton);

        // Configuración del título
        titleLabel = new JLabel();
        panel.add(titleLabel);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo Normal:");
        panel.add(txtlabel);

        // Configuración del label de salida
        txtNormal = new JLabel();
        panel.add(txtNormal);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo ForJoin:");
        panel.add(txtlabel);

        // Configuración del label de salida
        txtExecuteService = new JLabel();
        panel.add(txtExecuteService);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo Execute Service:");
        panel.add(txtlabel);

        // Configuración del label de salida
        txtForJoin = new JLabel();
        panel.add(txtForJoin);

        // Agregar el panel a la ventana
        add(panel);
    }

    public static void main(String[] args) {
        PalindromosMain mergeSortGUI = new PalindromosMain();
        mergeSortGUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Registrar un nuevo dato en la lista
        if (e.getSource() == registerButton) {
            JOptionPane.showMessageDialog(this, "DATOS AGREGADOS EXITOSAMENTE");
        }

        // Realizar el algoritmo Merge Sort y mostrar el resultado
        if (e.getSource() == joinButton) {
            JOptionPane.showMessageDialog(this, "MOSTRANDO DATOS");
        }

        // Limpiar los text areas
        if (e.getSource() == clearButton) {
            JOptionPane.showMessageDialog(this, "LIMPIANDO");
            inputTextArea.setText("");
        }

        // Ejecutar un Execute Service
        if (e.getSource() == serviceButton) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese al menos un dato", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Unirse a un For Join
        if (e.getSource() == executeButton) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese al menos un dato", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
