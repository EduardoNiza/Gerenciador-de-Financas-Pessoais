package EX2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

import usp.mac321.ep2.Usuario;
import EX1.LeitorFinancasPessoaisDAOImpl;

public class EscritaUsuariosDAOImple implements EscritaUsuariosDAO{
	private static final String USUARIOS_PATH = "csv/usuarios2.csv";

	private CSVWriter AbridorDeArquivos(String nomeDoArquivo, boolean appendOuNao) {
		try {
			FileWriter filewriter = new FileWriter(nomeDoArquivo, appendOuNao);
			CSVWriter writer = new CSVWriter(filewriter,
					CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
			return writer;


		} catch (Exception e) {
			System.out.println("Erro ao escrever no arquivo.");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void appendUsuario(String nomeArquivoaUsuarios, Usuario usuario) {
		CSVWriter writer = AbridorDeArquivos(nomeArquivoaUsuarios, true);
		writer.writeNext(usuario.getAll());
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o arquivo.");
 			e.printStackTrace();
		}	
	}

	@Override
	public void appendUsuarios(String nomeArquivoUsuarios, List<Usuario> usuarios) {
		CSVWriter writer = AbridorDeArquivos(nomeArquivoUsuarios, true);
		for(int i = 0; i < usuarios.size(); i++) {
			writer.writeNext(usuarios.get(i).getAll());
		}
 		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o arquivo.");
 			e.printStackTrace();
		}	
	}
	
	@Override
	public void criarArquivoUsuarios(String nomeArquivoUsuarios, List<Usuario> usuarios) {
		CSVWriter writer = AbridorDeArquivos(nomeArquivoUsuarios, false);
		String[] firstRow = "Apelido,Nome".split(",");
		writer.writeNext(firstRow);
		for(int i = 0; i < usuarios.size(); i++) {
			writer.writeNext(usuarios.get(i).getAll());
		}
 		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Erro ao fechar o arquivo.");
 			e.printStackTrace();
		}	
		
	}

	public static void main(String[] args) {
		EscritaUsuariosDAOImple escritor = new EscritaUsuariosDAOImple();
		LeitorFinancasPessoaisDAOImpl leitor = new LeitorFinancasPessoaisDAOImpl();
		List<Usuario> usuarios= leitor.leUsuarios("csv/usuarios.csv");

		escritor.criarArquivoUsuarios(USUARIOS_PATH, usuarios);
 	}
}