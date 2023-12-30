package Calculadora;

public class metodos implements implementacion {

    @Override
    public double suma(double a, double b) {
        return a + b;
    }

    @Override
    public double resta(double a, double b) {
        return a - b;
    }

    @Override
    public double division(double a, double b) {
        return (double) a / b;
    }

    @Override
    public double multiplicacion(double a, double b) {
        return a * b;
    }

    @Override
    public double potencia(double a, double b) {
        return Math.pow(a, b);
    }

    @Override
    public double raiz(double a) {
        return Math.sqrt(a);
    }

    @Override
    public double modulo(double a, double b) {
        return a % b;
    }
}
