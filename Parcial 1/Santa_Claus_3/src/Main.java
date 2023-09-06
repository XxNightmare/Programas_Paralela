
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        int numElfos = 30;
        int numRenos = 9;

        // Crear una instancia de Santa_Claus pasando la referencia al JFrame
        Santa_Claus santa = new Santa_Claus(numElfos, numRenos);

        Thread santaThread = new Thread(santa);
        santaThread.start();

        Renos[] renos = new Renos[numRenos];
        for (int i = 0; i < numRenos; i++) {
            renos[i] = new Renos(i+1, santa);
            Thread renosThread = new Thread(renos[i]);
            renosThread.start();
        }

        Elfos[] elfos = new Elfos[numElfos];
        for (int i = 0; i < numElfos; i++) {
            elfos[i] = new Elfos(i+1, santa);
            Thread elfosThread = new Thread(elfos[i]);
            elfosThread.start();
        }
    }
}
