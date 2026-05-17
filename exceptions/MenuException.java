package exceptions;

/**
 * Exceção utilizada exclusivamente para erros na exibição do menu do sistema
 */
public class MenuException extends RuntimeException {
    public MenuException(String message) {
        super(message);
    }
}
