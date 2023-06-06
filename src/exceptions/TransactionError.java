package exceptions;

public class TransactionError extends Exception {
    public TransactionError(String message) {
        super(message);
    }

}
