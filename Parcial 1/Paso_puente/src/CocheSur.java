public class CocheSur extends Thread {

    private int posicion;
    private Puente puente;

    public CocheSur(int posicion, Puente puente) {
        this.posicion = posicion;
        this.puente = puente;
    }

    public void run() {
        puente.llegarSur();
        puente.cruzarSur(posicion);
        System.out.println("Coche Sur en posición " + posicion + " ha cruzado el puente");
    }
}
