package EX1;

public class LancamentoDespesaInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private String idLancamento;
    private String categoriaInvalida;

    public LancamentoDespesaInvalidaException(String idLancamento, String categoriaInvalida) {
        this.idLancamento = idLancamento;
        this.categoriaInvalida = categoriaInvalida;
    }

    @Override
    public String toString() {
        return "LancamentoDespesaInvalidaException: Lançamento (id: " + idLancamento 
                + ") contém uma categoria de despesa inválida (" + categoriaInvalida + ") "
                + super.toString();
    }
}