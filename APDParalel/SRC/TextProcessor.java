package Paralel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class TextProcessor {
    private String text;

    public TextProcessor(String filename) {
        this.text = readTextFromFile(filename);
    }

    // Metoda pentru numărarea cuvintelor în mod paralel
    public Map<String, Integer> countWordsParallel(String[] wordsToCount) {
        Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();

        // Numărul total de fire de execuție
        int numThreads = Runtime.getRuntime().availableProcessors();
        
        // Contor pentru sincronizarea finalizării tuturor firelor de execuție
        CountDownLatch latch = new CountDownLatch(numThreads);

        // Dimensiunea fiecărui fragment de text
        int chunkSize = text.length() / numThreads;

        // Procesează fiecare fragment de text într-un fir de execuție separat
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * chunkSize;
            int endIndex = (i == numThreads - 1) ? text.length() : (i + 1) * chunkSize;
            String chunk = text.substring(startIndex, endIndex);

            // Creează și pornește un fir de execuție pentru fiecare fragment de text
            Thread thread = new Thread(new WordCounter(chunk, wordsToCount, wordCountMap, latch));
            thread.start();
        }

        try {
            // Așteaptă ca toate firele de execuție să-și finalizeze procesarea
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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

    // Clasa internă care numără cuvintele într-un fragment de text
    static class WordCounter implements Runnable {
        private String textChunk;
        private String[] wordsToCount;
        private Map<String, Integer> wordCountMap;
        private CountDownLatch latch;

        public WordCounter(String textChunk, String[] wordsToCount, Map<String, Integer> wordCountMap, CountDownLatch latch) {
            this.textChunk = textChunk;
            this.wordsToCount = wordsToCount;
            this.wordCountMap = wordCountMap;
            this.latch = latch;
        }

        @Override
        public void run() {
            for (String word : wordsToCount) {
                int count = 0;
                int lastIndex = 0;
                while ((lastIndex = textChunk.indexOf(word, lastIndex)) != -1) {
                    count++;
                    lastIndex += word.length();
                }
                wordCountMap.merge(word, count, Integer::sum);
            }
            latch.countDown(); // Marcajul finalizării procesării fragmentului de text
        }
    }
}
