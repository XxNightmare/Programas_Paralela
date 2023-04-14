
public class Renos implements Runnable {

    private int id;
    private Santa_Claus santa;
    private boolean waiting;

    public Renos(int id, Santa_Claus santa) {
        this.id = id;
        this.santa = santa;
        this.waiting = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                synchronized (santa) {
                    santa.addReno(id); // Añade al reno a la lista de espera de Santa
                    System.out.println("Reno " + id + " esta esperando por la señal de Santa.");

                    while (!santa.canFlySleigh() && !waiting) {
                        santa.wait(); // Espera la señal de Santa
                    }

                    if (santa.canFlySleigh()) {
                        waiting = true;
                        santa.flySleigh(); // Si hay 9 renos en la lista, Santa los lleva a volar el trineo
                        waiting = false;
                        santa.notifyAll(); // Despierta a todos los hilos en espera
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
