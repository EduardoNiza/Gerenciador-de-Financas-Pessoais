package EX1;

public class LancamentoUsuarioInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String idLancamento;
    private String usuarioInvalido;


    public LancamentoUsuarioInvalidoException(String idLancamento, String usuarioInvalido) {
        this.idLancamento = idLancamento;
        this.usuarioInvalido = usuarioInvalido;
    }

    @Override
    public String toString() {
        return "LancamentoUsuarioInvalidoException: Lançamento (id: " + idLancamento 
                + ") está associado a um usuário inexistente (" + usuarioInvalido + ") "
                + super.toString();
    }
}