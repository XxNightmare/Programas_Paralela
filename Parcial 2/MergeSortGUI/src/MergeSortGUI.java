
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

public class MergeSortGUI extends JFrame implements ActionListener {

    private JLabel titleLabel, inputLabel, outputLabel, txtlabel, txtlabel_2, txtlabel_3, txtlabel_4;
    private JTextArea inputTextArea, outputTextArea, TextAreaArreglo;
    private JButton Btnr, Btnn, clearButton, Btnes, Btnj;
    private ArrayList<Integer> dataList;
    private ForJoin fj;

    public MergeSortGUI() {
        // Configuración de la ventana
        setTitle("Algoritmo Merge Sort");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Configuración del panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 1));

        // Configuración del título
        titleLabel = new JLabel("Algoritmo Merge Sort", SwingConstants.CENTER);
        panel.add(titleLabel);

        // Configuración del título
        titleLabel = new JLabel();
        panel.add(titleLabel);

        // Configuración del label de entrada
        inputLabel = new JLabel("Cantidad de datos por ingresar:");
        panel.add(inputLabel);

        // Configuración del text area de entrada
        inputTextArea = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(inputTextArea);
        panel.add(scrollPane1);

        // Configuración del label de entrada
        inputLabel = new JLabel("Arreglo con el que trabajaremos:");
        panel.add(inputLabel);

        // Configuración del text area de entrada
        TextAreaArreglo = new JTextArea();
        TextAreaArreglo.setEnabled(false);
        JScrollPane scrollPane12 = new JScrollPane(TextAreaArreglo);
        panel.add(scrollPane12);

        // Configuración del label de salida
        outputLabel = new JLabel("Resultado:");
        panel.add(outputLabel);

        // Configuración del text area de salida
        outputTextArea = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane(outputTextArea);
        panel.add(scrollPane2);

        // Configuración de los botones
        Btnr = new JButton("Registrar");
        Btnr.addActionListener(this);
        panel.add(Btnr);

        Btnn = new JButton("Ejecutar Metodo MergeSort");
        Btnn.addActionListener(this);
        panel.add(Btnn);

        clearButton = new JButton("Limpiar");
        clearButton.addActionListener(this);
        panel.add(clearButton);

        Btnes = new JButton("Execute Service");
        Btnes.addActionListener(this);
        panel.add(Btnes);

        Btnj = new JButton("For Join");
        Btnj.addActionListener(this);
        panel.add(Btnj);

        // Configuración del título
        titleLabel = new JLabel();
        panel.add(titleLabel);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo Merge sort:");
        panel.add(txtlabel);

        // Configuración del label de salida
        txtlabel_3 = new JLabel();
        panel.add(txtlabel_3);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo ForJoin:");
        panel.add(txtlabel);

        // Configuración del label de salida
        txtlabel_2 = new JLabel();
        panel.add(txtlabel_2);

        // Configuración del label de salida
        txtlabel = new JLabel("Tiempo Execute Service:");
        panel.add(txtlabel);

        // Configuración del label de salida
        txtlabel_4 = new JLabel();
        panel.add(txtlabel_4);

        // Agregar el panel a la ventana
        add(panel);
    }

    public static void main(String[] args) {
        MergeSortGUI mergeSortGUI = new MergeSortGUI();
        mergeSortGUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Registrar un nuevo dato en la lista
        if (e.getSource() == Btnr) {
            dataList = new ArrayList<>();
            String input = inputTextArea.getText().trim();
            try {
                int data = Integer.parseInt(input);
                Random rand = new Random();
                for (int i = 0; i < data; i++) {
                    int numeroAleatorio = rand.nextInt(100) + 1;
                    dataList.add(numeroAleatorio);
                }

                int[] arr = new int[dataList.size()];
                for (int i = 0; i < dataList.size(); i++) {
                    arr[i] = dataList.get(i);
                }
                TextAreaArreglo.setText(Arrays.toString(arr));
                JOptionPane.showMessageDialog(this, "DATOS AGREGADOS EXITOSAMENTE");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Realizar el algoritmo Merge Sort y mostrar el resultado
        if (e.getSource() == Btnj) {
            if (dataList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese al menos un dato", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int[] arr = new int[dataList.size()];
                for (int i = 0; i < dataList.size(); i++) {
                    arr[i] = dataList.get(i);
                }
                long tiempo1 = System.currentTimeMillis();
                mergeSort(arr, 0, arr.length - 1);
                outputTextArea.setText(Arrays.toString(arr));
                long tiempo2 = System.currentTimeMillis() - tiempo1;
                txtlabel_2.setText(String.valueOf(tiempo2 + " Milisegundos"));
                JOptionPane.showMessageDialog(this, "MOSTRANDO DATOS");
            }
        }

        // Limpiar los text areas
        if (e.getSource() == clearButton) {
            JOptionPane.showMessageDialog(this, "LIMPIANDO");
            inputTextArea.setText("");
            outputTextArea.setText("");
        }

        // Ejecutar un Execute Service
        if (e.getSource() == Btnes) {
            if (dataList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese al menos un dato", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int[] arr = new int[dataList.size()];
                for (int i = 0; i < dataList.size(); i++) {
                    arr[i] = dataList.get(i);
                }
                long tiempo1 = System.currentTimeMillis();

                // Crear objeto de ExecuteService
                ExecuteService executeService = new ExecuteService(arr);

                // Ejecutar ordenamiento
                ExecutorService executor = Executors.newSingleThreadExecutor();
                //  Future representa un resultado pendiente de una tarea en segundo plano
                Future<int[]> future = executor.submit(executeService);

                // Obtener el arreglo ordenado del Future
                try {
                    int[] sortedArray = future.get();
                    outputTextArea.setText(Arrays.toString(sortedArray));
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
                long tiempo2 = System.currentTimeMillis() - tiempo1;
                txtlabel_4.setText(String.valueOf(tiempo2 + " Milisegundos"));
                JOptionPane.showMessageDialog(this, "MOSTRANDO DATOS");
                executor.shutdown();
            }
        }

        // Unirse a un For Join
        if (e.getSource() == Btnn) {
            if (dataList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese al menos un dato", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int[] arr = new int[dataList.size()];
                for (int i = 0; i < dataList.size(); i++) {
                    arr[i] = dataList.get(i);
                }
                ForkJoinPool fjp = new ForkJoinPool();
                long tiempo5 = System.currentTimeMillis();
                fjp.invoke(new ForJoin(arr, 0, arr.length - 1));
                outputTextArea.setText(Arrays.toString(arr));
                long tiempo6 = System.currentTimeMillis() - tiempo5;
                txtlabel_3.setText(String.valueOf(tiempo6 + " Milisegundos"));
                JOptionPane.showMessageDialog(this, "MOSTRANDO DATOS");
            }
        }
    }

// Implementación del algoritmo Merge Sort
    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            MergeSortAlg mer = new MergeSortAlg();
            mer.merge(arr, l, m, r);
        }
    }
}
