package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class ManejadorCliente extends Thread {
    private Socket socket;
    private int numeroCliente;

    public ManejadorCliente(Socket socket, int numeroCliente) {
        this.socket = socket;
        this.numeroCliente = numeroCliente;
    }

    public void run() {
        try {
            DataInputStream entradaUsuario = new DataInputStream(socket.getInputStream());
            DataOutputStream salidaUsuario = new DataOutputStream(socket.getOutputStream());

            // Obtener el primer número del usuario
            int numero1 = entradaUsuario.readInt();
            System.out.println("Primer número recibido del usuario " + numeroCliente + ": " + numero1);

            // Obtener el segundo número del usuario
            int numero2 = entradaUsuario.readInt();
            System.out.println("Segundo número recibido del usuario " + numeroCliente + ": " + numero2);

            // Obtener la opción de operación del usuario
            int opcionUsuario = entradaUsuario.readInt();

            // Realizar la operación seleccionada por el usuario
            int resultado = realizarOperacion(numero1, numero2, opcionUsuario);

            // Enviar el resultado de la operación al usuario
            salidaUsuario.writeInt(resultado);

            // Cerrar la conexión con el usuario
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int realizarOperacion(int numero1, int numero2, int opcion) {
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
            resultado = 0;
        }

        return resultado;
    }
}