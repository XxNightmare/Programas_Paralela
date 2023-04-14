
public class Santa_Claus implements Runnable {

    private int numElfosEsperando = 0;
    private int numRenosEsperando = 0;
    private boolean renosListos = false;
    private boolean elfosListos = false;
    private Object lock = new Object();

    public Santa_Claus(int numElfos, int numRenos) {
        if (numElfos < 3) {
            System.out.println("Se necesitan al menos 3 elfos para recibir ayuda de Santa.");
        }
        if (numRenos < 9) {
            System.out.println("Se necesitan al menos 9 renos para que Santa pueda volar su trineo.");
        }
    }

    public synchronized void addElf() {
        numElfosEsperando++;
    }

    public synchronized void addReno(int id) {
        numRenosEsperando++;
    }

    public synchronized boolean canHelpElfs() {
        return numElfosEsperando >= 3;
    }

    public synchronized boolean canFlySleigh() {
        return renosListos && elfosListos && numRenosEsperando == 9;
    }

    public synchronized void helpElfs() {
        System.out.println("Santa esta ayudando a los elfos.");
        numElfosEsperando -= 3;
        elfosListos = false;
    }

    public synchronized void flySleigh() {
        System.out.println("*****************************************************");
        System.out.println("El trineo esta listo para salir, en espera de Santa.");
        System.out.println("Santa esta repartiendo regalos en su trineo.");
        numRenosEsperando = 0;
        numElfosEsperando = 0;
        renosListos = false;
        elfosListos = false;
        System.out.println("Santa ha regresado de estar repartiendo regalos en su trineo.");
        System.out.println("Los Renos se han ido de vacaciones");
        System.out.println("*****************************************************");
    }

    public synchronized void elfsReady() {
        elfosListos = true;
        lock.notifyAll();
    }

    public synchronized void renosReady() {
        renosListos = true;
        lock.notifyAll();
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    while (!canFlySleigh()) {
                        lock.wait(); // Espera hasta que elfosListos y renosListos sean verdaderos
                    }
                    flySleigh(); // Vuela el trineo
                    lock.notifyAll(); // Notifica a los threads en espera en el lock
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
