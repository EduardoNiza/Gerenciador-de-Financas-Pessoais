package EX3;

import usp.mac321.ep2.Lancamento;

public class StatePlanejado implements State{
	@Override
	public void mostraState(Lancamento lancamento, String nomeArquivoLancamento, String nomeArquivoUsuarios) {
	int estado = lancamento.checaState(lancamento, nomeArquivoLancamento, nomeArquivoUsuarios);
	if(estado == 0)
		lancamento.setState(new StateExecutado());
	else if(estado == 2)
		lancamento.setState(new StateInvalido());
		
	}
}


