import java.util.Random;

class Cocinero extends Thread {

    private int comida;
    private final Random rand;
    private boolean comidaAgotada;

    public Cocinero() {
        this.comida = 0;
        this.rand = new Random();
        this.comidaAgotada = false;
    }

    public synchronized void run() {
        while (true) {
            try {
                while (comida > 0 || comidaAgotada) {
                    wait();
                }
                Thread.sleep(1000);
                System.out.println("Cocinero: No hay comida. Voy a preparar más.");
                comida = rand.nextInt(10) + 1;
                System.out.println("Cocinero: He preparado " + comida + " porciones de comida.");
                comidaAgotada = false;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized int tomarComida() {
        while (comida == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        comida--;
        if (comida == 0) {
            notifyAll();
        }
        return 1;
    }

    public synchronized void informarComidaAgotada() {
        comidaAgotada = true;
    }
}
