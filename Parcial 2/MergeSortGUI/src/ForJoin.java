import java.util.concurrent.RecursiveAction;

public class ForJoin extends RecursiveAction {
    int[] arr;
    int izquierda, derecha;

    public ForJoin(int[] arr, int izquier, int daderecha) {
        this.arr = arr;
        this.izquierda = izquier;
        this.derecha = daderecha;
    }
    
    @Override
    protected void compute() {
        if (izquierda < derecha) {
            int m = (izquierda + derecha) / 2;
            ForJoin izq = new ForJoin(arr, izquierda, m);
            ForJoin der = new ForJoin(arr, m + 1, derecha);
            invokeAll(izq, der);
            izq.join();
            der.join();
            MergeSortAlg mer = new MergeSortAlg();
            mer.merge(arr, izquierda, m, derecha);
        }
    }
}
