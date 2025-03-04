package EX3;

import usp.mac321.ep2.Lancamento;

public class StateExecutado implements State{
	@Override
	public void mostraState(Lancamento lancamento, String nomeArquivoLancamento, String nomeArquivoUsuarios) {
	int estado = lancamento.checaState(lancamento, nomeArquivoLancamento, nomeArquivoUsuarios);
	if(estado == 1)
		lancamento.setState(new StatePlanejado());
	else if(estado == 2)
		lancamento.setState(new StateInvalido());
		
	}
}