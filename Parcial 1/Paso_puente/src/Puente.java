public class Puente {
    private int cochesNorte;
    private int cochesSur;

    public synchronized void cruzarNorte(int posicion) {
        while (cochesSur > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Coche Norte en posición " + posicion + " cruzando el puente");
        cochesNorte--;
        if (cochesNorte == 0) {
            notifyAll();
        }
    }

    public synchronized void cruzarSur(int posicion) {
        while (cochesNorte > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Coche Sur en posición " + posicion + " cruzando el puente");
        cochesSur--;
        if (cochesSur == 0) {
            notifyAll();
        }
    }

    public synchronized void llegarNorte() {
        cochesNorte++;
    }

    public synchronized void llegarSur() {
        cochesSur++;
    }
}
