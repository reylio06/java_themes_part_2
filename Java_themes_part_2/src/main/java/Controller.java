import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Controller {

    static final String INPUT_FILE = "src/main/resources/userInput.csv";
    static final String SORTED_FILE = "src/main/resources/sortedInput.csv";
    static final String UNIQUE_FILE = "src/main/resources/uniqueInput.csv";
    private final StringProcessor stringProcessor;
    private final FileProcessor fileProcessor;
    private final Scanner scanner;
    static final Logger logger = Logger.getLogger(StringProcessor.class.getName());

    public Controller() {
        stringProcessor = new StringProcessor();
        fileProcessor = new FileProcessor();
        scanner = new Scanner(System.in);
    }

    public void runApplication() {
        try {
            while (true) {
                // Display menu options
                System.out.println("\nMenu:");
                System.out.println("1. Write strings to 'userInput.csv'");
                System.out.println("2. Sort strings and write to 'sortedInput.csv'");
                System.out.println("3. Remove duplicates and write to 'uniqueInput.csv'");
                System.out.println("4. Update string in 'uniqueInput.csv'");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Get validated strings from user and write to input file
                        List<String> inputStrings = getValidatedStrings(scanner);
                        fileProcessor.writeToFile(INPUT_FILE, inputStrings);
                        break;
                    case 2:
                        // Read strings from input file, sort them, and write to sorted file
                        List<String> sortedStrings = stringProcessor.sortStrings(fileProcessor.readFromFile(INPUT_FILE));
                        fileProcessor.writeToFile(SORTED_FILE, sortedStrings);
                        logger.info("Strings sorted and written to '" + SORTED_FILE + "'.");
                        break;
                    case 3:
                        // Read strings from input file, remove duplicates, and write to unique file
                        List<String> inputStringsForDupRemoval = fileProcessor.readFromFile(INPUT_FILE);
                        List<String> uniqueStrings = stringProcessor.removeDuplicates(inputStringsForDupRemoval);
                        fileProcessor.writeToFile(UNIQUE_FILE, uniqueStrings);
                        logger.info("Duplicates removed and unique strings written to '" + UNIQUE_FILE + "'.");
                        break;
                    case 4:
                        // Get strings to update from user, update the file, and log the operation
                        List<String> updatedStrings = updateStrings(scanner, fileProcessor);
                        fileProcessor.writeToFile(UNIQUE_FILE, updatedStrings);
                        break;
                    case 5:
                        // Exit the program
                        System.out.println("Exiting program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (BusinessException e) {
            System.out.println(e.getMessage() + " " + e.getOperationName());
        } finally {
            scanner.close();
        }
    }
    public static List<String> getValidatedStrings(Scanner scanner) throws BusinessException {

        // Move the logic of getValidatedStrings from StringProcessor to Main
        List<String> strings = new ArrayList<>();

        // Keep asking for strings until an empty string is entered
        while (true) {
            System.out.print("Enter a string (press Enter to finish): ");
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                break;
            }

            try {
                // Validate the input string
                validateString(input);
                strings.add(input);
            } catch (BusinessException e) {
                // If validation fails, throw BusinessException
                System.out.println(e.getMessage() + " " + e.getOperationName());
            }
        }
        return strings;
    }

    public static void validateString(String input) throws BusinessException {

        // Check if the string contains only letters and numbers.
        if (!input.matches("[a-zA-Z0-9]+")) {
            throw new BusinessException("Invalid characters in string. Only letters and numbers are allowed.", "validateString");
        }
    }

    public static List<String> updateStrings(Scanner scanner, FileProcessor fileProcessor) throws BusinessException {

        List<String> updatedStrings = new ArrayList<>();

        try {

            // Get string to update and the updated string from user input
            System.out.print("Enter string to update: ");
            String searchString = scanner.nextLine();

            System.out.print("Enter updated string: ");
            String updatedString = scanner.nextLine();

            // Read strings from unique file
            List<String> lines = fileProcessor.readFromFile(UNIQUE_FILE);

            if (lines.contains(searchString)) {
                // Update the string in the list
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).equals(searchString)) {
                        lines.set(i, updatedString);
                    }
                }
                updatedStrings.addAll(lines);

                logger.info("String updated in '" + UNIQUE_FILE + "'.");

            } else {
                // If the string to update does not exist, print a message and return original list
                logger.info("The string '" + searchString + "' does not exist in the file.");
                return lines;
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), "getUpdatedStrings");
        }
        return updatedStrings;
    }
}