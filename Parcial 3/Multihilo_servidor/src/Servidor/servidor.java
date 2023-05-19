package Servidor;

import com.sun.istack.internal.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

public class servidor {

    public static final int PUERTO = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket S;
        System.out.println("Inicializando servidor...");
        try {
            S = new ServerSocket(PUERTO);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = S.accept();
                System.out.println("Nueva Conexion entrante: " + socket);
                ((threadServidor) new threadServidor(socket, idSession)).run();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
