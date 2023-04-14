
import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        segunda_clase s = new implementacion();
        Scanner scan = new Scanner(System.in);
        int valor = 0;
        while (valor != 7) {
            System.out.println("Que quieres hacer?");
            System.out.println("1) SUMA");
            System.out.println("2) RESTA");
            System.out.println("3) RESTAMULTIPLICACION");
            System.out.println("4) DIVISION");
            System.out.println("5) POTENCIA");
            System.out.println("6) MODULO");
            System.out.println("7) SALIR");
            System.out.print("Respuesta: ");
            valor = scan.nextInt();
            switch (valor) {
                case 1:
                    System.out.println(s.suma(10, 15));
                    break;
                case 2:
                    System.out.println(s.resta(8, 7));
                    break;
                case 3:
                    System.out.println(s.mult(2, 5));
                    break;
                case 4:
                    System.out.println(s.div(4, 2));
                    break;
                case 5:
                    System.out.println(s.pot(2, 4));
                    break;
                case 6:
                    System.out.println(s.mot(8, 5));
                    break;
                default:
                    break;
            }
        }
    }
}
