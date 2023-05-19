package Clientes;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import Interfaces.ChatServer;

public class clienteRMI {
    public static void main(String[] args) {
        try {
            String nombre = JOptionPane.showInputDialog("Ingresa tu nombre");
            String nom = nombre;
            
            Registry rmii = LocateRegistry.getRegistry("localhost", 1025);
            ChatServer servidor = (ChatServer) rmii.lookup("Chat");
            new Thread(new implementacionClienteChat(nom, servidor)).start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
