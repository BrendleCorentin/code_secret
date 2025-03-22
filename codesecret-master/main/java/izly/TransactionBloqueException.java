package izly;

public class TransactionBloqueException extends Exception {
    public TransactionBloqueException() {
        super();
    }

    public TransactionBloqueException(String message) {
        super(message);
    }
}
