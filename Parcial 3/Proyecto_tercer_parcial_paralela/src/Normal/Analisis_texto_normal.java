package Normal;

import java.util.ArrayList;
import java.util.List;

public class Analisis_texto_normal {
    public List<String> getWords(String text) {
        // se define un arreglo de Strings "wordsArray" que contiene todas las palabras que se han dividido 
        // en el parámetro "text" utilizando " " como delimitador.
        String[] wordsArray = text.split(" ");
        // se inicializa una lista vacía "wordsList" que almacenará las palabras después de ser separadas
        List<String> wordsList = new ArrayList<>();
        // se recorre cada palabra en el arreglo "wordsArray".
        for (String word : wordsArray) {
            // cada palabra del arreglo "wordsArray" se agrega a la lista "wordsList".
            System.out.println("");
            wordsList.add(word);
        }
        // devuelve la lista de palabras
        return wordsList;
    }
}
