import java.util.*;

class StringProcessor {

    // Method to sort a list of strings in lexicographic order
    public List<String> sortStrings(List<String> strings) {

        // Sort the list of strings using Collections.sort() method
        Collections.sort(strings);

        // Return the sorted list of strings
        return strings;
    }

    public List<String> removeDuplicates(List<String> strings) {

        // Create a HashSet to store unique strings, which automatically removes duplicates
        Set<String> uniqueStrings = new HashSet<>(strings);

        // Convert the HashSet back to an ArrayList to preserve the order of elements
        return new ArrayList<>(uniqueStrings);
    }
}