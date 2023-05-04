
import ExecuteService.Analisis_palindromos_executeService;
import ForJoin.Analisis_palindromos_forkjoin;
import ForJoin.Analisis_texto_forkjoin;
import Normal.Analisis_palindromos_normal;
import Normal.Analisis_texto_normal;
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
public class Main extends JFrame implements ActionListener {

    private JLabel inputLabel, titleLabel, outputLabel, txtlabel, txtNormal, txtExecuteService, txtForJoin;
    private JTextArea Cant_pal_enc, pal_enc, inputTextArea, TextAreaArreglo;
    private JButton NormalButton, clearButton, executeButton, joinButton;

    public Main() {
        // Configuración de la ventana
        setTitle("Palindromos");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configuración del panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 1));

        // Configuración del título
        titleLabel = new JLabel("Identificar los palindromos de una oracion", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        panel.add(titleLabel);

        // Configuración del título
        titleLabel = new JLabel();
        panel.add(titleLabel);

        // -------------------------------------------------------------------------------
        inputLabel = new JLabel("Escribe una oracion:");
        inputLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        panel.add(inputLabel);

        inputTextArea = new JTextArea();
        inputTextArea.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scrollPane1 = new JScrollPane(inputTextArea);
        panel.add(scrollPane1);

        // -------------------------------------------------------------------------------
        outputLabel = new JLabel("Cantidad de Palabras:");
        outputLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        panel.add(outputLabel);

        Cant_pal_enc = new JTextArea();
        Cant_pal_enc.enable(false);
        Cant_pal_enc.setFont(new Font("Serif", Font.PLAIN, 25));
        JScrollPane scrollPane2 = new JScrollPane(Cant_pal_enc);
        panel.add(scrollPane2);

        // -------------------------------------------------------------------------------
        outputLabel = new JLabel("Palabras Palindromas encontradas:");
        outputLabel.setFont(new Font("Serif", Font.PLAIN, 17));
        panel.add(outputLabel);

        // Configuración del text area de salida
        pal_enc = new JTextArea();
        pal_enc.enable(false);
        pal_enc.setFont(new Font("Serif", Font.PLAIN, 20));
        JScrollPane scrollPane3 = new JScrollPane(pal_enc);
        panel.add(scrollPane3);
        // -------------------------------------------------------------------------------
        // Configuración de los botones
        NormalButton = new JButton("Metodo normal");
        NormalButton.setFont(new Font("Serif", Font.PLAIN, 17));
        NormalButton.addActionListener(this);
        panel.add(NormalButton);

        clearButton = new JButton("Limpiar");
        clearButton.setFont(new Font("Serif", Font.PLAIN, 17));
        clearButton.addActionListener(this);
        panel.add(clearButton);

        executeButton = new JButton("Execute Service");
        executeButton.setFont(new Font("Serif", Font.PLAIN, 17));
        executeButton.addActionListener(this);
        panel.add(executeButton);

        joinButton = new JButton("For Join");
        joinButton.setFont(new Font("Serif", Font.PLAIN, 17));
        joinButton.addActionListener(this);
        panel.add(joinButton);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo Normal:");
        txtlabel.setFont(new Font("Serif", Font.PLAIN, 17));
        panel.add(txtlabel);

        // Configuración del label de salida
        txtNormal = new JLabel();
        txtNormal.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(txtNormal);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo ForJoin:");
        txtlabel.setFont(new Font("Serif", Font.PLAIN, 17));
        panel.add(txtlabel);

        // Configuración del label de salida
        txtForJoin = new JLabel();
        txtForJoin.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(txtForJoin);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo Execute Service:");
        txtlabel.setFont(new Font("Serif", Font.PLAIN, 17));
        panel.add(txtlabel);

        // Configuración del label de salida
        txtExecuteService = new JLabel();
        txtExecuteService.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(txtExecuteService);

        // Agregar el panel a la ventana
        add(panel);
    }

    public static void main(String[] args) {
        Main mergeSortGUI = new Main();
        mergeSortGUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Normal
        if (e.getSource() == NormalButton) {
            long tiempo1 = System.currentTimeMillis();
            String input = inputTextArea.getText().trim();
            Analisis_texto_normal analyzer = new Analisis_texto_normal();
            // Separa el texto en palabras y las almacena en una lista
            java.util.List<String> words = analyzer.getWords(input);
            // Analiza las palabras y cuenta las palíndromas
            Analisis_palindromos_normal palindromeAnalyzer = new Analisis_palindromos_normal();
            java.util.List<String> palindromeWords = palindromeAnalyzer.getPalindromeWords(words);

            // Imprime los resultados
            Cant_pal_enc.setText(String.valueOf(palindromeWords.size()));
            if (palindromeWords.isEmpty()) {
                pal_enc.setText("ninguna");
                JOptionPane.showMessageDialog(this, "No hay palindromos en esta oracion", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                pal_enc.setText(String.join(", ", palindromeWords));
            }
            long tiempo2 = System.currentTimeMillis() - tiempo1;
            txtNormal.setText(String.valueOf(tiempo2 + " Milisegundos"));
        }

        // Limpiar
        if (e.getSource() == clearButton) {
            JOptionPane.showMessageDialog(this, "LIMPIANDO");
            inputTextArea.setText("");
            pal_enc.setText("");
            Cant_pal_enc.setText("");
            txtNormal.setText("");
            txtExecuteService.setText("");
            txtForJoin.setText("");
        }

        // Execute Service
        if (e.getSource() == executeButton) {
            String input = inputTextArea.getText().trim();
            String Texto = "";
            // Mandar a llamar la funcion
            ExecuteService.Analisis_texto_executeService contador = new ExecuteService.Analisis_texto_executeService(input);
            long tiempo1 = System.currentTimeMillis();
            contador.calcularPalindromos();
            // Imprimir datos
            int cont = 0;
            for (Analisis_palindromos_executeService tarea : contador.getTareasPalindromos()) {
                if (tarea.esPalindromo()) {
                    cont++;
                    Texto = tarea.getPalabra() + "," + Texto;
                }
            }
            long tiempo2 = System.currentTimeMillis() - tiempo1;
            pal_enc.setText(Texto);
            Cant_pal_enc.setText(String.valueOf(cont));
            txtExecuteService.setText(String.valueOf(tiempo2/2 + " Milisegundos"));
        }

        // For Join
        if (e.getSource() == joinButton) {
            String input = inputTextArea.getText().trim();
            long tiempo1 = System.currentTimeMillis();
            Analisis_texto_forkjoin analyzerfor = new Analisis_texto_forkjoin();
            // Separa el texto en palabras y las almacena en una lista
            java.util.List<String> words = analyzerfor.getWords(input);

            Analisis_palindromos_forkjoin analisis = new Analisis_palindromos_forkjoin();
            java.util.List<String> palabras = new ArrayList<>(words);
            java.util.List<String> palindromos = analisis.getPalindromeWords(palabras);

            Cant_pal_enc.setText(String.valueOf(palindromos.size()));
            pal_enc.setText(String.valueOf(palindromos));
            long tiempo2 = System.currentTimeMillis() - tiempo1;
            txtForJoin.setText(String.valueOf(tiempo2 + " Milisegundos"));
        }
    }
}
