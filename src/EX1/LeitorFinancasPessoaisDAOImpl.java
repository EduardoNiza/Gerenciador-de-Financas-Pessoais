package EX1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import usp.mac321.ep2.Lancamento;
import usp.mac321.ep2.TipoDespesa;
import usp.mac321.ep2.TipoReceita;
import usp.mac321.ep2.Usuario;
import usp.mac321.ep2.LeitorFinancasPessoaisDAO;

public class LeitorFinancasPessoaisDAOImpl implements LeitorFinancasPessoaisDAO {

    private CSVReader AbridorDeArquivos(String nomeArquivo) {
        try {
            FileReader filereader = new FileReader(nomeArquivo);
            return new CSVReaderBuilder(filereader).withSkipLines(1).build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo não encontrado: " + nomeArquivo, e);
        }
    }

    private boolean checaSeUsuarioEstaNaLista(String apelido, List<Usuario> listaUsuarios) {
        for (Usuario usuario : listaUsuarios) {
            if (apelido.equals(usuario.getApelido())) {
                return true;
            }
        }
        return false;
    }

    private boolean checaSeTipoDespesaEstaNaLista(String subcategoria, List<TipoDespesa> listaTiposDespesas) {
        for (TipoDespesa tipoDespesa : listaTiposDespesas) {
            if (subcategoria.equals(tipoDespesa.getSubcategoria())) {
                return true;
            }
        }
        return false;
    }

    private boolean checaSeTipoReceitaEstaNaLista(String subcategoria, List<TipoReceita> listaTiposReceitas) {
        for (TipoReceita tipoReceita : listaTiposReceitas) {
            if (subcategoria.equals(tipoReceita.getSubcategoria())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Usuario> leUsuarios(String nomeArquivoUsuarios) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        try (CSVReader reader = AbridorDeArquivos(nomeArquivoUsuarios)) {
            String[] nextUsuario;
            while ((nextUsuario = reader.readNext()) != null) {
                Usuario usuario = new Usuario(nextUsuario[0], nextUsuario[1]);
                listaUsuarios.add(usuario);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Erro na leitura do arquivo: " + nomeArquivoUsuarios, e);
        }
        return listaUsuarios;
    }

    @Override
    public List<TipoDespesa> leTiposDespesas(String nomeArquivoTiposDespesas) {
        List<TipoDespesa> listaTiposDespesas = new ArrayList<>();
        try (CSVReader reader = AbridorDeArquivos(nomeArquivoTiposDespesas)) {
            String[] nextTipoDespesa;
            while ((nextTipoDespesa = reader.readNext()) != null) {
                TipoDespesa tipoDespesa = new TipoDespesa(nextTipoDespesa[0], nextTipoDespesa[1]);
                listaTiposDespesas.add(tipoDespesa);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Erro na leitura do arquivo: " + nomeArquivoTiposDespesas, e);
        }
        return listaTiposDespesas;
    }

    @Override
    public List<TipoReceita> leTiposReceitas(String nomeArquivoTiposReceitas) {
        List<TipoReceita> listaTiposReceitas = new ArrayList<>();
        try (CSVReader reader = AbridorDeArquivos(nomeArquivoTiposReceitas)) {
            String[] nextTipoReceita;
            while ((nextTipoReceita = reader.readNext()) != null) {
                TipoReceita tipoReceita = new TipoReceita(nextTipoReceita[0], nextTipoReceita[1]);
                listaTiposReceitas.add(tipoReceita);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Erro na leitura do arquivo: " + nomeArquivoTiposReceitas, e);
        }
        return listaTiposReceitas;
    }

    @Override
    public List<Lancamento> leLancamentos(String nomeArquivoLancamentos) {
        List<Usuario> listaUsuarios = leUsuarios("csv/usuarios.csv");
        List<TipoDespesa> listaTiposDespesas = leTiposDespesas("csv/tiposDespesas.csv");
        List<TipoReceita> listaTiposReceitas = leTiposReceitas("csv/tiposReceitas.csv");
        List<Lancamento> listaLancamentos = new ArrayList<>();
        try (CSVReader reader = AbridorDeArquivos(nomeArquivoLancamentos)) {
            String[] nextLancamento;
            while ((nextLancamento = reader.readNext()) != null) {
                if (!checaSeUsuarioEstaNaLista(nextLancamento[2], listaUsuarios)) {
        			throw new LancamentoUsuarioInvalidoException(nextLancamento[0], nextLancamento[2]);
                }
                
                if (nextLancamento[3].equals("TRUE") && !checaSeTipoDespesaEstaNaLista(nextLancamento[4], listaTiposDespesas)) {
        			throw new LancamentoDespesaInvalidaException(nextLancamento[0], nextLancamento[4]);

                }
                if (nextLancamento[3].equals("FALSE") && !checaSeTipoReceitaEstaNaLista(nextLancamento[4], listaTiposReceitas)) {
        			throw new LancamentoReceitaInvalidaException(nextLancamento[0], nextLancamento[4]);
                }
                
                Lancamento lancamento = new Lancamento(
                    nextLancamento[0], 
                    nextLancamento[1], 
                    nextLancamento[2], 
                    nextLancamento[3], 
                    nextLancamento[4],
                    nextLancamento[5], 
                    nextLancamento[6] 
                );
                listaLancamentos.add(lancamento);
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException("Erro na leitura do arquivo: " + nomeArquivoLancamentos, e);
        }
        return listaLancamentos;
    }
    

}