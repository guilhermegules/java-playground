package guihermegules.core.model;

/**
 *
 * @author Guilherme Gules Moreira
 * @version 2.0
 */
public class Disco extends Midia{
    
    private int duracao;
    private String formato;
    
    public Disco() {}
    
    public Disco(String titulo, String autor, String genero, boolean disponivel, int duracao, String formato) {
        super(titulo, autor, genero, disponivel);
        this.duracao = duracao;
        this.formato = formato;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    @Override
    public String toString() {
        return codigo + " [Livro] " 
                + " " + titulo
                + " " + autor
                + " " + duracao + " min "
                + " " + formato
                + " " + genero
                + " " + disponivel;
    }
}
