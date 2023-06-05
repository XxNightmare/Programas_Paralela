package Servidor;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalculadoraRemota extends Remote {
    void conectar(int usuario) throws RemoteException;
    int getNumeroUsuario1() throws RemoteException;
    int getNumeroUsuario2() throws RemoteException;
    void setNumeroUsuario1(int numero) throws RemoteException;
    void setNumeroUsuario2(int numero) throws RemoteException;
    int sumar(int usuario, int numero) throws RemoteException;
    int restar(int usuario, int numero) throws RemoteException;
    int multiplicar(int usuario, int numero) throws RemoteException;
    int dividir(int usuario, int numero) throws RemoteException;
}
