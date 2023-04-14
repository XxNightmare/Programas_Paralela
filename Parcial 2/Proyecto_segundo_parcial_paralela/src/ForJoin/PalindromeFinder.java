import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

public class PalindromeFinder {

    public static void main(String[] args) {
        String text = "anitalavalatina";
        AtomicInteger count = new AtomicInteger(0);
        PalindromeRecorder recorder = new PalindromeRecorder();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new PalindromeCounter(text, 0, text.length() - 1, count, recorder));
        System.out.println("Number of palindromes: " + count.get());
        System.out.println("Palindromes found: " + recorder.getRecord());
    }

}
