import ExecuteService.Analisis_palindromos_executeService;
import ForJoin.Analisis_palindromos_forkjoin;
import ForJoin.Analisis_texto_forkjoin;
import Normal.Analisis_palindromos_normal;
import Normal.Analisis_texto_normal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1025;
    private PalindromeServerInterface server;
    private String textoUnido = "";

    private JLabel inputLabel, titleLabel, outputLabel, txtlabel, txtNormal, txtExecuteService, txtForJoin, txtEspaciado;
    private JTextArea Cant_pal_enc, pal_enc, inputTextArea, TextAreaArreglo, Oracion_unida;
    private JButton NormalButton, clearButton, executeButton, joinButton, GuardarButton, ActualizarButton;

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
        outputLabel = new JLabel("Oracion unida:");
        outputLabel.setFont(new Font("Serif", Font.BOLD, 17));
        panel.add(outputLabel);

        Oracion_unida = new JTextArea();
        Oracion_unida.enable(false);
        Oracion_unida.setFont(new Font("Serif", Font.PLAIN, 25));
        JScrollPane scrollPane4 = new JScrollPane(Oracion_unida);
        panel.add(scrollPane4);
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
        // ------------------------ Configuración de los botones -------------------------
        // Boton Unir oracion
        GuardarButton = new JButton("Unir oracion");
        GuardarButton.setFont(new Font("Serif", Font.PLAIN, 17));
        GuardarButton.addActionListener(this);
        panel.add(GuardarButton);

        // Boton Actualizar
        ActualizarButton = new JButton("Actualizar");
        ActualizarButton.setFont(new Font("Serif", Font.PLAIN, 17));
        ActualizarButton.addActionListener(this);
        panel.add(ActualizarButton);

        // Boton Ejecutar el metodo Normal
        NormalButton = new JButton("Metodo normal");
        NormalButton.setFont(new Font("Serif", Font.PLAIN, 17));
        NormalButton.addActionListener(this);
        panel.add(NormalButton);

        // Boton Limpiar
        clearButton = new JButton("Limpiar");
        clearButton.setFont(new Font("Serif", Font.PLAIN, 17));
        clearButton.addActionListener(this);
        panel.add(clearButton);

        // Boton Execute Service
        executeButton = new JButton("Execute Service");
        executeButton.setFont(new Font("Serif", Font.PLAIN, 17));
        executeButton.addActionListener(this);
        panel.add(executeButton);

        // Boton For Join
        joinButton = new JButton("For Join");
        joinButton.setFont(new Font("Serif", Font.PLAIN, 17));
        joinButton.addActionListener(this);
        panel.add(joinButton);
        // -------------------------------------------------------------------------------
        // ------------------------ Configuración de Label -------------------------
        // Configuración del label de salida
        //txtEspaciado = new JLabel();
        //txtEspaciado.setFont(new Font("Serif", Font.PLAIN, 20));
        //panel.add(txtEspaciado);
        // Tiempo normal
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

        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_ADDRESS, SERVER_PORT);
            server = (PalindromeServerInterface) registry.lookup("PalindromeServer");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // Resto del código para iniciar la interfaz de usuario
        Main mergeSortGUI = new Main();
        mergeSortGUI.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Normal
        if (e.getSource() == NormalButton) {
            long tiempo1 = System.currentTimeMillis();
            String input = Oracion_unida.getText().trim();
            System.out.println(input);
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
            String input = Oracion_unida.getText().trim();
            System.out.println(input);
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
            System.out.println(Texto);
            Cant_pal_enc.setText(String.valueOf(cont));
            if (cont > 0) {
                pal_enc.setText(Texto);
                txtExecuteService.setText(String.valueOf(tiempo2 / 2 + " Milisegundos"));
            } else {
                pal_enc.setText("ninguna");
                JOptionPane.showMessageDialog(this, "No hay palindromos en esta oracion", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // For Join
        if (e.getSource() == joinButton) {
            String input = Oracion_unida.getText().trim();
            System.out.println(input);
            long tiempo1 = System.currentTimeMillis();
            Analisis_texto_forkjoin analyzerfor = new Analisis_texto_forkjoin();
            // Separa el texto en palabras y las almacena en una lista
            java.util.List<String> words = analyzerfor.getWords(input);

            Analisis_palindromos_forkjoin analisis = new Analisis_palindromos_forkjoin();
            java.util.List<String> palabras = new ArrayList<>(words);
            java.util.List<String> palindromos = analisis.getPalindromeWords(palabras);

            Cant_pal_enc.setText(String.valueOf(palindromos.size()));
            if (String.valueOf(palindromos).isEmpty()) {
                pal_enc.setText(String.valueOf(palindromos));
                long tiempo2 = System.currentTimeMillis() - tiempo1;
                txtForJoin.setText(String.valueOf(tiempo2 + " Milisegundos"));
            } else {
                pal_enc.setText("ninguna");
                JOptionPane.showMessageDialog(this, "No hay palindromos en esta oracion", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Guardar
        if (e.getSource() == GuardarButton) {
            String input = inputTextArea.getText().trim();
            try {
                server.guardarTexto(input);
                Oracion_unida.setText(server.obtenerTextoActualizado());
                JOptionPane.showMessageDialog(null, "Información almacenada");
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }

        // Actualizar
        if (e.getSource() == ActualizarButton) {
            try {
                // Obtener el texto actualizado del servidor
                String textoActualizado = server.obtenerTextoActualizado();

                // Mostrar el texto actualizado en el JTextArea
                Oracion_unida.setText(textoActualizado);
                JOptionPane.showMessageDialog(null, "Información actualizada");
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }
}
