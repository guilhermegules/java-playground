package guihermegules.core.model;

/**
 *
 * @author Guilherme Gules Moreira
 * @version 2.0
 */
public class Livro extends Midia {

    private int numPaginas;
    private String editora;

    public Livro(String titulo, String autor, String genero, boolean disponivel, int paginas, String editora) {
        super(titulo, autor, genero, disponivel);
        this.numPaginas = paginas;
        this.editora = editora;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return  codigo + " [Livro] " 
                + " " + titulo
                + " " + autor
                + " " + numPaginas + " pags. "
                + " " + editora
                + " " + genero
                + " " + disponivel;
                
    }
}
