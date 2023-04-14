import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class PalindromeCounter extends RecursiveAction {

    private static final long serialVersionUID = 1L;
    private static final int THRESHOLD = 100;
    private final String text;
    private final int start;
    private final int end;
    private final AtomicInteger count;
    private final PalindromeRecorder recorder;

    public PalindromeCounter(String text, int start, int end, AtomicInteger count, PalindromeRecorder recorder) {
        this.text = text;
        this.start = start;
        this.end = end;
        this.count = count;
        this.recorder = recorder;
    }

    @Override
    protected void compute() {
        if (end - start <= THRESHOLD) {
            for (int i = start; i < end; i++) {
                for (int j = i + 1; j <= end; j++) {
                    String word = text.substring(i, j);
                    if (isPalindrome(word)) {
                        count.incrementAndGet();
                        recorder.record(word);
                    }
                }
            }
        } else {
            int mid = (start + end) / 2;
            PalindromeCounter left = new PalindromeCounter(text, start, mid, count, recorder);
            PalindromeCounter right = new PalindromeCounter(text, mid + 1, end, count, recorder);
            invokeAll(left, right);
        }
    }

    private static boolean isPalindrome(String word) {
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}
