package usp.mac321.ep2;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import EX1.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class TestaLeitorFinancasPessoais {

    private static final String USUARIOS_PATH = "csv/usuarios.csv";
    private static final String TIPOS_DESPESAS_PATH = "csv/tiposDespesas.csv";
    private static final String TIPOS_RECEITAS_PATH = "csv/tiposReceitas.csv";
    private static final String LANCAMENTOS_PATH = "csv/lancamentos.csv";
    private static final String LANCAMENTOS_ERRO_DESPESA_PATH = "csv/lancamentosDespesaErrada.csv";
    private static final String LANCAMENTOS_ERRO_RECEITA_PATH = "csv/lancamentosReceitaErrada.csv";
    private static final String LANCAMENTOS_SEM_RESPONSAVEL_PATH = "csv/lancamentosSemResponsa.csv";

    List<Usuario> usuarios;
    List<TipoDespesa> tiposDespesas;
    List<TipoReceita> tiposReceitas;
    List<Lancamento> lancamentos;
    LeitorFinancasPessoaisDAO leitor;

    @BeforeEach
    void setUp() throws Exception {
        leitor = new LeitorFinancasPessoaisDAOImpl();
    }

    @AfterEach
    void tearDown() throws Exception {
        leitor = null;
    }

    @Test
    void testUsuarios() {
        usuarios = leitor.leUsuarios(USUARIOS_PATH);
        assertEquals(2, usuarios.size());
    }

    @Test
    void testTiposDespesas() {
        tiposDespesas = leitor.leTiposDespesas(TIPOS_DESPESAS_PATH);
        assertEquals(6, tiposDespesas.size());
    }

    @Test
    void testTiposReceitas() {
        tiposReceitas = leitor.leTiposReceitas(TIPOS_RECEITAS_PATH);
        assertEquals(4, tiposReceitas.size());
    }

    @Test
    void testLancamentos() {
        lancamentos = leitor.leLancamentos(LANCAMENTOS_PATH);
        assertEquals(8, lancamentos.size());
    }

    @Test
    void testLancamentosComErroDespesa() {
        assertThrows(
            LancamentoDespesaInvalidaException.class,
            () -> { leitor.leLancamentos(LANCAMENTOS_ERRO_DESPESA_PATH); }
        );
    }

    @Test
    void testLancamentosComErroReceita() {
        assertThrows(
            LancamentoReceitaInvalidaException.class,
            () -> { leitor.leLancamentos(LANCAMENTOS_ERRO_RECEITA_PATH); }
        );
    }

    @Test
    void testLancamentosSemResponsavel() {
        assertThrows(
            LancamentoUsuarioInvalidoException.class,
            () -> { leitor.leLancamentos(LANCAMENTOS_SEM_RESPONSAVEL_PATH); }
        );
    }
}