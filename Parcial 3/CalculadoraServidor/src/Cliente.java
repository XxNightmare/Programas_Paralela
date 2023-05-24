import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            // Conectarse al servidor en el puerto 1025
            Socket socket = new Socket("localhost", 1025);

            // Obtener los flujos de entrada y salida del socket
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Leer el número y enviarlo al servidor
            BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese un número: ");
            String numero = lector.readLine();
            salida.println(numero);

            // Leer el mensaje del servidor y esperar
            String mensaje = entrada.readLine();
            System.out.println("Servidor: " + mensaje);

            // Leer el número del otro cliente y enviarlo al servidor
            System.out.print("Ingrese el número del otro cliente: ");
            String otroNumero = lector.readLine();
            salida.println(otroNumero);

            // Leer la operación y enviarla al servidor
            System.out.print("Ingrese la operación que desea realizar: ");
            String operacion = lector.readLine();
            salida.println(operacion);

            // Leer el resultado del servidor
            String resultado = entrada.readLine();
            System.out.println("Servidor: " + resultado);

            // Cerrar conexiones
            entrada.close();
            salida.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
