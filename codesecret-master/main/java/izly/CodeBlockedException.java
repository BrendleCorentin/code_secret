package izly;

public class CodeBlockedException extends TransactionBloqueException {
    // Constructeur par défaut
    public CodeBlockedException() {
        super("Le code secret est bloqué après plusieurs tentatives infructueuses.");
    }

    public CodeBlockedException(String message) {
        super(message);
    }
}
