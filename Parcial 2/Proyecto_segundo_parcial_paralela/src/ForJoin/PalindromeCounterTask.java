
package ForJoin;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PalindromeCounterTask extends RecursiveTask<PalindromeResult> {
    private static final int THRESHOLD = 10;
    private final String text;
    private final int start;
    private final int end;
    
    public PalindromeCounterTask(String text) {
        this(text, 0, text.length());
    }
    
    public PalindromeCounterTask(String text, int start, int end) {
        this.text = text;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected PalindromeResult compute() {
        if (end - start <= THRESHOLD) {
            return countPalindromesSequentially();
        }
        
        int mid = start + (end - start) / 2;
        PalindromeCounterTask leftTask = new PalindromeCounterTask(text, start, mid);
        PalindromeCounterTask rightTask = new PalindromeCounterTask(text, mid, end);
        leftTask.fork();
        PalindromeResult rightResult = rightTask.compute();
        PalindromeResult leftResult = leftTask.join();
        return mergeResults(leftResult, rightResult);
    }
    
    private PalindromeResult countPalindromesSequentially() {
        PalindromeResult result = new PalindromeResult();
        String[] words = text.substring(start, end).split("\\s+");
        for (String word : words) {
            if (PalindromeChecker.isPalindrome(word)) {
                result.add(word);
            }
        }
        return result;
    }
    
    private PalindromeResult mergeResults(PalindromeResult left, PalindromeResult right) {
        left.add(right);
        return left;
    }
}
