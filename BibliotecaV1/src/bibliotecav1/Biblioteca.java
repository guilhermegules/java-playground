package bibliotecav1;

/**
 *
 * @author Guilherme Gules Moreira
 */
public class Biblioteca {
    
    private Livro[] acervo;
    private long proximoCodigo = 1;

    public Biblioteca(int tamanho) {
        this.acervo = new Livro[tamanho];
    }
    
    public Livro[] pesquisar(String titulo) {
        try {
            if(titulo == null || titulo.equals("")) return acervo;
            Livro[] auxiliar = new Livro[acervo.length];
            titulo = titulo.toLowerCase();
            for(int i = 0; i < acervo.length; i++) {
                if(acervo[i] != null && acervo[i].getTitulo().toLowerCase().contains(titulo)) {
                   auxiliar[i] = acervo[i];
                } 
            }
            return auxiliar;
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Não foi possível executar a pesquisa " + nullPointerException);
        } finally {
            System.out.println("Livros pesquisados: \n");
        }
    }
    
    public Livro pesquisar(long codigo) {
        try {
            for (Livro livro : acervo) {
                if (livro != null && livro.getCodigo() == codigo) {
                    return livro;
                }
            }      
        } catch (NullPointerException nullPointer) {
            throw new NullPointerException("O acervo está vázio ou "
                    + "o livro pesquisado não foi encontrado "
                    + nullPointer.getMessage());
        } finally {
            System.out.println("Livros pesquisados: ");
        }
        return null;
    }
    
    private String converterGenero(int genero) {
        switch(genero) {
            case 1: return "Ação";
            case 2: return "Romance";
            case 3: return "Ficção";
            case 4: return "História";
            case 5: return "Técnico";
            default: return null;
        }
    }
    
    public void cadasrarLivro(String titulo, String autor, int genero) {
        try {
            Livro livro = new Livro(proximoCodigo, titulo, autor, converterGenero(genero), true);
            for(int i = 0; i < acervo.length; i++) {
                if(acervo[i] == null) {
                    acervo[i] = livro;
                    proximoCodigo++;
                    return;
                    }
            }   
        } catch (ArrayIndexOutOfBoundsException arrayIndexException) {
            throw new ArrayIndexOutOfBoundsException("Sem espaço disponível no acervo");
        } finally {
            System.out.println("Livro cadastrado com sucesso");
        }
    }
    
    public boolean emprestarLivro(int codigo) {
        Livro livro = pesquisar(codigo);
        if(livro == null) return false;
        
        if(livro.isDisponivel() == false) return false;
        else {
            livro.setDisponivel(false);
            return true;
        }
    }
    
    public boolean devolverLivro(int codigo) {
        Livro livro = pesquisar(codigo);
        if(livro == null) return false;
        
        if(livro.isDisponivel() == true) return false;
        else {
            livro.setDisponivel(true);
            return true;
        }
    }
    
    public boolean editarLivro(int codigo, String novoTitulo, String novoAutor, int novoGenero) {
        Livro livro = pesquisar(codigo);
        if(livro == null) return false;
        if(novoTitulo != null && !novoTitulo.equals("")) livro.setTitulo(novoTitulo);
        if(novoTitulo != null && !novoAutor.equals("")) livro.setAutor(novoAutor);
        if(novoGenero != 0) livro.setGenero(converterGenero(novoGenero));
        return true;
    }
    
    
    public boolean excluirLivro(int codigo) {
        for(Livro livro: acervo) {
            if(livro != null && livro.getCodigo() == codigo) {
                livro = null;
                return true;
            } 
        }
        return false;
    }
}
