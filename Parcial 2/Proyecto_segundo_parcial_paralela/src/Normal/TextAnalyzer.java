package Normal;

import java.util.ArrayList;
import java.util.List;

class TextAnalyzer {

    public List<String> getWords(String text) {
        // Separa el texto en palabras y las almacena en una lista
        String[] wordsArray = text.split(" ");
        List<String> wordsList = new ArrayList<>();
        for (String word : wordsArray) {
            wordsList.add(word);
        }
        return wordsList;
    }
}
