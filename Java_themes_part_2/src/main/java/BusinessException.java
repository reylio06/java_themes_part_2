public class BusinessException extends Exception {
    private String operationName;

    public BusinessException(String message, String operationName) {

        // Call the constructor of the superclass (Exception) with a concatenated message
        // The message includes both the original message and the name of the operation
        super(message);
        this.operationName = operationName;

    }

    public String getOperationName() {

        return operationName;
    }

    public void setOperationName(String operationName) {

        this.operationName = operationName;
    }
}