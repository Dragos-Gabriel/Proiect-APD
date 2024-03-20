package Paralel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainSequential {
    public static void main(String[] args) {
        String filename = "Text.txt";
        String[] wordsToCount = { "societate", "clasa", "profesorul", "tata" };

        // Crearea obiectului TextProcessor și inițializarea cu fișierul de procesat
        TextProcessor textProcessor = new TextProcessor(filename);

        long startTime = System.currentTimeMillis();

		// Apelarea metodei pentru numărarea cuvintelor secvențial
		Map<String, Integer> wordCounts = textProcessor.countWordsSequential(wordsToCount);

		// Oprește timpul de execuție
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println("Timp total de rulare este: " + elapsedTime + " ms");

		// Scriere în fișierul Excel
		String excelFilename = "WordCounts.csv";
		ExcelWriter.writeTimings(wordCounts, elapsedTime, excelFilename);
    }
}

