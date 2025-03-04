package EX3;


public class LancamentoInexistenteException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String aux1;
	public LancamentoInexistenteException(String lancamento){  
		aux1 = lancamento;
 	}
	public String toString(){ 
		return("LancamentoReferencialInexistenteException: Lancamento(id:" + aux1 + ") "
				+ super.toString());
	}
}
