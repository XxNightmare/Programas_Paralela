package ForJoin;

public class PalindromeChecker {

    public static boolean isPalindrome(String word) {
        if (word.length() < 3) {
            return false;
        }
        word = word.toLowerCase(); // convertir a minúsculas
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
