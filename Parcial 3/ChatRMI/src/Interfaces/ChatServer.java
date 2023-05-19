package Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote{
    void registro(chatCliente cliente) throws RemoteException;
    void mensaje(String mensaje) throws RemoteException;
}
