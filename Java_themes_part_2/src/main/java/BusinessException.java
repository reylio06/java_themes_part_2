class BusinessException extends Exception {

    public BusinessException(String message, String operationName) {
        super(message);
    }
}