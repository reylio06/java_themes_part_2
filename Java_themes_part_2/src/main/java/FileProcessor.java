import java.io.*;
import java.util.*;
import java.util.Set;

public class FileProcessor {
    public static List<String> readFile(String filePath) throws IOException {
        // Open a BufferedReader wrapped around a FileReader for the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return readLines(reader);
        }
    }

    public static Set<String> readUniqueFile(String filePath) throws IOException {
        // Open a BufferedReader wrapped around a FileReader for the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return readUniqueLines(reader);
        }
    }

    public static void writeToFile(String filePath, List<String> lines) throws IOException {
        // Open a PrintWriter wrapped around a FileWriter for the specified file path
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writeLines(writer, lines);
        }
    }

    public static void writeUniqueToFile(String filePath, Set<String> uniqueLines) throws IOException {
        // Open a PrintWriter wrapped around a FileWriter for the specified file path
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writeLines(writer, uniqueLines);
        }
    }

    private static List<String> readLines(BufferedReader reader) throws IOException {
        // Create list to store updated strings
        List<String> lines = new ArrayList<>();
        String line;
        // Read each line from the file
        while ((line = reader.readLine()) != null) {
            // Add original line
            lines.add(line);
        }
        return lines;
    }

    private static Set<String> readUniqueLines(BufferedReader reader) throws IOException {
        // Create set to store unique strings
        Set<String> uniqueLines = new HashSet<>();
        String line;

        // Read each line from the input file
        while ((line = reader.readLine()) != null) {
            // Add the line to the set of unique strings
            uniqueLines.add(line);
        }
        return uniqueLines;
    }

    private static void writeLines(PrintWriter writer, Iterable<String> lines) {
        // Iterate through strings list and write each one to file
        for (String line : lines) {
            writer.println(line);
        }
    }
}