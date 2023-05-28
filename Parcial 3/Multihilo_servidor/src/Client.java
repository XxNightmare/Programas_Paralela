

import java.util.ArrayList;

public class Client {
    static final int MAX_HILOS = 10;
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 5; i++) {
            clients.add(new Thread(new ThreadClient(i)));
        }
        for (Thread thread : clients) {
            thread.start();
        }
    }
}
