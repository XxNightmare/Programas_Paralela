public class implementacion implements segunda_clase {
    public int suma(int a, int b) {
        int sum = 0;
        sum = a + b;
        return sum;
    }

    public int resta(int a, int b) {
        int sum = 0;
        sum = a - b;
        return sum;
    }

    public int mult(int a, int b) {
        int sum = 0;
        sum = a * b;
        return sum;
    }

    public float div(int a, int b) {
        int sum = 0;
        sum = a / b;
        return sum;
    }

    public double pot(double a, double b) {
        double sum = 0;
        sum = Math.pow(a, b);
        return sum;
    }

    public float mot(int a, int b) {
        int sum = 0;
        sum = a % b;
        return sum;
    }

}
