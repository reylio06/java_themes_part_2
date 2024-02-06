import java.io.*;
import java.util.*;
import java.util.logging.Logger;


class StringProcessor {
    static final String INPUT_FILE = "src/main/resources/userInput.csv";
    static final String SORTED_FILE = "src/main/resources/sortedInput.csv";
    static final String UNIQUE_FILE = "src/main/resources/uniqueInput.csv";

    public StringProcessor(String inputFile, String sortedFile, String uniqueFile) {
    }

    public List<String> sortStrings(List<String> strings) {
        Collections.sort(strings);
        return strings;
    }

    public List<String> removeDuplicates(List<String> strings) {
        Set<String> uniqueStrings = new HashSet<>(strings);
        return new ArrayList<>(uniqueStrings);
    }

//    public List<String> updateString(List<String> lines) {
//        String searchString = lines.get(0);
//        String updatedString = lines.get(1);
//        List<String> updatedLines = new ArrayList<>();
//        try {
//            FileProcessor fileProcessor = new FileProcessor();
//            List<String> existingLines = fileProcessor.readFromFile(uniqueFile);
//            for (String line : existingLines) {
//                if (line.equals(searchString)) {
//                    updatedLines.add(updatedString);
//                } else {
//                    updatedLines.add(line);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return updatedLines;
//    }
//
//    public void updateStringInFile(List<String> lines) throws BusinessException {
//        try {
//            FileProcessor fileProcessor = new FileProcessor();
//            fileProcessor.writeToFile(uniqueFile, lines);
//            System.out.println("String updated in '" + uniqueFile + "'.");
//        } catch (IOException e) {
//            throw new BusinessException("Error updating string in file.", "updateStringInFile");
//        }
//    }
}