Create a custom BusinessException class that can also return a meaningful error message to the user, depending on use case
 

Provide the user with a menu input and the following options:
 

Give strings as input and write them in a file in the resources folder (e.g. "userInput.csv")
Having the file above with data from the user, sort the strings alphabetically and write them in a separate file ("sortedInput.csv")
Having the "userInput.csv" file, provide the user with the option to remove duplicates and store the result in "uniqueInput.csv"
Using "uniqueInput.csv", the user can update a string, if it exists; provide exception handling: if the given string doesn't exist, throw a BusinessException with a meaningful message about what went wrong
e.g.: string to update given by user: "cat"

         updated string to be stored in file: "dog"

         if "cat" exists, replace its value, otherwise throw BusinessException

Add validation for the input strings given by user so that they only contain letters and numbers; exception handling as above
