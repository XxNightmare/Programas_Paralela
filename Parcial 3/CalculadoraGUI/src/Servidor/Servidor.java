package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 1025;

        try {
            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor Activo");

            while (true) {
                Socket socket = serverSocket.accept();
                Thread hilo = new Thread(new ServidorHilo(socket));
                hilo.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
