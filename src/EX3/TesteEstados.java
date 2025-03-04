package EX3;


import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import usp.mac321.ep2.Lancamento;
import EX1.LeitorFinancasPessoaisDAOImpl;


class TesteEstados {
    private static final String LANCAMENTOS_PATH = "csv/csvState/lancamentos.csv";
    private static final String USUARIOS_PATH = "csv/csvState/usuarios.csv";

    List<Lancamento> lancamentos;
    static LeitorFinancasPessoaisDAOImpl leitor;

    @BeforeAll
    public static void setUp() {
        leitor = new LeitorFinancasPessoaisDAOImpl();
    }

    @Test
    public void testLancamentoComErro() {
        Lancamento lancamento = new Lancamento("L999", "30/06/24", "Zezinho", "TRUE", "Jardim da Infância", "600", "Escolinha SóTela");

        assertThrows(
            LancamentoInexistenteException.class,
            () -> { lancamento.checaState(lancamento, LANCAMENTOS_PATH, USUARIOS_PATH); }
        );
    }

    
    @Test
    public void testLancamentoPlanejado() {
        Lancamento lancamento = new Lancamento("1", "30/12/24", "Pai", "TRUE", "Principal", "10000", "Salário do papai");
        lancamento.setState(new StatePlanejado());
        int state = lancamento.checaState(lancamento, LANCAMENTOS_PATH, USUARIOS_PATH);
        assertEquals(1, state);
    }

    @Test
    public void testLancamentoExecutado() {
        Lancamento lancamento = new Lancamento("2", "30/05/24", "Pai", "TRUE", "Bico", "1000", "Conserto computador vizinha");
        lancamento.setState(new StateExecutado());
        int state = lancamento.checaState(lancamento, LANCAMENTOS_PATH, USUARIOS_PATH);
        assertEquals(0, state);
    }

    @Test
    public void testLancamentoInvalido() {
        Lancamento lancamento = new Lancamento("3", "", "Pai", "TRUE", "Cinema", "50", "Barbie");
        lancamento.setState(new StatePlanejado());
        int state = lancamento.checaState(lancamento, LANCAMENTOS_PATH, USUARIOS_PATH);
        assertEquals(2, state);
    }
}
