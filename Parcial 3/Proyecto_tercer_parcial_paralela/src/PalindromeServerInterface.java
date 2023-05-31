import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PalindromeServerInterface extends Remote {

    void guardarTexto(String texto) throws RemoteException; // Método para guardar un texto en el servidor

    String obtenerTextoActualizado() throws RemoteException; // Método para obtener el texto actualizado del servidor
}
