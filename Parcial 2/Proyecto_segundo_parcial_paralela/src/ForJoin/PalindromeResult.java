package ForJoin;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PalindromeResult {
    private final ConcurrentHashMap<String, Integer> wordCount;
    private final ConcurrentLinkedDeque<String> words;
    
    public PalindromeResult() {
        this.wordCount = new ConcurrentHashMap<>();
        this.words = new ConcurrentLinkedDeque<>();
    }
    
    public void add(String word) {
        wordCount.compute(word, (k, v) -> v == null ? 1 : v + 1);
        words.add(word);
    }
    
    public void add(PalindromeResult result) {
        for (String word : result.getWords()) {
            add(word);
        }
    }
    
    public int getCount() {
        return wordCount.size();
    }
    
    public ConcurrentHashMap<String, Integer> getWordCount() {
        return wordCount;
    }
    
    public ConcurrentLinkedDeque<String> getWords() {
        return words;
    }
}
