package EX2;

import java.util.List;

import usp.mac321.ep2.Usuario;

public interface EscritaUsuariosDAO{
	public void criarArquivoUsuarios(String nomeArquivoUsuarios, List<Usuario> usuarios);
	public void appendUsuario(String nomeArquivoUsuarios, Usuario usuario);
	public void appendUsuarios(String nomeArquivoUsuarios, List<Usuario> usuarios);
}