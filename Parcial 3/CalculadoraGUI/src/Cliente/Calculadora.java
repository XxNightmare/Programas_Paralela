package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Calculadora {

    private DataInputStream entrada;
    private DataOutputStream salida;
    private Socket socket;
    private CalculadoraGUI cliente;

    public Calculadora() {
        try {
            socket = new Socket("localhost", 1025);
            System.out.println("Conectado al servidor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCliente(CalculadoraGUI cliente) {
        this.cliente = cliente;
    }

    public void realizarOperacion(String textoBoton, int numero) {
        int opcionSeleccionada = obtenerOpcion(textoBoton);

        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());

            salida.writeInt(numero);
            salida.writeInt(opcionSeleccionada);

            String resultado = entrada.readUTF();
            cliente.mostrarResultado(resultado);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int obtenerOpcion(String textoBoton) {
        if (textoBoton.equals("+")) {
            return 1;
        } else if (textoBoton.equals("-")) {
            return 2;
        } else if (textoBoton.equals("*")) {
            return 3;
        } else if (textoBoton.equals("/")) {
            return 4;
        } else {
            return 0;
        }
    }
}
