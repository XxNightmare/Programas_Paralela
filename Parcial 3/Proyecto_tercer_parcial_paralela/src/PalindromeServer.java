import ExecuteService.Analisis_palindromos_executeService;
import ExecuteService.Analisis_texto_executeService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class PalindromeServer {

    // Puerto en el que el servidor escucha las conexiones
    private static final int PORT = 1025;

    // Contador de usuarios conectados
    private static int userCount = 0;

    // Lista que almacena los textos enviados por los clientes
    private static List<String> textosAlmacenados = new ArrayList<>();

    // Lista de PrintWriter de los clientes conectados
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    // Texto a actualizar en el servidor
    private static String textoActualizar;

    public static void main(String[] args) {
        try {
            // Crear un ServerSocket en el puerto especificado
            ServerSocket serverSocket = new ServerSocket(PORT);

            // Imprimir el mensaje de inicio del servidor
            System.out.println("Servidor Iniciado, Puerto: " + PORT);

            // Esperar conexiones entrantes de forma continua
            while (true) {
                // Aceptar la conexión entrante del cliente
                Socket clientSocket = serverSocket.accept();

                // Crear un objeto ClientHandler para manejar al cliente en un hilo separado
                ClientHandler clientHandler = new ClientHandler(clientSocket);

                // Crear y iniciar un nuevo hilo para manejar al cliente
                Thread thread = new Thread(clientHandler);
                thread.start();

                // Agregar el PrintWriter del cliente a la lista
                clientWriters.add(new PrintWriter(clientSocket.getOutputStream(), true));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase interna que maneja a un cliente en un hilo separado
    private static class ClientHandler implements Runnable {

        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                // Leer solicitud del cliente
                String command = in.readLine();
                String input = in.readLine();

                // Si el cliente solicita guardar un texto
                if ("GUARDAR_TEXTO".equals(command)) {
                    // Incrementar el contador de usuarios
                    userCount++;
                    System.out.println("Total usuarios registrados: " + userCount);

                    // Unir las oraciones almacenadas
                    String textoUnido = MetUnirOraciones(input);

                    // Actualizar el texto almacenado en el servidor
                    setTextoActualizar(textoUnido);

                    // Enviar el texto unido a todos los clientes conectados
                    for (PrintWriter writer : clientWriters) {
                        writer.println(textoUnido);
                    }
                }

                // Si el cliente solicita obtener el texto actualizado
                if ("UNION_TEXTO_GETTER".equals(command)) {
                    out.println(getTextoActualizar());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Método que une las oraciones almacenadas en el servidor
        private String MetUnirOraciones(String input) {
            textosAlmacenados.add(input);
            String textoUnido = String.join(" ", textosAlmacenados);
            return textoUnido;
        }
    }

    // Método getter para obtener el texto actualizado en el servidor
    public static String getTextoActualizar() {
        return textoActualizar;
    }

    // Método setter para actualizar el texto en el servidor
    public static void setTextoActualizar(String textoActualizar) {
        PalindromeServer.textoActualizar = textoActualizar;
    }
}