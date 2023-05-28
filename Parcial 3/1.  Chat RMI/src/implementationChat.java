import java.lang.System.Logger.Level;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Logger;
import Interfaces.chatCliente;
import Interfaces.chatServidor;

public class implementationChat extends UnicastRemoteObject implements chatServidor{
    public ArrayList<chatCliente> clientes;
    
    public implementationChat() throws RemoteException{
        clientes = new ArrayList<chatCliente>();
    }
    
    @Override
    public void mensaje(String mensaje) throws RemoteException{
        int a = 0;
        while(a < clientes.size()){
            try {
                clientes.get(a++).mensajeCliente(mensaje);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void registro(chatCliente cliente) throws RemoteException{
        this.clientes.add(cliente);
    }
}
