import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Santa_Claus implements Runnable {
    private int numElfosEsperando = 0;
    private int[] renosEsperando = new int[9];
    private Semaphore semaforo = new Semaphore(1);

    public Santa_Claus(int numElfos, int numRenos) {
        if (numElfos < 3) {
            System.out.println("Se necesitan al menos 3 elfos para recibir ayuda de Santa.");
        }
        if (numRenos < 9) {
            System.out.println("Se necesitan al menos 9 renos para que Santa pueda volar su trineo.");
        }
    }

    public void addElf() {
        numElfosEsperando++;
    }

    public void addReno(int id) {
        renosEsperando[id-1] = 1;
    }

    public boolean canHelpElfs() {
        return numElfosEsperando >= 3;
    }

    public boolean canFlySleigh() {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += renosEsperando[i];
        }
        return sum == 9;
    }

    public void helpElfs() {
        System.out.println("Santa esta ayudando a los elfos.");
        numElfosEsperando -= 3;
    }

    public void flySleigh() {
        System.out.println("*****************************************************");
        System.out.println("El trineo esta listo para salir, en espera de Santa.");
        System.out.println("Santa esta repartiendo regalos en su trineo.");
        for (int i = 0; i < 9; i++) {
            renosEsperando[i] = 0;
        }
        numElfosEsperando = 0;
        System.out.println("Santa ha regresado de estar repartiendo regalos en su trineo.");
        System.out.println("Los Renos se han ido de vacaciones");
        System.out.println("*****************************************************");
    }

    public Semaphore getSemaforo() {
        return semaforo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaforo.acquire();
                if (canFlySleigh()) {
                    flySleigh();
                } else if (canHelpElfs()) {
                    helpElfs();
                }
                semaforo.release();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
