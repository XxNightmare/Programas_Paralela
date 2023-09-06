import java.util.concurrent.Semaphore;

public class Renos implements Runnable {
    private int id;
    private Santa_Claus santa;
    private Semaphore semaforo;

    public Renos(int id, Santa_Claus santa) {
        this.id = id;
        this.santa = santa;
        this.semaforo = santa.getSemaforo();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // Espera aleatoria de cada reno
                semaforo.acquire(); // Adquiere el semaforo para garantizar la sincronización

                
                santa.addReno(id); // Añade al reno a la lista de espera de Santa
                System.out.println("Ha llegado el Reno " + id + ".");

                if (santa.canFlySleigh()) {
                    santa.flySleigh(); // Si ya han llegado los 9 renos, Santa sale a repartir regalos
                }

                semaforo.release(); // Libera el semaforo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }    
}