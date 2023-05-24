import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1025);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            // Esperar a que se conecten dos clientes
            Socket cliente1 = serverSocket.accept();
            System.out.println("Cliente 1 conectado.");
            Socket cliente2 = serverSocket.accept();
            System.out.println("Cliente 2 conectado.");

            // Crear hilos para manejar las conexiones de los clientes
            ClienteHandler clienteHandler1 = new ClienteHandler(cliente1);
            ClienteHandler clienteHandler2 = new ClienteHandler(cliente2);

            // Establecer la referencia mutua entre los hilos
            clienteHandler1.setOtroClienteHandler(clienteHandler2);
            clienteHandler2.setOtroClienteHandler(clienteHandler1);

            // Iniciar los hilos
            clienteHandler1.start();
            clienteHandler2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
