package usp.mac321.ep2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EX1.LancamentoDespesaInvalidaException;
import EX1.LancamentoReceitaInvalidaException;
import EX1.LancamentoUsuarioInvalidoException;
import EX1.LeitorFinancasPessoaisDAOImpl;

class TestaLeitura {

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
    public void testTiposDespesas() {
        tiposDespesas = leitor.leTiposDespesas("csv/tiposDespesas.csv");
        assertEquals(6, tiposDespesas.size());  
    }

    @Test
    public void testTiposReceitas() {
        tiposReceitas = leitor.leTiposReceitas("csv/tiposReceitas.csv");
        assertEquals(4, tiposReceitas.size());  
    }

    @Test
    public void testUsuarios() {
        usuarios = leitor.leUsuarios("csv/usuarios.csv");
        assertEquals(2, usuarios.size());  
    }

    @Test
    public void testLancamentosOK() {
        lancamentos = leitor.leLancamentos("csv/lancamentos.csv");
        assertEquals(8, lancamentos.size());  
    }
    @Test
    public void testLancamentoUsuarioDesconhecido() {
        assertThrows(
            LancamentoUsuarioInvalidoException.class,
            () -> { leitor.leLancamentos("csv/lancamentosSemResponsa.csv"); }
        );
    }

    @Test
    public void testLancamentoDespesaDesconhecida() {
        assertThrows(
            LancamentoDespesaInvalidaException.class,
            () -> { leitor.leLancamentos("csv/lancamentosDespesaErrada.csv"); }
        );
    }

    @Test
    public void testLancamentoReceitaDesconhecida() {
        assertThrows(
            LancamentoReceitaInvalidaException.class,
            () -> { leitor.leLancamentos("csv/lancamentosReceitaErrada.csv"); }
        );
    }
}
