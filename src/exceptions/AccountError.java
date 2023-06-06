package exceptions;

public class AccountError extends TransactionError {
    public AccountError(String message) {
        super(message);
    }

}
