import ExecuteService.PalindromeTask;
import ForJoin.PalindromeCounterTask;
import ForJoin.PalindromeResult;
import Normal.PalindromeAnalyzer;
import Normal.TextAnalyzer;
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
import java.util.concurrent.atomic.AtomicInteger;

// 
public class PalindromosMain extends JFrame implements ActionListener {

    private JLabel inputLabel, titleLabel, outputLabel, txtlabel, txtNormal, txtExecuteService, txtForJoin;
    private JTextArea Cant_pal_enc, pal_enc, inputTextArea, TextAreaArreglo;
    private JButton NormalButton, clearButton, executeButton, joinButton;

    private static final ForkJoinPool pool = new ForkJoinPool();

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
        NormalButton = new JButton("Metodo normal");
        NormalButton.addActionListener(this);
        panel.add(NormalButton);

        clearButton = new JButton("Limpiar");
        clearButton.addActionListener(this);
        panel.add(clearButton);

        executeButton = new JButton("Execute Service");
        executeButton.addActionListener(this);
        panel.add(executeButton);

        joinButton = new JButton("For Join");
        joinButton.addActionListener(this);
        panel.add(joinButton);

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
        txtForJoin = new JLabel();
        panel.add(txtForJoin);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo Execute Service:");
        panel.add(txtlabel);

        // Configuración del label de salida
        txtExecuteService = new JLabel();
        panel.add(txtExecuteService);

        // Agregar el panel a la ventana
        add(panel);
    }

    public static void main(String[] args) {
        PalindromosMain mergeSortGUI = new PalindromosMain();
        mergeSortGUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Normal
        if (e.getSource() == NormalButton) {
            String input = inputTextArea.getText().trim();
            // Separa el texto en palabras y las almacena en una lista
            long tiempo1 = System.currentTimeMillis();
            TextAnalyzer analyzer = new TextAnalyzer();
            java.util.List<String> words = analyzer.getWords(input);

            // Analiza las palabras y cuenta las palíndromas
            PalindromeAnalyzer palindromeAnalyzer = new PalindromeAnalyzer();
            java.util.List<String> palindromeWords = palindromeAnalyzer.getPalindromeWords(words);

            // Imprime los resultados
            Cant_pal_enc.setText(String.valueOf(palindromeWords.size()));
            if (palindromeWords.isEmpty()) {
                pal_enc.setText("ninguna");
                JOptionPane.showMessageDialog(this, "No hay palindromos en esta oracion", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                pal_enc.setText(String.join(", ", palindromeWords));
                JOptionPane.showMessageDialog(this, "MOSTRANDO DATOS");
            }
            long tiempo2 = System.currentTimeMillis() - tiempo1;
            txtNormal.setText(String.valueOf(tiempo2 + " Milisegundos"));
        }

        // Limpiar
        if (e.getSource() == clearButton) {
            JOptionPane.showMessageDialog(this, "LIMPIANDO");
            inputTextArea.setText("");
        }

        // Execute Service
        if (e.getSource() == executeButton) {
            String input = inputTextArea.getText().trim();
            String Texto = "";
            long tiempo1 = System.currentTimeMillis();
            // Mandar a llamar la funcion
            ExecuteService.PalindromeCounter contador = new ExecuteService.PalindromeCounter(input);
            contador.calcularPalindromos();
            // Imprimir datos
            int cont = 0;
            for (PalindromeTask tarea : contador.getTareasPalindromos()) {
                if (tarea.esPalindromo()) {
                    cont++;
                    Texto = tarea.getPalabra() + "," + Texto;
                }
            }
            pal_enc.setText(Texto);
            Cant_pal_enc.setText(String.valueOf(cont));
            long tiempo2 = System.currentTimeMillis() - tiempo1;
            txtExecuteService.setText(String.valueOf(tiempo2 + " Milisegundos"));
            JOptionPane.showMessageDialog(this, "MOSTRANDO DATOS");
        }

        // For Join
        if (e.getSource() == joinButton) {
            String input = inputTextArea.getText().trim();
            long tiempo1 = System.currentTimeMillis();
            PalindromeResult result = pool.invoke(new PalindromeCounterTask(input));
            long tiempo2 = System.currentTimeMillis() - tiempo1;
            txtForJoin.setText(String.valueOf(tiempo2 + " Milisegundos"));
            Cant_pal_enc.setText(String.valueOf(result.getCount()));
            pal_enc.setText(String.valueOf(result.getWords()));
            JOptionPane.showMessageDialog(this, "MOSTRANDO DATOS");
        }
    }
}
