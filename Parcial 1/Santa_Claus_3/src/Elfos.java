public class Elfos implements Runnable {
    private int id;
    private Santa_Claus santa;
    private boolean waiting;

    public Elfos(int id, Santa_Claus santa) {
        this.id = id;
        this.santa = santa;
        this.waiting = false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);

                synchronized(santa) {
                    santa.addElf(); // Añade al elfo a la lista de espera de Santa
                    System.out.println("Elfo " + id + " esta esperando por la ayuda de Santa.");

                    while (!santa.canHelpElfs() && !waiting) {
                        santa.wait(); // Espera la ayuda de Santa
                    }

                    if (santa.canHelpElfs()) {
                        waiting = true;
                        santa.helpElfs(); // Si hay 3 elfos en la lista, Santa los ayuda
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
