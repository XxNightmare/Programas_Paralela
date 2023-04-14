
public class Canibales {

    public static void main(String[] args) {
        int cantidad_canivales = 5;
        Cocinero cocinero = new Cocinero();
        Canibal[] canibales = new Canibal[cantidad_canivales];

        for (int i = 0; i < cantidad_canivales; i++) {
            canibales[i] = new Canibal(cocinero);
            canibales[i].start();
        }

        cocinero.start();
        try {
            cocinero.join();
        } catch (Exception e) {
        }
    }
}
