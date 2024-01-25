import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            StringProcessor stringProcessor = new StringProcessor();

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
                        stringProcessor.writeStringsToFile(StringProcessor.INPUT_FILE, stringProcessor.getValidatedStrings(scanner));
                        break;
                    case 2:
                        stringProcessor.sortStringsToFile(StringProcessor.INPUT_FILE, StringProcessor.SORTED_FILE);
                        break;
                    case 3:
                        stringProcessor.removeDuplicates(StringProcessor.INPUT_FILE, StringProcessor.UNIQUE_FILE);
                        break;
                    case 4:
                        stringProcessor.updateStringInFile(StringProcessor.UNIQUE_FILE, scanner);
                        break;
                    case 5:
                        System.out.println("Exiting program.");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }
}