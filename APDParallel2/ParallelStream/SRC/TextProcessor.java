package Proiect;

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

    // Metoda pentru numărarea cuvintelor în mod paralel folosind parallelStream()
    public Map<String, Integer> countWordsParallel(String[] wordsToCount) {
        Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();

        // Imparte textul in fragmente de marime egala, iar apoi proceseaza fiecare fragment in mod paralel
        text.lines().parallel().forEach(chunk -> {
            for (String word : wordsToCount) {
                int count = 0;
                int lastIndex = 0;
                while ((lastIndex = chunk.indexOf(word, lastIndex)) != -1) {
                    count++;
                    lastIndex += word.length();
                }
                wordCountMap.merge(word, count, Integer::sum);
            }
        });

        return wordCountMap;
    }

    // Metoda pentru citirea textului din fișier
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

