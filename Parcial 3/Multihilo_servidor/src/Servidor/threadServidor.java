package Servidor;

import com.sun.istack.internal.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class threadServidor {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSesion;

    public threadServidor(Socket socket, int idSesion) {
        this.socket = socket;
        this.idSesion = idSesion;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconectar(){
        try {
            System.out.println("Desconectando...");
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        String accion = "";
        try {
            accion = dis.readUTF();
            if(accion.equals("hola")){
                System.out.println("El cliente con idSesion "+this.idSesion+ "Saluda");
                dos.writeUTF("Adios");
            }
        } catch (IOException ex) {
            Logger.getLogger(threadServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        desconectar();
    }

}
