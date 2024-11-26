import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        File selectedFile = null;

        // Check for command-line arguments
        if (args.length > 0) {
            // Use the first argument as the file path
            selectedFile = new File(args[0]);
            if (!selectedFile.exists() || !selectedFile.isFile()) {
                System.out.println("The file specified does not exist or is not a valid file. Exiting program.");
                return;
            }
        } else {
            // Launch JFileChooser for user to pick a file interactively
            JFileChooser fileChooser = new JFileChooser(new File("src"));
            int result = fileChooser.showOpenDialog(null);

            if (result != JFileChooser.APPROVE_OPTION) {
                System.out.println("No file selected. Exiting program.");
                return;
            }
            selectedFile = fileChooser.getSelectedFile();
        }

        // Initialize counters
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        // Read and process the file
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(selectedFile.getAbsolutePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                lineCount++;
                String[] words = line.split("\\s+");
                wordCount += words.length;
                charCount += line.length();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Display summary report
        System.out.println("\nSummary Report:");
        System.out.println("File name: " + selectedFile.getName());
        System.out.println("Number of lines: " + lineCount);
        System.out.println("Number of words: " + wordCount);
        System.out.println("Number of characters: " + charCount);
    }
}
