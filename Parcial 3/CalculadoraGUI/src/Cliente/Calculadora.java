package Cliente;

public class Calculadora {
    private double numero1;
    private char operacion;

    public void setNumero1(double numero1) {
        this.numero1 = numero1;
    }

    public void setOperacion(char operacion) {
        this.operacion = operacion;
    }

    public double calcularResultado(double numero2) {
        double resultado = 0;

        switch (operacion) {
            case '+':
                resultado = numero1 + numero2;
                break;
            case '-':
                resultado = numero1 - numero2;
                break;
            case '*':
                resultado = numero1 * numero2;
                break;
            case '/':
                resultado = numero1 / numero2;
                break;
        }

        return resultado;
    }
}
