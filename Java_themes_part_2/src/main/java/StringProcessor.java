import java.io.*;
import java.util.*;
import java.util.logging.Logger;


public class StringProcessor {

    // Provide path for input_file
    static final String INPUT_FILE = "src/main/resources/userInput.csv";

    // Provide path for sorted_file
    static final String SORTED_FILE = "src/main/resources/sortedInput.csv";

    // Provide path for unique_file
    static final String UNIQUE_FILE = "src/main/resources/uniqueInput.csv";

    // Create logger to replace System.out.println statements
    static final Logger logger = Logger.getLogger(StringProcessor.class.getName());

     public List<String> getValidatedStrings(Scanner scanner) {
        // Create new list to store the strings that the user inputs
        List<String> strings = new ArrayList<>();

        while (true) {
            System.out.print("Enter a string (press Enter to finish): ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                break;
            }

            try {
                // Validate each string with validateString method
                validateString(input);
                // Add string to list
                strings.add(input);

            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }

        // Return string list
        return strings;
    }


    private static void validateString(String input) throws BusinessException {
        // Check to see if input string only contains letters and numbers
        // a-z matches a single character in the range between a and z
        // A-Z matches a single character in the range between A and Z
        // 0-9 matches a single character in the range between 0 and 9
        // + matches the previous token between one and unlimited times, as many times as possible, giving back as needed
        if (!input.matches("[a-zA-Z0-9]+")) {
            throw new BusinessException("Invalid characters in string. Only letters and numbers are allowed.");
        }
    }

    public void writeStringsToFile(String filePath, List<String> strings) {

        try {
            // Call writeStringsToFile method to write strings to file
            FileProcessor.writeToFile(filePath, strings);
            logger.info("Strings written to '" + filePath + "'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortStringsToFile(String inputFilePath, String outputFilePath) {
        // Open a BufferedReader wrapped around a FileReader for the input file
        try {
            List<String> strings = FileProcessor.readFile(inputFilePath);

            // Sort strings alphabetically
            Collections.sort(strings);

            // Write sorted strings to output file
            FileProcessor.writeToFile(outputFilePath, strings);

            logger.info("Strings sorted and written to '" + outputFilePath + "'.");


        } catch (IOException e) {
            // I/O error handling
            e.printStackTrace();
        }
    }

    public void removeDuplicates(String inputFilePath, String outputFilePath){

        try {

            // Create set to store unique strings
            Set<String> uniqueStrings = FileProcessor.readUniqueFile(inputFilePath);

            //Call writeUniqueToFile to write unique strings to output file
            FileProcessor.writeUniqueToFile(outputFilePath, uniqueStrings);

            logger.info("Duplicates removed and unique strings written to '" + outputFilePath + "'.");

        } catch (IOException e) {
            // I/O error handling
            e.printStackTrace();

        }
    }

    public void updateStringInFile(String filePath, Scanner scanner){
        try {
            // Prompt user for the string to update and the updated string
            System.out.print("Enter string to update: ");
            String searchString = scanner.nextLine();
            validateString(searchString);

            System.out.print("Enter updated string: ");
            String updatedString = scanner.nextLine();
            validateString(updatedString);

            // Create list to store updated strings
            List<String> lines = new ArrayList<>();

            // Check if the file exists
            File file = new File(filePath);
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    // Read each line from the file
                    while ((line = reader.readLine()) != null) {
                        // If the line matches the string to update, add the updated string
                        if (line.equals(searchString)) {
                            lines.add(updatedString);
                        } else {
                            // Add original line
                            lines.add(line);
                        }
                    }
                }
            } else {
                // Print error regarding filePath not found
                throw new BusinessException("File '" + filePath + "' does not exist.");
            }

            // Check if the updated string exists in the file
                // Write updated strings back to the file
                FileProcessor.writeToFile(filePath, lines);
                removeDuplicates(filePath, filePath);

        } catch (IOException | BusinessException e) {
            // I/O error handling
            e.printStackTrace();
        }
    }
}