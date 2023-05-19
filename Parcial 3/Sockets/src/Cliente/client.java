package Cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    
    public static final int PUERTO = 8080;
    public static final String Host = "localhost";
    
    public static void main(String[] args) throws IOException {
        System.out.println("addr = "+Host);
        Socket socket = new Socket(Host, PUERTO);
        try {
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                System.out.println("Usuario: "+"Hola server soy tu cliente que vale mil");
                String str = entrada.readLine();
                System.out.println(str);
                salida.println("FIN");
            } finally {
                System.out.println("Cerrando ...");
                socket.close();
            }
    }
    
}
