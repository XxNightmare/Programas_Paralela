package Servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor implements CalculadoraRemota {

    private int usuario1;
    private int usuario2;
    private int numeroUsuario1;
    private int numeroUsuario2;

    public Servidor() throws RemoteException {
        super();
        usuario1 = 0;
        usuario2 = 0;
        numeroUsuario1 = 0;
        numeroUsuario2 = 0;
    }

    public static void main(String[] args) {
        try {
            Servidor servidor = new Servidor();
            CalculadoraRemota stub = (CalculadoraRemota) UnicastRemoteObject.exportObject(servidor, 0);

            // Obtener el registro RMI o crearlo si no existe
            Registry registry = null;
            try {
                registry = LocateRegistry.createRegistry(1099);
            } catch (RemoteException e) {
                registry = LocateRegistry.getRegistry(1099);
            }

            // Vincular el stub del servidor al registro
            registry.rebind("Calculadora", stub);

            System.out.println("Servidor iniciado. Esperando conexiones...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void conectar(int usuario) throws RemoteException {
        if (usuario == 1) {
            usuario1 = 1;
            System.out.println("Usuario 1 conectado.");
        } else if (usuario == 2) {
            usuario2 = 1;
            System.out.println("Usuario 2 conectado.");
        }

        if (usuario1 == 1 && usuario2 == 1) {
            System.out.println("Ambos usuarios conectados. Listos para realizar operaciones.");
        }
    }

    @Override
    public int getNumeroUsuario1() throws RemoteException {
        return numeroUsuario1;
    }

    @Override
    public int getNumeroUsuario2() throws RemoteException {
        return numeroUsuario2;
    }

    @Override
    public void setNumeroUsuario1(int numero) throws RemoteException {
        numeroUsuario1 = numero;
    }

    @Override
    public void setNumeroUsuario2(int numero) throws RemoteException {
        numeroUsuario2 = numero;
    }

    @Override
    public int sumar(int usuario, int numero) throws RemoteException {
        return numero + numero;
    }

    @Override
    public int restar(int usuario, int numero) throws RemoteException {
        return numero - numero;
    }

    @Override
    public int multiplicar(int usuario, int numero) throws RemoteException {
        return numero * numero;
    }

    @Override
    public int dividir(int usuario, int numero) throws RemoteException {
        return numero / numero;
    }
}
