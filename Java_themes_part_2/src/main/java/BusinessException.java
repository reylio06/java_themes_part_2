class BusinessException extends Exception {

    public BusinessException(String message, String operationName) {
        // Call the constructor of the superclass (Exception) with a concatenated message
        // The message includes both the original message and the name of the operation
        super(message + operationName);
    }
}