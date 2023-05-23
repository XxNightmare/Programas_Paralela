package Servidores;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1025);
            System.out.println("Servidor iniciado. Esperando conexiones...");

            // Esperar la conexión del primer usuario
            Socket usuario1 = serverSocket.accept();
            System.out.println("Primer usuario conectado");

            // Esperar la conexión del segundo usuario
            Socket usuario2 = serverSocket.accept();
            System.out.println("Segundo usuario conectado");

            // Crear instancias de las clases para manejar las conexiones de los usuarios
            UsuarioHandler usuario1Handler = new UsuarioHandler(usuario1);
            UsuarioHandler usuario2Handler = new UsuarioHandler(usuario2);

            // Iniciar los hilos para manejar las conexiones de los usuarios
            Thread usuario1Thread = new Thread(usuario1Handler);
            Thread usuario2Thread = new Thread(usuario2Handler);

            usuario1Thread.start();
            usuario2Thread.start();

            // Esperar a que ambos hilos terminen antes de cerrar el servidor
            usuario1Thread.join();
            usuario2Thread.join();

            // Cerrar el servidor
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
