package EX2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;
import EX1.*;

import usp.mac321.ep2.Lancamento;

public class EscritaLancamentoDAOImple implements EscritaLancamentoDAO {
	private static final String Lancamentos_PATH = "csv/Lancamentos2.csv";

	private CSVWriter AbridorDeArquivos(String nomeDoArquivo, boolean appendOuNao) {
		try {
			FileWriter filewriter = new FileWriter(nomeDoArquivo, appendOuNao);
			CSVWriter writer = new CSVWriter(filewriter, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			return writer;

		} catch (Exception e) {
			System.out.println("Erro ao escrever no arquivo.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void appendLancamento(String nomeArquivoLancamentos, Lancamento Lancamento) {
		CSVWriter writer = AbridorDeArquivos(nomeArquivoLancamentos, true);
		writer.writeNext(Lancamento.getAll());
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o arquivo.");
			e.printStackTrace();
		}
	}

	@Override
	public void appendLancamentos(String nomeArquivoLancamentos, List<Lancamento> Lancamentos) {
		CSVWriter writer = AbridorDeArquivos(nomeArquivoLancamentos, true);
		for (int i = 0; i < Lancamentos.size(); i++) {
			writer.writeNext(Lancamentos.get(i).getAll());
		}
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o arquivo.");
			e.printStackTrace();
		}
	}

	@Override
	public void criarArquivoLancamento(String nomeArquivoLancamento, List<Lancamento> Lancamento) {
		CSVWriter writer = AbridorDeArquivos(nomeArquivoLancamento, false);
		String[] firstRow = "ID,Data,ResponsÃ¡vel,Despesa?,SubCategoria,Valor,DescriÃ§Ã£o".split(",");
		writer.writeNext(firstRow);
		for (int i = 0; i < Lancamento.size(); i++) {
			writer.writeNext(Lancamento.get(i).getAll());
		}
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o arquivo.");
			e.printStackTrace();
		}

	}


    public void apagarLancamentoAtivo(String nomeArquivoLancamentos, List<Lancamento> lancamentos, String idLancamentoParaApagar) {
        CSVWriter writer = AbridorDeArquivos(nomeArquivoLancamentos, false);
        String[] firstRow = "ID,Data,Responsável,Despesa?,SubCategoria,Valor,Descrição".split(",");
        writer.writeNext(firstRow);
        for (Lancamento lancamento : lancamentos) {
            if (!lancamento.getIdLancamento().equals(idLancamentoParaApagar)) {
                writer.writeNext(lancamento.getAll());
            }
        }
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao fechar o arquivo.");
            e.printStackTrace();
        }
    }
}




