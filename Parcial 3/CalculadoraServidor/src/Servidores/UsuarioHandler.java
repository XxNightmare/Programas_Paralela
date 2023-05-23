package Servidores;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class UsuarioHandler implements Runnable {
    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;

    public UsuarioHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.entrada = new DataInputStream(socket.getInputStream());
        this.salida = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            // Obtener el número del usuario
            int numero = entrada.readInt();
            System.out.println("Número recibido del usuario: " + numero);

            // Enviar el menú de operaciones al usuario
            salida.writeUTF("Elige una operación:\n1. Suma\n2. Resta\n3. Multiplicación\n4. División");
            int opcion = entrada.readInt();

            // Realizar la operación seleccionada
            int resultado = 0;
            String operacion = "";

            switch (opcion) {
                case 1:
                    resultado = numero + numero;
                    operacion = "Suma";
                    break;
                case 2:
                    resultado = numero - numero;
                    operacion = "Resta";
                    break;
                case 3:
                    resultado = numero * numero;
                    operacion = "Multiplicación";
                    break;
                case 4:
                    resultado = numero / numero;
                    operacion = "División";
                    break;
                default:
                    System.out.println("Opción inválida del usuario");
                    break;
            }

            // Enviar el resultado de la operación al usuario
            salida.writeUTF("El resultado de la " + operacion + " es: " + resultado);

            // Cerrar la conexión
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
