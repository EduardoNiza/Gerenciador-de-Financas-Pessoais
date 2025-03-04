package EX2;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.Usuario;
import EX1.LeitorFinancasPessoaisDAOImpl;


class TestaEscritaPlanilhas {
    private static final String LANCAMENTOS_PATH = "csv/csvEscrita/lancamentos.csv";
    private static final String LANCAMENTOS_ESCRITA_PATH = "csv/csvEscrita/lancamentosEscrita.csv";
    
    private static final String USUARIOS_PATH = "csv/csvEscrita/usuarios.csv";
    private static final String USUARIOS_ESCRITA_PATH = "csv/csvEscrita/usuariosEscrita.csv";

    List<Usuario> usuarios;
    
    List<Lancamento> lancamentos;
    static LeitorFinancasPessoaisDAOImpl leitor;
    EscritaLancamentoDAO escritorLancamentos;
    EscritaUsuariosDAO escritorUsuarios;


    @BeforeAll
    public static void setUp() {
        leitor = new LeitorFinancasPessoaisDAOImpl();
    }

    @Test
    public void testLancamentos1() {
        lancamentos = leitor.leLancamentos(LANCAMENTOS_PATH);
        assertEquals(8, lancamentos.size());
    }

    // cria um arquivo com header a partir de uma lista
    @Test
    public void testLancamentos2() {
        lancamentos = leitor.leLancamentos(LANCAMENTOS_PATH);
        escritorLancamentos = new EscritaLancamentoDAOImple();
        escritorLancamentos.criarArquivoLancamento(LANCAMENTOS_ESCRITA_PATH, lancamentos);
        lancamentos = leitor.leLancamentos(LANCAMENTOS_ESCRITA_PATH);
        assertEquals(8, lancamentos.size());
    }

    // adiciona um lançamento no csv
    @Test
    public void testLancamentos3() {
        lancamentos = leitor.leLancamentos(LANCAMENTOS_PATH);
        escritorLancamentos = new EscritaLancamentoDAOImple();
        escritorLancamentos.criarArquivoLancamento(LANCAMENTOS_ESCRITA_PATH, lancamentos);
        Lancamento lancamento = new Lancamento("9", "05/05/24", "Pai", "FALSE", "Principal", "5111", "Bônus");
        escritorLancamentos.appendLancamento(LANCAMENTOS_ESCRITA_PATH, lancamento);
        lancamentos = leitor.leLancamentos(LANCAMENTOS_ESCRITA_PATH);
        assertEquals(9, lancamentos.size());
    }

    // adiciona uma lista de dois lançamentos no csv
    @Test
    public void testLancamentos4() {
        lancamentos = leitor.leLancamentos(LANCAMENTOS_PATH);
        escritorLancamentos = new EscritaLancamentoDAOImple();
        escritorLancamentos.criarArquivoLancamento(LANCAMENTOS_ESCRITA_PATH, lancamentos);
        List<Lancamento> novosLancamentos = new ArrayList<Lancamento>();
        novosLancamentos.add(new Lancamento("10", "06/05/24", "Pai", "TRUE", "Cinema", "60010", "Filme"));
        novosLancamentos.add(new Lancamento("11", "07/05/24", "Pai", "TRUE", "Streaming", "10011", "Netflix"));
        escritorLancamentos.appendLancamentos(LANCAMENTOS_ESCRITA_PATH, novosLancamentos);
        lancamentos = leitor.leLancamentos(LANCAMENTOS_ESCRITA_PATH);
        assertEquals(10, lancamentos.size());
    }

    // remove um lançamento específico do csv
    @Test
    public void testLancamentos5() {
        lancamentos = leitor.leLancamentos(LANCAMENTOS_PATH);
        escritorLancamentos = new EscritaLancamentoDAOImple();
        escritorLancamentos.criarArquivoLancamento(LANCAMENTOS_ESCRITA_PATH, lancamentos);
        // usuario escolhe o id que ele ter deletar no terceiro parametro
        escritorLancamentos.apagarLancamentoAtivo(LANCAMENTOS_ESCRITA_PATH, lancamentos, "3");
        lancamentos = leitor.leLancamentos(LANCAMENTOS_ESCRITA_PATH);
        assertEquals(7, lancamentos.size());
     }
    
    @Test
    public void testUsuarios1() {
        usuarios = leitor.leUsuarios(USUARIOS_PATH);
        assertEquals(2, usuarios.size());
    }

    // cria um arquivo com header a partir de uma lista
    @Test
    public void testUsuarios2() {
        usuarios = leitor.leUsuarios(USUARIOS_PATH);
        escritorUsuarios = new EscritaUsuariosDAOImple();
        escritorUsuarios.criarArquivoUsuarios(USUARIOS_ESCRITA_PATH, usuarios);
        usuarios = leitor.leUsuarios(USUARIOS_ESCRITA_PATH);
        assertEquals(2, usuarios.size());
    }

    // adiciona um usuário no csv
    @Test
    public void testUsuarios3() {
        usuarios = leitor.leUsuarios(USUARIOS_PATH);
        escritorUsuarios = new EscritaUsuariosDAOImple();
        escritorUsuarios.criarArquivoUsuarios(USUARIOS_ESCRITA_PATH, usuarios);
        Usuario usuario = new Usuario("Jao", "Joao da Bezerra");
        escritorUsuarios.appendUsuario(USUARIOS_ESCRITA_PATH, usuario);
        usuarios = leitor.leUsuarios(USUARIOS_ESCRITA_PATH);
        assertEquals(3, usuarios.size());
    }

    // adiciona uma lista de dois usuários no csv
    @Test
    public void testUsuarios4() {
        usuarios = leitor.leUsuarios(USUARIOS_PATH);
        escritorUsuarios = new EscritaUsuariosDAOImple();
        escritorUsuarios.criarArquivoUsuarios(USUARIOS_ESCRITA_PATH, usuarios);
        List<Usuario> novosUsuarios = new ArrayList<Usuario>();
        novosUsuarios.add(new Usuario("Banana", "Ana Maria"));
        novosUsuarios.add(new Usuario("Descobridor", "Pedro Alves Cabral"));
        escritorUsuarios.appendUsuarios(USUARIOS_ESCRITA_PATH, novosUsuarios);
        usuarios = leitor.leUsuarios(USUARIOS_ESCRITA_PATH);
        assertEquals(4, usuarios.size());
    }
}
