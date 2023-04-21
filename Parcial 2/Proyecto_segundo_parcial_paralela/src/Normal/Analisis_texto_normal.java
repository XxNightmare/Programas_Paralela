package Normal;

import java.util.ArrayList;
import java.util.List;

public class Analisis_texto_normal {
    //es un método público que recibe como parámetro un texto y devuelve una lista de palabras
    public List<String> getWords(String text) {
        // se define un arreglo de Strings "wordsArray" que contiene todas las palabras que se han dividido en el parámetro "text" utilizando " " como delimitador.
        String[] wordsArray = text.split(" ");
        // se inicializa una lista vacía "wordsList" que almacenará las palabras después de ser separadas
        List<String> wordsList = new ArrayList<>();
        // se recorre cada palabra en el arreglo "wordsArray".
        for (String word : wordsArray) {
            // cada palabra del arreglo "wordsArray" se agrega a la lista "wordsList".
            wordsList.add(word);
        }
        // devuelve la lista de palabras "wordsList"
        return wordsList;
    }
}
