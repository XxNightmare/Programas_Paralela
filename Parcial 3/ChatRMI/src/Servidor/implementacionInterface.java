package Servidor;


import Interfaces.interfaceRMI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class implementacionInterface extends UnicastRemoteObject implements interfaceRMI {
    public implementacionInterface() throws RemoteException{
        super();
    }
    
    public String nombre(String nombre) throws RemoteException {
        return nombre;
    }
}
