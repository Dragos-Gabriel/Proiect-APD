package Paralel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TextProcessor {
    private String text;

    public TextProcessor(String filename) {
        this.text = readTextFromFile(filename);
    }

    // Metodă pentru numărarea cuvintelor secvențial
    public Map<String, Integer> countWordsSequential(String[] wordsToCount) {
        Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();

        // Numărarea cuvintelor în textul complet
        for (String word : wordsToCount) {
            int count = 0;
            int lastIndex = 0;

            while ((lastIndex = text.indexOf(word, lastIndex)) != -1) {
                count++;
                lastIndex += word.length(); // Actualizare poziție de start pentru următoarea apariție a cuvântului
            }

            wordCountMap.put(word, count);
        }

        return wordCountMap;
    }

    private String readTextFromFile(String filename) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
}

