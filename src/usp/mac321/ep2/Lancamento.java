package usp.mac321.ep2;

import EX1.LeitorFinancasPessoaisDAOImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import EX3.LancamentoInexistenteException;
import EX3.State;



public class Lancamento {
    private String idLancamento;
    private String data;
    private String despesa;
    private String usuario;
    private String subcategoria;
    private String descricao;
    private String valor;
	private State state;

    
    public Lancamento(String idLancamento, String data, String usuario, String despesa, String subcategoria, String valor, String descricao) {
        this.idLancamento = idLancamento;
        this.data = data;
        this.despesa = despesa;
        this.usuario = usuario;
        this.subcategoria = subcategoria;
        this.descricao = descricao;
        this.valor = valor;
    }

    public void setIdLancamento(String idLancamento) {
        this.idLancamento = idLancamento;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTipo(String despesa) {
        this.despesa = despesa;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getIdLancamento() {
        return idLancamento;
    }

    public String getData() {
        return data;
    }

    public String getDespesa() {
        return despesa;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getValor() {
        return valor;
    }

    public String[] getAll() {
        String[] lancamento = {this.idLancamento, this.data, this.usuario, this.despesa, this.subcategoria, this.valor, this.descricao};

        return lancamento;
    }

    public void printContents() {
        System.out.println("IdLancamento: " + idLancamento);
        System.out.println("Data: " + data);
        System.out.println("Despesa: " + despesa);
        System.out.println("Usuario: " + usuario);
        System.out.println("Subcategoria: " + subcategoria);
        System.out.println("Valor: " + valor);
        System.out.println("Descricao: " + descricao);
        System.out.println("==============");
    }
    
	public void setState(State newState) {
		this.state = newState;
	}
	
    public State getState() {
        return this.state;
    }
	
    public int checaState(Lancamento lancamento, String nomeArquivoLancamentos, String nomeArquivoUsuarios) {
        LeitorFinancasPessoaisDAOImpl leitor = new LeitorFinancasPessoaisDAOImpl();
        List<Lancamento> lancamentos = leitor.leLancamentos(nomeArquivoLancamentos);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yy");

        // Verifica se o lançamento está na lista
        boolean lancamentoExiste = false;
        for (Lancamento l : lancamentos) {
            if (l.getIdLancamento().equals(lancamento.getIdLancamento())) {
                lancamentoExiste = true;
                break;
            }
        }
        if (!lancamentoExiste) {
            throw new LancamentoInexistenteException(lancamento.idLancamento);
        }

        try {
            Date dataObjeto = dateformat.parse(lancamento.getData());
            Date hoje = new Date();

            if (hoje.before(dataObjeto)) {
                // Planejado
                return 1;
            } else {
                // Executado
                return 0;
            }
        } catch (ParseException e) {
            return 2; // Inválido
        }
    
    }
}