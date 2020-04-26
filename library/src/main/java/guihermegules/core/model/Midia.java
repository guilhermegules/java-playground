package guihermegules.core.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Guilherme Gules Moreira
 * @version 2.0
 */
public abstract class Midia {
    
    private static AtomicInteger nextId = new AtomicInteger(1);
    protected long codigo;
    protected String titulo;
    protected String autor;
    protected String genero;
    protected boolean disponivel;

    public Midia() {}
    
    public Midia(String titulo, String autor, String genero, boolean disponivel) {
        codigo = nextId.getAndAdd(1);
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponivel = disponivel;
    }
    
    public long getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "\ncódigo: " + codigo
                + "\ntitulo: " + titulo
                + "\nautor: " + autor 
                + "\ngenero: " + genero 
                + "\ndisponivel: " + disponivel;
    }
   
}
