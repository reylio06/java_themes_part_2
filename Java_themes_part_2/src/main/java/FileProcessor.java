import java.io.*;
import java.util.*;
import java.util.Set;

public class FileProcessor {
    public List<String> readFromFile(String filePath) throws BusinessException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new BusinessException(e.getMessage(), "readFromFile");
        }
    }

    public void writeToFile(String filePath, List<String> lines) throws BusinessException {
        // Debug statement
        System.out.println("Writing to file: " + filePath);
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();

        if (!parentDirectory.exists()) {
            // Create parent directories if they don't exist
            parentDirectory.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (IOException e) {
            throw new BusinessException(e.getMessage(), "writeToFile");
        }
        // Debug statement
        System.out.println("Write operation completed.");
    }
}