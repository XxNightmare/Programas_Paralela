package Cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 1025;

        try {
            Socket socket = new Socket(HOST, PUERTO);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            // Recibir la calculadora desde el servidor
            CalculadoraGUI calculadoraGUI = (CalculadoraGUI) inputStream.readObject();

            // Mostrar la calculadora
            calculadoraGUI.setVisible(true);

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
