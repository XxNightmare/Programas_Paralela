import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Interfaces.interfaceRMI;

public class implementationInterface extends UnicastRemoteObject implements interfaceRMI{
    public implementationInterface() throws RemoteException{
        super();
    }

    @Override
    public String nombre(String nombre) throws RemoteException {
        return nombre;
    }
}