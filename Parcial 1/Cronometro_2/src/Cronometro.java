public class Cronometro {
    private long tiempo;
    private int milisegundos;
    private int segundos;
    private int minutos;

    public Cronometro() {
        this.tiempo = 0;
        this.milisegundos = 0;
        this.segundos = 0;
        this.minutos = 0;
    }

    public void iniciar() {
        Thread hilo = new Thread(new Hilo());
        hilo.start();
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class Hilo implements Runnable {
        @Override
        public void run() {
            while (minutos < 2) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tiempo++;
                actualizarTiempo();
                System.out.printf("%02d:%02d:%02d\n", minutos, segundos, milisegundos/10);
            }
        }
    }

    private void actualizarTiempo() {
        milisegundos = (int) (tiempo % 1000);
        segundos = (int) ((tiempo / 1000) % 60);
        minutos = (int) (tiempo / 60000);
    }
}
