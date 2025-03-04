package EX1;

public class LancamentoReceitaInvalidaException extends RuntimeException {
	private static final long serialVersionUID = 1L;
    private String idLancamento;
    private String categoriaInvalida;

    public LancamentoReceitaInvalidaException(String idLancamento, String categoriaInvalida) {
        this.idLancamento = idLancamento;
        this.categoriaInvalida = categoriaInvalida;
    }

    @Override
    public String toString() {
        return "LancamentoReceitaInvalidaException: Lançamento (id: " + idLancamento 
                + ") contém uma categoria de receita inválida (" + categoriaInvalida + ") "
                + super.toString();
    }
}