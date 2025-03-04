package EX4;
import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;
import usp.mac321.ep2.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVWriter;

public class Gerenciador {
    private List<Usuario> usuarios;
    private List<TipoDespesa> tiposDespesa;
    private List<TipoReceita> tiposReceita;
    private List<Lancamento> lancamentos;

    public Gerenciador() {
        this.usuarios = new ArrayList<>();
        this.tiposDespesa = new ArrayList<>();
        this.tiposReceita = new ArrayList<>();
        this.lancamentos = new ArrayList<>();
    }

    public void criarUsuario(String apelido, String nome) {
        usuarios.add(new Usuario(apelido, nome));
    }

    public void removerUsuario(String apelido) {
        usuarios.removeIf(usuario -> usuario.getApelido().equals(apelido));
    }

    public void criarTipoDespesa(String subcategoria, String categoria) {
        tiposDespesa.add(new TipoDespesa(subcategoria, categoria));
    }

    public void removerTipoDespesa(String subcategoria) {
        tiposDespesa.removeIf(tipoDespesa -> tipoDespesa.getSubcategoria().equals(subcategoria));
    }

    public void criarTipoReceita(String subcategoria, String categoria) {
        tiposReceita.add(new TipoReceita(subcategoria, categoria));
    }

    public void removerTipoReceita(String subcategoria) {
        tiposReceita.removeIf(tipoReceita -> tipoReceita.getSubcategoria().equals(subcategoria));
    }

    public void criarLancamento(String id, String data, String usuario, String despesa, String subcategoria, String valor, String descricao) {
        lancamentos.add(new Lancamento(id, data, usuario, despesa, subcategoria, valor, descricao));
    }

    public void editarLancamento(String id, String data, String usuario, String despesa, String subcategoria, String valor, String descricao) {
        for (Lancamento lancamento : lancamentos) {
            if (lancamento.getIdLancamento().equals(id)) {
                lancamento.setData(data);
                lancamento.setUsuario(usuario);
                lancamento.setTipo(despesa);
                lancamento.setSubcategoria(subcategoria);
                lancamento.setValor(valor);
                lancamento.setDescricao(descricao);
                break;
            }
        }
    }

    public void salvarEstado(String usuariosFile, String despesasFile, String receitasFile, String lancamentosFile) {
        salvarUsuarios(usuariosFile);
        salvarTiposDespesas(despesasFile);
        salvarTiposReceitas(receitasFile);
        salvarLancamentos(lancamentosFile);
    }

    private void salvarUsuarios(String usuariosFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(usuariosFile))) {
            String[] header = {"ID", "Nome"};
            writer.writeNext(header);
            for (Usuario usuario : usuarios) {
                writer.writeNext(new String[]{usuario.getApelido(), usuario.getNome()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarTiposDespesas(String despesasFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(despesasFile))) {
            String[] header = {"Subcategoria", "Categoria"};
            writer.writeNext(header);
            for (TipoDespesa tipoDespesa : tiposDespesa) {
                writer.writeNext(new String[]{tipoDespesa.getSubcategoria(), tipoDespesa.getCategoria()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarTiposReceitas(String receitasFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(receitasFile))) {
            String[] header = {"Subcategoria", "Categoria"};
            writer.writeNext(header);
            for (TipoReceita tipoReceita : tiposReceita) {
                writer.writeNext(new String[]{tipoReceita.getSubcategoria(), tipoReceita.getCategoria()});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void salvarLancamentos(String lancamentosFile) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(lancamentosFile))) {
            String[] header = {"ID", "Data", "Usuario", "Despesa", "Subcategoria", "Valor", "Descricao"};
            writer.writeNext(header);
            for (Lancamento lancamento : lancamentos) {
                writer.writeNext(lancamento.getAll());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double computarTotal(String startDate, String endDate) {
        double total = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            for (Lancamento lancamento : lancamentos) {
                Date dataLancamento = sdf.parse(lancamento.getData());
                if (!dataLancamento.before(start) && !dataLancamento.after(end)) {
                    total += Double.parseDouble(lancamento.getValor());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return total;
    }

    public double computarTotalPorTipo(String tipo, String startDate, String endDate) {
        double total = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            for (Lancamento lancamento : lancamentos) {
                Date dataLancamento = sdf.parse(lancamento.getData());
                if (!dataLancamento.before(start) && !dataLancamento.after(end) && lancamento.getDespesa().equals(tipo)) {
                    total += Double.parseDouble(lancamento.getValor());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return total;
    }

    public double computarTotalPorSubcategoria(String subcategoria, String startDate, String endDate) {
        double total = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            for (Lancamento lancamento : lancamentos) {
                Date dataLancamento = sdf.parse(lancamento.getData());
                if (!dataLancamento.before(start) && !dataLancamento.after(end) && lancamento.getSubcategoria().equals(subcategoria)) {
                    total += Double.parseDouble(lancamento.getValor());
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return total;
    }

    public void imprimirRelatorio(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);
            double totalDespesas = 0;
            double totalReceitas = 0;

            System.out.println("Relatório Financeiro");
            System.out.println("====================");
            System.out.println("Intervalo: " + startDate + " - " + endDate);
            System.out.println();

            System.out.println("Despesas:");
            for (Lancamento lancamento : lancamentos) {
                Date dataLancamento = sdf.parse(lancamento.getData());
                if (!dataLancamento.before(start) && !dataLancamento.after(end) && lancamento.getDespesa().equals("Despesa")) {
                    totalDespesas += Double.parseDouble(lancamento.getValor());
                    lancamento.printContents();
                }
            }

            System.out.println("Total de Despesas: " + totalDespesas);
            System.out.println();

            System.out.println("Receitas:");
            for (Lancamento lancamento : lancamentos) {
                Date dataLancamento = sdf.parse(lancamento.getData());
                if (!dataLancamento.before(start) && !dataLancamento.after(end) && lancamento.getDespesa().equals("Receita")) {
                    totalReceitas += Double.parseDouble(lancamento.getValor());
                    lancamento.printContents();
                }
            }

            System.out.println("Total de Receitas: " + totalReceitas);
            System.out.println();

            System.out.println("Saldo: " + (totalReceitas - totalDespesas));
            System.out.println("====================");

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<TipoDespesa> getTiposDespesa() {
        return tiposDespesa;
    }

    public List<TipoReceita> getTiposReceita() {
        return tiposReceita;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }
}
