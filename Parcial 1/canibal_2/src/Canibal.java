class Canibal extends Thread {
    private Cocinero cocinero;
    private int comidaAcumulada;
    
    public Canibal(Cocinero cocinero) {
        this.cocinero = cocinero;
        this.comidaAcumulada = 0;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                comidaAcumulada += cocinero.tomarComida();
                System.out.println("Caníbal " + Thread.currentThread().getId() + ": He comido una porción.");
                if (comidaAcumulada == 0) {
                    cocinero.informarComidaAgotada();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
