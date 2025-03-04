package usp.mac321.ep2;

public class TipoReceita {
    private String categoria;
    private String subcategoria;

    public TipoReceita(String categoria, String subcategoria) {
        this.categoria = categoria;
        this.subcategoria = subcategoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public String getSubcategoria() {
        return this.subcategoria;
    }

    public String[] getAll() {
        String[] receita = {this.categoria, this.subcategoria};
        return receita;
    }

    public void printContents() {
        System.out.println("Categoria: " + categoria);
        System.out.println("Subcategoria: " + subcategoria);
        System.out.println("==============");
    }
}