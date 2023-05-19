package Servidor;

import Interfaces.chatCliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import Interfaces.ChatServer;

public class implementacionChat extends UnicastRemoteObject implements  ChatServer {
    public ArrayList<chatCliente> clientes;
    
    public implementacionChat() throws RemoteException {
        clientes = new ArrayList<chatCliente>();
    }
    
    @Override
    public void mensaje(String mensaje) throws RemoteException {
        int a = 0;
        while (a < clientes.size()) {
            clientes.get(a++).mensajeCliente(mensaje);
        }
    }
    
    @Override
    public void registro(chatCliente cliente) throws  RemoteException {
        this.clientes.add(cliente);
    }
}
