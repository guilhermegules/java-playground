package bibliotecav1;

/**
 *
 * @author Guilherme Gules Moreira
 */
public class Livro {
    
    private long codigo;
    private String titulo;
    private String autor;
    private String genero;
    private boolean disponivel;

    public Livro(){}
    
    public Livro(long codigo, String titulo, String autor, String genero, boolean disponivel) {
        this.codigo = codigo;
        this.disponivel = disponivel;
        this.autor = autor;
        this.titulo = titulo;
        this.genero = genero;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public long getCodigo() {
        return codigo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
}
