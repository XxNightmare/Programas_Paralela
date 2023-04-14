public class CocheNorte extends Thread {
    private int posicion;
    private Puente puente;

    public CocheNorte(int posicion, Puente puente) {
        this.posicion = posicion;
        this.puente = puente;
    }

    public void run() {
        puente.llegarNorte();
        puente.cruzarNorte(posicion);
        System.out.println("Coche Norte en posición " + posicion + " ha cruzado el puente");
    }
}
