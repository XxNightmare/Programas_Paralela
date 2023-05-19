package Clientes;

import Interfaces.chatCliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import org.omg.CORBA.portable.RemarshalException;
import Interfaces.ChatServer;

public class implementacionClienteChat extends UnicastRemoteObject implements chatCliente, Runnable {
    ChatServer servidor;
    public String nombre = null;
    
    implementacionClienteChat(String nombre, ChatServer servidor) throws RemoteException {
        this.nombre = nombre;
        this.servidor = servidor;
        servidor.registro(this);
    }
    
    public void mensajeCliente(String mensaje) throws RemoteException {
        System.out.println(mensaje);
    }
    
    @Override
    public void run(){
        Scanner s = new Scanner(System.in);
        String mensaje;
        
        while (true) {
            mensaje = s.nextLine();
            try {
                servidor.mensaje(nombre+": "+mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
