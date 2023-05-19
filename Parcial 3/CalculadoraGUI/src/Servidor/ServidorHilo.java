package Servidor;

import Cliente.CalculadoraGUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServidorHilo implements Runnable {

    public Socket socket;

    public ServidorHilo(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            // Enviar la calculadora al cliente
            CalculadoraGUI calculadoraGUI = new CalculadoraGUI();
            outputStream.writeObject(calculadoraGUI);

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
