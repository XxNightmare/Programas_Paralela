import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuteService implements Callable<int[]> {

    private final int[] arr;

    public ExecuteService(int[] arr) {
        this.arr = arr;
    }

    public int[] call() {
        mergeSort(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            MergeSortAlg mer = new MergeSortAlg();
            mer.merge(arr, l, m, r);
        }
    }
}
