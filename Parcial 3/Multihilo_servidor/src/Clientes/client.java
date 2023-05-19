package Clientes;

import java.util.ArrayList;

public class client {
    static final int MAX_HILOS = 10;
    public static final String HOST = "localhost";
    
    public static void main(String[] args) {
        ArrayList<Thread> client = new ArrayList<Thread>();
        for(int i = 0; i < 5; i++){
            client.add(new threadClient(i));
        }
        for(Thread thread:client){
            thread.start();
        }
    }
}
