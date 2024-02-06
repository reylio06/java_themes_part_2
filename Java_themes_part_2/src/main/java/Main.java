import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

public class Main {
    static final String INPUT_FILE = "src/main/resources/userInput.csv";
    static final String SORTED_FILE = "src/main/resources/sortedInput.csv";
    static final String UNIQUE_FILE = "src/main/resources/uniqueInput.csv";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringProcessor stringProcessor = new StringProcessor(INPUT_FILE, SORTED_FILE, UNIQUE_FILE);
        FileProcessor fileProcessor = new FileProcessor();

        try {
            while (true) {
                System.out.println("\nMenu:");
                System.out.println("1. Write strings to 'userInput.csv'");
                System.out.println("2. Sort strings and write to 'sortedInput.csv'");
                System.out.println("3. Remove duplicates and write to 'uniqueInput.csv'");
                System.out.println("4. Update string in 'uniqueInput.csv'");
                System.out.println("5. Exit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        List<String> inputStrings = getValidatedStrings(scanner);
                        fileProcessor.writeToFile(StringProcessor.INPUT_FILE, inputStrings);
                        break;
                    case 2:
                        List<String> sortedStrings = stringProcessor.sortStrings(fileProcessor.readFromFile(StringProcessor.INPUT_FILE));
                        fileProcessor.writeToFile(StringProcessor.SORTED_FILE, sortedStrings);
                        System.out.println("Strings sorted and written to '" + StringProcessor.SORTED_FILE + "'.");
                        break;
                    case 3:
                        List<String> inputStringsForDupRemoval = fileProcessor.readFromFile(StringProcessor.INPUT_FILE);
                        List<String> uniqueStrings = stringProcessor.removeDuplicates(inputStringsForDupRemoval);
                        fileProcessor.writeToFile(StringProcessor.UNIQUE_FILE, uniqueStrings);
                        System.out.println("Duplicates removed and unique strings written to '" + StringProcessor.UNIQUE_FILE + "'.");
                        break;
                    case 4:
                        List<String> updatedStrings = getUpdatedStrings(scanner, fileProcessor);
                        fileProcessor.writeToFile(UNIQUE_FILE, updatedStrings);
                        System.out.println("String updated in '" + UNIQUE_FILE + "'.");
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (BusinessException e) {
            e.getMessage();
        } finally {
            scanner.close();
        }
    }

    public static List<String> getValidatedStrings(Scanner scanner) throws BusinessException {
        // Move the logic of getValidatedStrings from StringProcessor to Main
        List<String> strings = new ArrayList<>();
        while (true) {
            System.out.print("Enter a string (press Enter to finish): ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            try {
                validateString(input);
                strings.add(input);
            } catch (BusinessException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return strings;
    }

    public static void validateString(String input) throws BusinessException {
        if (!input.matches("[a-zA-Z0-9]+")) {
            throw new BusinessException("Invalid characters in string. Only letters and numbers are allowed.", "validateString");
        }
    }

    public static List<String> getUpdatedStrings(Scanner scanner, FileProcessor fileProcessor) throws BusinessException {
        List<String> updatedStrings = new ArrayList<>();
        try {
            System.out.print("Enter string to update: ");
            String searchString = scanner.nextLine();
            System.out.print("Enter updated string: ");
            String updatedString = scanner.nextLine();
            List<String> lines = fileProcessor.readFromFile(UNIQUE_FILE);
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(searchString)) {
                    lines.set(i, updatedString);
                }
            }
            updatedStrings.addAll(lines);
        } catch (BusinessException e) {
            throw new BusinessException(e.getMessage(), "getUpdatedStrings");
        }
        return updatedStrings;
    }
}