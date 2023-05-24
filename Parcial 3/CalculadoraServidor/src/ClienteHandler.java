import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteHandler extends Thread {
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    private static int contador = 0;
    private int id;
    private ClienteHandler otroClienteHandler;

    public ClienteHandler(Socket socket) {
        this.cliente = socket;
        this.id = ++contador;
        try {
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salida = new PrintWriter(cliente.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOtroClienteHandler(ClienteHandler otroClienteHandler) {
        this.otroClienteHandler = otroClienteHandler;
    }

    @Override
    public void run() {
        try {
            System.out.println("Cliente " + id + ": Esperando a que el otro usuario escriba un número.");

            // Leer el número del cliente
            String numero = entrada.readLine();
            System.out.println("Cliente " + id + ": Número ingresado: " + numero);

            // Notificar al otro cliente que puede continuar
            synchronized (otroClienteHandler) {
                otroClienteHandler.notifyAll();
            }

            // Leer el número del otro cliente
            String otroNumero = entrada.readLine();
            System.out.println("Cliente " + id + ": Número ingresado por el otro cliente: " + otroNumero);

            // Solicitar la operación al cliente
            salida.println("Ingrese la operación que desea realizar (suma, resta, multiplicacion, division):");
            String operacion = entrada.readLine();
            System.out.println("Cliente " + id + ": Operación ingresada: " + operacion);

            // Realizar la operación
            double resultado = 0.0;
            double num1 = Double.parseDouble(numero);
            double num2 = Double.parseDouble(otroNumero);

            switch (operacion.toLowerCase()) {
                case "suma":
                    resultado = num1 + num2;
                    break;
                case "resta":
                    resultado = num1 - num2;
                    break;
                case "multiplicacion":
                    resultado = num1 * num2;
                    break;
                case "division":
                    resultado = num1 / num2;
                    break;
                default:
                    salida.println("Operación no válida. Inténtelo de nuevo.");
                    break;
            }

            // Enviar el resultado al cliente
            salida.println("El resultado de la operación es: " + resultado);

            // Cerrar conexiones
            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
