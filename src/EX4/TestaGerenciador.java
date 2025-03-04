package EX4;
import org.junit.Before;
import org.junit.Test;

import usp.mac321.ep2.Lancamento;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestaGerenciador {

	    private Gerenciador gerenciador;

	    @Before
	    public void setUp() {
	        gerenciador = new Gerenciador();
	        gerenciador.criarUsuario("1", "Alice");
	        gerenciador.criarUsuario("2", "Bob");
	        gerenciador.criarTipoDespesa("Alimentação", "Comida");
	        gerenciador.criarTipoReceita("Salário", "Trabalho");
	        gerenciador.criarLancamento("1", "10/06/2024", "Alice", "TRUE", "Alimentação", "50", "Almoço");
	        gerenciador.criarLancamento("2", "15/06/2024", "Bob", "FALSE", "Salário", "2000", "Pagamento");
	    }
	   // /*
	    @Test
	    public void testCriarUsuario() {
	        gerenciador.criarUsuario("3", "Charlie");
	        assertEquals(3, gerenciador.getUsuarios().size());
	    }

	    @Test
	    public void testRemoverUsuario() {
	        gerenciador.removerUsuario("1");
	        assertEquals(1, gerenciador.getUsuarios().size());
	    }

	    @Test
	    public void testCriarTipoDespesa() {
	        gerenciador.criarTipoDespesa("Transporte", "Mobilidade");
	        assertEquals(2, gerenciador.getTiposDespesa().size());
	    }

	   

	    @Test
	    public void testRemoverTipoReceita() {
	        gerenciador.removerTipoReceita("Salário");
	        assertEquals(0, gerenciador.getTiposReceita().size());
	    }

	    @Test
	    public void testCriarLancamento() {
	        gerenciador.criarLancamento("3", "20/06/2024", "Alice", "TRUE", "Alimentação", "30", "Jantar");
	        assertEquals(3, gerenciador.getLancamentos().size());
	    }

	    @Test
	    public void testEditarLancamento() {
	        gerenciador.editarLancamento("1", "11/06/2024", "Alice", "TRUE", "Alimentação", "60", "Almoço atualizado");
	        Lancamento lancamento = gerenciador.getLancamentos().get(0);
	        assertEquals("11/06/2024", lancamento.getData());
	        assertEquals("60", lancamento.getValor());
	        assertEquals("Almoço atualizado", lancamento.getDescricao());
	    }

	    @Test
	    public void testComputarTotal() {
	        double total = gerenciador.computarTotal("01/06/2024", "30/06/2024");
	        assertEquals(2050, total, 0.001);
	    }

	    @Test
	    public void testComputarTotalPorTipo() {
	        double totalDespesas = gerenciador.computarTotalPorTipo("TRUE", "01/06/2024", "30/06/2024");
	        assertEquals(50, totalDespesas, 0.001);

	        double totalReceitas = gerenciador.computarTotalPorTipo("FALSE", "01/06/2024", "30/06/2024");
	        assertEquals(2000, totalReceitas, 0.001);
	    }

	    @Test
	    public void testComputarTotalPorSubcategoria() {
	        double totalAlimentacao = gerenciador.computarTotalPorSubcategoria("Alimentação", "01/06/2024", "30/06/2024");
	        assertEquals(50, totalAlimentacao, 0.001);

	        double totalSalario = gerenciador.computarTotalPorSubcategoria("Salário", "01/06/2024", "30/06/2024");
	        assertEquals(2000, totalSalario, 0.001);
	    }
	    
	  //  */
	    @Test
	    public void testRemoverTipoDespesa() {
	        gerenciador.removerTipoDespesa("Alimentação");
	        assertEquals(0, gerenciador.getTiposDespesa().size());
	    }

	    @Test
	    public void testCriarTipoReceita() {
	        gerenciador.criarTipoReceita("Bônus", "Trabalho");
	        assertEquals(2, gerenciador.getTiposReceita().size());
	    }
	   // /*
	    @Test
	    public void testImprimirRelatorio() {
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outContent));

	        gerenciador.imprimirRelatorio("01/06/2024", "30/06/2024");

	        String expectedOutput = "Relatório Financeiro\n" +
	                "====================\n" +
	                "Intervalo: 01/06/2024 - 30/06/2024\n" +
	                "\n" +
	                "Despesas:\n" +
	                "IdLancamento: 1\n" +
	                "Data: 10/06/2024\n" +
	                "Despesa: Despesa\n" +
	                "Usuario: Alice\n" +
	                "Subcategoria: Alimentação\n" +
	                "Valor: 50\n" +
	                "Descricao: Almoço\n" +
	                "==============\n" +
	                "Total de Despesas: 50.0\n" +
	                "\n" +
	                "Receitas:\n" +
	                "IdLancamento: 2\n" +
	                "Data: 15/06/2024\n" +
	                "Despesa: Receita\n" +
	                "Usuario: Bob\n" +
	                "Subcategoria: Salário\n" +
	                "Valor: 2000\n" +
	                "Descricao: Pagamento\n" +
	                "==============\n" +
	                "Total de Receitas: 2000.0\n" +
	                "\n" +
	                "Saldo: 1950.0\n" +
	                "====================\n";

	        assertEquals(expectedOutput, outContent.toString());
	    }
	   // */
	}

