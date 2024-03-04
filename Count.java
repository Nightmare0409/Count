package Count;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Count{

    public static void main(String[] args) {
        try {
            // Prompting the user to enter the name of input file.
            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            System.out.print("Enter the name of the input file: ");
            String inputFileName = reader.readLine();
            reader.close();

            // Counting words from input file.
            Map<String, Integer> wordCountMap = countWords(inputFileName);

            // Writing the results to an output file.
            writeWordCountToFile(wordCountMap, "output.txt");

            System.out.println("Word count saved to output.txt");

        } catch (IOException e) {
            System.out.println("Error reading or writing file: " + e.getMessage());
        }
    }

    // Method counting words in the input file.
    private static Map<String, Integer> countWords(String inputFileName) throws IOException {
        Map<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.toLowerCase().split("\\s+"); // Split by whitespace.
                for (String word : words) {
                    word = word.replaceAll("[^a-zA-Z]", ""); // Removing non-alphabetic characters.
                    if (!word.isEmpty()) {
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }

        return wordCountMap;
    }

    // Method writing word count to output file.
    private static void writeWordCountToFile(Map<String, Integer> wordCountMap, String outputFileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        }
    }
}
