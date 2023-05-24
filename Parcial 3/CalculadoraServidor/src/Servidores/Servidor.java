package Servidores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
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

            // Establecer los flujos de entrada/salida para ambos usuarios
            DataInputStream entradaUsuario1 = new DataInputStream(usuario1.getInputStream());
            DataOutputStream salidaUsuario1 = new DataOutputStream(usuario1.getOutputStream());
            DataInputStream entradaUsuario2 = new DataInputStream(usuario2.getInputStream());
            DataOutputStream salidaUsuario2 = new DataOutputStream(usuario2.getOutputStream());

            // Obtener el primer número del usuario 1
            int numero1 = entradaUsuario1.readInt();
            System.out.println("Primer número recibido del usuario 1: " + numero1);

            // Obtener el primer número del usuario 2
            int numero2 = entradaUsuario2.readInt();
            System.out.println("Primer número recibido del usuario 2: " + numero2);

            // Obtener la opción de operación del usuario 1
            int opcionUsuario1 = entradaUsuario1.readInt();

            // Obtener la opción de operación del usuario 2
            int opcionUsuario2 = entradaUsuario2.readInt();

            // Realizar la operación seleccionada por el usuario 1
            int resultado1 = 0;
            String operacion1 = "";

            switch (opcionUsuario1) {
                case 1:
                    resultado1 = numero1 + numero2;
                    operacion1 = "Suma";
                    break;
                case 2:
                    resultado1 = numero1 - numero2;
                    operacion1 = "Resta";
                    break;
                case 3:
                    resultado1 = numero1 * numero2;
                    operacion1 = "Multiplicación";
                    break;
                case 4:
                    resultado1 = numero1 / numero2;
                    operacion1 = "División";
                    break;
                default:
                    System.out.println("Opción inválida del usuario 1");
                    break;
            }

            // Realizar la operación seleccionada por el usuario 2
            int resultado2 = 0;
            String operacion2 = "";

            switch (opcionUsuario2) {
                case 1:
                    resultado2 = numero1 + numero2;
                    operacion2 = "Suma";
                    break;
                case 2:
                    resultado2 = numero1 - numero2;
                    operacion2 = "Resta";
                    break;
                case 3:
                    resultado2 = numero1 * numero2;
                    operacion2 = "Multiplicación";
                    break;
                case 4:
                    resultado2 = numero1 / numero2;
                    operacion2 = "División";
                    break;
                default:
                    System.out.println("Opción inválida del usuario 2");
                    break;
            }

            // Enviar el resultado de la operación a ambos usuarios
            salidaUsuario1.writeUTF(" " + resultado1);
            salidaUsuario2.writeUTF(" " + resultado2);

            // Cerrar conexiones
            usuario1.close();
            usuario2.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
