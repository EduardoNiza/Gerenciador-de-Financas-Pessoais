package EX3;

public class FormatoDataInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String aux1;

    public FormatoDataInvalidoException(String data) {
        aux1 = data;
    }

    @Override
    public String toString() {
        return "FormatoDataInvalidoException: Data inválida (" + aux1 + ") " + super.toString();
    }
}