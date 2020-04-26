package guihermegules.core.model;

/**
 *
 * @author Guilherme Gules Moreira
 * @version 2.0
 */
public class Revista extends Midia {

    private String materias;
    private int numPaginas;
    
    public Revista() {
    }
    
    public Revista(String titulo, String autor, String genero, boolean disponivel, int numPaginas, String materias) {
        super(titulo, autor, genero, disponivel);
        this.numPaginas = numPaginas;
        this.materias = materias;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    @Override
    public String toString() {
        return codigo + " [Revis.] " 
                + " " + titulo
                + " " + autor
                + " " + numPaginas + " pags. "
                + " " + genero
                + " " + disponivel;
    }
    
    
}
