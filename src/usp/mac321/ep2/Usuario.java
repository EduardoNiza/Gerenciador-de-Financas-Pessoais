package usp.mac321.ep2;

public class Usuario {
    private String apelido;
    private String nome;

    public Usuario(String apelido, String nome) {
        this.apelido = apelido;
        this.nome = nome;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getNome() {
        return nome;
    }

    public void printContents() {
        System.out.println("Apelido: " + apelido);
        System.out.println("Nome: " + nome);                
        System.out.println("==============");
    }

    public String[] getAll() {
        String[] usuario = {this.apelido, this.nome};
        return usuario;
    }
}