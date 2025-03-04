package EX2;

import java.util.List;

import usp.mac321.ep2.Lancamento;

 
public interface EscritaLancamentoDAO {
	public void criarArquivoLancamento(String nomeArquivoLancamento, List<Lancamento> lancamento);
	public void appendLancamento(String nomeArquivoLancamento, Lancamento Lancamento);
	public void appendLancamentos(String nomeArquivoLancamento, List<Lancamento> lancamentos);
    public void apagarLancamentoAtivo(String nomeArquivoLancamentos, List<Lancamento> lancamentos, String idLancamentoParaApagar);

}