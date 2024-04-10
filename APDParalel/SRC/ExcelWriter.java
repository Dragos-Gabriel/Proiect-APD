package Paralel;



import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ExcelWriter {

    // Metoda pentru scrierea rezultatelor în fișierul CSV
    public static synchronized void writeTimings(Map<String, Integer> wordCounts, long elapsedTime, String csvFilename) {
        try (FileWriter writer = new FileWriter(csvFilename)) {
            // Scrie antetul în fișierul CSV
            writer.write("Cuvant, Numar Aparitii\n");

            // Scrie fiecare pereche (cuvânt, număr de apariții) în fișierul CSV
            for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
                String word = entry.getKey(); // Obține cheia (cuvântul)
                int count = entry.getValue(); // Obține valoarea (numărul de apariții)
                writer.write(word + ", " + count + "\n"); // Scrie în fișierul CSV
            }

            System.out.println("Fisierul Excel a fost creat cu succes.");
        } catch (IOException e) {
            // Afișează orice excepție apărută în timpul scrierii
            e.printStackTrace();
        }
    }
}
