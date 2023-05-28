import java.util.logging.Logger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

class Server {
    public static final int PUERTO = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        System.out.println("Inicializando servidor...");
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = serverSocket.accept();
                System.out.println("Nueva Conexion entrante: " + socket);
                new Thread(new ThreadServer(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
