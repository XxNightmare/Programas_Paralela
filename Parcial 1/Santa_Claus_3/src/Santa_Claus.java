
import java.awt.Color;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Santa_Claus implements Runnable {

    private int numElfosEsperando = 0;
    private int[] renosEsperando = new int[9];
    private Semaphore semaforo = new Semaphore(1);
    private int duendesAyudados = 0;
    private JFrame frame;

    public Santa_Claus(int numElfos, int numRenos) {
        if (numElfos < 3) {
            System.out.println("Se necesitan al menos 3 elfos para recibir ayuda de Santa.");
        }
        if (numRenos < 9) {
            System.out.println("Se necesitan al menos 9 renos para que Santa pueda volar su trineo.");
        }

        // Configurar la ventana de Swing
        frame = new JFrame("Santa's Workshop");
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    // Agregar un método para obtener la ventana de Santa_Claus
    public JFrame getFrame() {
        return frame;
    }

    // Agregar una función para mostrar un cuadrado verde en una posición aleatoria
    private void mostrarCuadrado(Color c) {
        // Obtener las dimensiones del panel
        int panelWidth = frame.getWidth();

        // Calcular coordenadas aleatorias dentro de los límites del panel
        int x = (int) (Math.random() * (panelWidth - 10));
        int y = (int) (Math.random() * (300 - 10));

        // Crear el cuadrado verde en las coordenadas calculadas
        JPanel greenSquare = new JPanel();
        greenSquare.setBackground(c);
        greenSquare.setBounds(x, y, 10, 10);

        frame.add(greenSquare);
        frame.revalidate();
        frame.repaint();
    }

    public void addElf() throws InterruptedException {
        Thread.sleep(1000);
        // Mostrar un cuadrado verde cuando se ayuda a un duende
        mostrarCuadrado(Color.GREEN);
        numElfosEsperando++;
    }

    public void addReno(int id) throws InterruptedException {
        Thread.sleep(1000);
        renosEsperando[id - 1] = 1;
        mostrarCuadrado(Color.BLUE);

    }

    public boolean canHelpElfs() {
        return numElfosEsperando >= 3;
    }

    public boolean canFlySleigh() {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += renosEsperando[i];
        }
        return sum == 9;
    }

    public void helpElfs() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Santa esta ayudando a los elfos.");
        for (int i=0; i<3; i++){
            mostrarCuadrado(Color.BLACK);
        }
        numElfosEsperando -= 3;
        System.out.println("Santa se fue a dormir.");
    }

    public void flySleigh() {
        System.out.println("*****************************************************");
        System.out.println("El trineo esta listo para salir, en espera de Santa.");
        System.out.println("Santa esta repartiendo regalos en su trineo.");
        for (int i = 0; i < 9; i++) {
            renosEsperando[i] = 0;
        }
        numElfosEsperando = 0;
        System.out.println("Santa ha regresado de estar repartiendo regalos en su trineo.");
        System.out.println("Los Renos se han ido de vacaciones");
        System.out.println("*****************************************************");
        // Eliminar todos los cuadrados verdes cuando se ayudan a 3 duendes
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
    }

    public Semaphore getSemaforo() {
        return semaforo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaforo.acquire();
                if (canFlySleigh()) {
                    flySleigh();
                } else if (canHelpElfs()) {
                    helpElfs();
                }
                semaforo.release();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
