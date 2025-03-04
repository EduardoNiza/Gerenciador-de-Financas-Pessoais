package usp.mac321.ep2;

public class TipoDespesa {
    private String categoria;
    private String subcategoria;

	public TipoDespesa(String categoria, String subcategoria) {
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
        String[] despesa = {this.categoria, this.subcategoria};
        return despesa;
    }

    public void printContents() {
        System.out.println("Categoria: " + categoria);
        System.out.println("Subcategoria: " + subcategoria);
        System.out.println("==============");
    }
}