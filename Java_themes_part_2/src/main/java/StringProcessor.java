import java.io.*;
import java.util.*;

public class StringProcessor {

    // Provide path for input_file
    private static final String INPUT_FILE = "src/main/resources/userInput.csv";

    // Provide path for sorted_file
    private static final String SORTED_FILE = "src/main/resources/sortedInput.csv";

    // Provide path for unique_file
    private static final String UNIQUE_FILE = "src/main/resources/uniqueInput.csv";

    public static void main(String[] args) {
        try {
            // Read input from user
            Scanner scanner = new Scanner(System.in);

            // Display menu and handle user choices
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Write strings to 'userInput.csv'");
                System.out.println("2. Sort strings and write to 'sortedInput.csv'");
                System.out.println("3. Remove duplicates and write to 'uniqueInput.csv'");
                System.out.println("4. Update string in 'uniqueInput.csv'");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                // Read user choice
                int choice = scanner.nextInt();
                // Consume the newline
                scanner.nextLine();

                // Switch case to perform actions based on user choice
                switch (choice) {
                    case 1:
                        // Call writeToFile method to write strings to userInput.csv
                        writeToFile(INPUT_FILE, getValidatedStrings(scanner));
                        break;
                    case 2:
                        // Call sortAndWriteToFile to sort strings and write them to sortedInput.csv
                        sortAndWriteToFile(INPUT_FILE, SORTED_FILE);
                        break;
                    case 3:
                        // Call removeDuplicatesAndWriteToFile to remove duplicates and write them to uniqueInput.csv
                        removeDuplicatesAndWriteToFile(INPUT_FILE, UNIQUE_FILE);
                        break;
                    case 4:
                        // Call updateStringInFile to update a string in uniqueInput.csv
                        updateStringInFile(UNIQUE_FILE, scanner);
                        break;
                    case 5:
                        // Exit program
                        System.out.println("Exiting program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InputMismatchException e) {
            // Handle cases where user enters invalid input
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private static List<String> getValidatedStrings(Scanner scanner) {
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
                // If string is invalid, display error message
                System.out.println("Error: " + e.getMessage());
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

    private static void writeToFile(String filePath, List<String> strings) {

        // Open a PrintWriter wrapped around a FileWriter for the specified file path
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            // Iterate through strings list and write each one to file
            for (String s : strings) {
                writer.println(s);
            }
            System.out.println("Strings written to '" + filePath + "'.");

        } catch (IOException e) {
            // If string is invalid, display error message
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void sortAndWriteToFile(String inputFilePath, String outputFilePath) {
        // Open a BufferedReader wrapped around a FileReader for the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));

             // Open a PrintWriter wrapped around a FileWriter for the output file
             PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {

            // Create a list to store strings from the input file
            List<String> strings = new ArrayList<>();
            String line;

            // Read strings from input file
            while ((line = reader.readLine()) != null) {
                // Add the line to the list of strings
                strings.add(line);
            }

            // Sort strings alphabetically
            Collections.sort(strings);

            // Write sorted strings to output file
            for (String s : strings) {
                writer.println(s);
            }

            System.out.println("Strings sorted and written to '" + outputFilePath + "'.");
        } catch (IOException e) {
            // I/O error handling
            System.out.println("Error sorting and writing to file: " + e.getMessage());
        }
    }

    private static void removeDuplicatesAndWriteToFile(String inputFilePath, String outputFilePath) {

        // Open a BufferedReader wrapped around a FileReader for the input file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             // Open a PrintWriter wrapped around a FileWriter for the output file
             PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {

            // Create set to store unique strings
            Set<String> uniqueStrings = new HashSet<>();
            String line;
            // Read each line from the input file
            while ((line = reader.readLine()) != null) {
                // Add the line to the set of unique strings
                uniqueStrings.add(line);
            }

            // Iterate through the set of unique strings and write each one to file
            for (String s : uniqueStrings) {
                writer.println(s);
            }

            System.out.println("Duplicates removed and unique strings written to '" + outputFilePath + "'.");
        } catch (IOException e) {
            // I/O error handling
            System.out.println("Error removing duplicates and writing to file: " + e.getMessage());
        }
    }

    private static void updateStringInFile(String filePath, Scanner scanner) {
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
                        // Debugging statement
                        System.out.println("Current line: " + line);
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
            if (!lines.contains(updatedString)) {
                throw new BusinessException("String to update not found in the file.");
            }

            // Write updated strings back to the file
            writeToFile(filePath, lines);
            System.out.println("String updated in '" + filePath + "'.");
        } catch (IOException | BusinessException e) {
            // I/O error handling
            System.out.println("Error updating string: " + e.getMessage());
        }
    }
    }