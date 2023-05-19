package Clientes;

import Servidor.threadServidor;
import com.sun.istack.internal.logging.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class threadClient {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int id;
    
    public threadClient(int id){
        this.id = id;
    }
    
    public void run(){
        try {
            socket = new Socket("127.0.0.1", 8080);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            System.out.println(id + " envia saludos");
            dos.writeUTF("hola");
            String respuesta  = " ";
            respuesta = dis.readUTF();
            System.out.println(id + " Servidor devuelve saludo: "+ respuesta);
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(threadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
