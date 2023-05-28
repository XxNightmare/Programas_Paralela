import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import Interfaces.chatServidor;

public class clienteRMI {
    public static void main(String[] args) {
        try {
            String nombre = JOptionPane.showInputDialog("Ingresa tu nombre");
            String nom = nombre;

            Registry rmi = LocateRegistry.getRegistry("192.168.137.205", 1025);
            chatServidor servidor = (chatServidor) rmi.lookup("Chat");
            new Thread(new implementationClienteChat(nom, servidor)).start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
