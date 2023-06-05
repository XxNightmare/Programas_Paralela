import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PalindromeServer extends UnicastRemoteObject implements PalindromeServerInterface {

    private static final int PORT = 1025; // Puerto en el que se va a escuchar las conexiones
    private static int userCount = 0; // Contador de usuarios registrados
    private static List<String> textosAlmacenados = new ArrayList<>(); // Lista para almacenar los textos recibidos
    private static String textoActualizar; // Texto actualizado a enviar a los clientes

    public PalindromeServer() throws RemoteException {
    }

    public static void main(String[] args) {
        try {
            PalindromeServer server = new PalindromeServer(); // Crea una instancia del servidor
            Registry registry = LocateRegistry.createRegistry(PORT); // Crea un registro RMI en el puerto especificado
            registry.rebind("PalindromeServer", server); // Vincula el servidor al registro con el nombre "PalindromeServer"
            System.out.println("Servidor Iniciado, Puerto: " + PORT); // Imprime un mensaje de inicio del servidor
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void guardarTexto(String texto) throws RemoteException {
        userCount++; // Incrementa el contador de usuarios registrados
        System.out.println("Total usuarios registrados: " + userCount); // Imprime el número de usuarios registrados
        String textoUnido = MetUnirOraciones(texto); // Une el texto recibido con los textos almacenados previamente
        setTextoActualizar(textoUnido); // Establece el texto actualizado a enviar a los clientes
    }

    @Override
    public String obtenerTextoActualizado() throws RemoteException {
        return getTextoActualizar(); // Obtiene el texto actualizado para ser enviado al cliente que lo solicita
    }

    private String MetUnirOraciones(String input) {
        textosAlmacenados.add(input); // Agrega el texto recibido a la lista de textos almacenados
        String textoUnido = String.join(" ", textosAlmacenados); // Une todos los textos almacenados separados por un espacio
        return textoUnido; // Devuelve el texto unido
    }

    public static String getTextoActualizar() {
        return textoActualizar; // Obtiene el texto actualizado
    }

    public static void setTextoActualizar(String textoActualizar) {
        PalindromeServer.textoActualizar = textoActualizar; // Establece el texto actualizado
    }
}
