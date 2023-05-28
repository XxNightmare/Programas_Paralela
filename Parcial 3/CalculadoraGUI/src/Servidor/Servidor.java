package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static int resultado1 = 0;
    static int resultado2 = 0;

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

            OperacionesUsuario operacionesUsuario = new OperacionesUsuario(numero1, numero2);

            // Realizar la operación seleccionada por el usuario 1
            String operacion1 = operacionesUsuario.realizarOperacion(opcionUsuario1, 1);

            // Realizar la operación seleccionada por el usuario 2
            String operacion2 = operacionesUsuario.realizarOperacion(opcionUsuario2, 2);

            System.out.println(opcionUsuario1);
            System.out.println(opcionUsuario2);
            System.out.println(resultado1);
            System.out.println(resultado2);

            // Enviar el resultado de la operación al usuario 1
            salidaUsuario1.writeUTF(operacion1);

            // Enviar el resultado de la operación al usuario 2
            salidaUsuario2.writeUTF(operacion2);

            // Cerrar las conexiones
            usuario1.close();
            usuario2.close();
            serverSocket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class OperacionesUsuario {
    private int numero1;
    private int numero2;

    public OperacionesUsuario(int numero1, int numero2) {
        this.numero1 = numero1;
        this.numero2 = numero2;
    }

    public String realizarOperacion(int opcion, int usuario) {
        int resultado;

        if (opcion == 1) {
            resultado = numero1 + numero2;
        } else if (opcion == 2) {
            resultado = numero1 - numero2;
        } else if (opcion == 3) {
            resultado = numero1 * numero2;
        } else if (opcion == 4) {
            resultado = numero1 / numero2;
        } else {
            return "Operación inválida";
        }

        if (usuario == 1) {
            Servidor.resultado1 = resultado;
        } else if (usuario == 2) {
            Servidor.resultado2 = resultado;
        }

        return String.valueOf(resultado);
    }
}