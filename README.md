![image](https://github.com/reylio06/java_themes_part_2/assets/101171969/2045c2f3-b116-4553-afc4-05ad04c3cee0)Create a custom BusinessException class that can also return a meaningful error message to the user, depending on use case
 

Provide the user with a menu input and the following options:
 

Give strings as input and write them in a file in the resources folder (e.g. "userInput.csv")

Having the file above with data from the user, sort the strings alphabetically and write them in a separate file ("sortedInput.csv")

Having the "userInput.csv" file, provide the user with the option to remove duplicates and store the result in "uniqueInput.csv"

Using "uniqueInput.csv", the user can update a string, if it exists; provide exception handling: if the given string doesn't exist, throw a BusinessException with a meaningful message about what went wrong
e.g.: string to update given by user: "cat"

         updated string to be stored in file: "dog"

         if "cat" exists, replace its value, otherwise throw BusinessException

Add validation for the input strings given by user so that they only contain letters and numbers; exception handling as above

-------------------------------------------------------------------------

Update

-------------------------------------------------------------------------

BusinessException should have 2 attributes: message and operationName which you always fill in depending on the cause of the exception.

Catch BusinessException should be set to the main switch in main and the cause and message should be displayed to the user.

InputMismatchException must be mapped to a BusinessException and the cause of the exception must be taken from the original one.

All Catches in StringProcessor should be mapped to a BusinessException and the cause of the exception should be taken from the original one.

Reading and writing values from the console should be 100% done in the Main class. E.g. Reading from StringProcessor.getValidatedStrings should be moved to Main.

All methods in StringProcessor and File Processor should no longer be static, they should be accessed using objects.

All tries should have a catch and the exception should be passed on as a BusinessException.

FileProcessor should have only 2 methods : ReadFromFile(String filePath) and WriteToFile(String filePath, List<String> lines) which are general methods used everywhere in StringProcessor.
