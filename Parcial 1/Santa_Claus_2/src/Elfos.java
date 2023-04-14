import java.util.concurrent.Semaphore;

public class Elfos implements Runnable {
    private int id;
    private Santa_Claus santa;
    private Semaphore semaforo;

    public Elfos(int id, Santa_Claus santa) {
        this.id = id;
        this.santa = santa;
        this.semaforo = santa.getSemaforo();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                semaforo.acquire(); // Adquiere el semaforo para garantizar la sincronización

                santa.addElf(); // Añade al elfo a la lista de espera de Santa
                System.out.println("Elfo " + id + " esta esperando por la ayuda de Santa.");

                if (santa.canHelpElfs()) {
                    santa.helpElfs(); // Si hay 3 elfos en la lista, Santa los ayuda
                }

                semaforo.release(); // Libera el semaforo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
