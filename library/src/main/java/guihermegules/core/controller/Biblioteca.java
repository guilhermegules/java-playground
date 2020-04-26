package guihermegules.core.controller;

import guihermegules.core.model.Disco;
import guihermegules.core.model.Livro;
import guihermegules.core.model.Midia;
import guihermegules.core.model.Revista;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Guilherme Gules Moreira
 * @version 2.0
 */
public class Biblioteca {

    private List<Midia> midias;

    public Biblioteca() {
        this.midias = new ArrayList<>();
    }
 
    public Optional<Midia> pesquisar(String titulo) {
        try {
            if (titulo == null && titulo.equals("")) {
                midias.stream().forEach(System.out::println);
            }
            return midias.stream().filter(m -> m.getTitulo().equalsIgnoreCase(titulo)).findAny();
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException("Não foi possível executar a pesquisa " + nullPointerException);
        } finally {
            System.out.println("Livros pesquisados: \n");
        }
    }

    public Midia pesquisar(long codigo) {
        try {
            return midias.stream().filter(a -> a.getCodigo() == codigo).findFirst().orElseThrow(() -> new IllegalArgumentException("Nenhuma mídia encontrada!"));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        } catch (NullPointerException nullPointer) {
            System.out.println("Livros estão nulos " + nullPointer.getMessage());
        } finally {
            System.out.println("Livros pesquisados: ");
        }
        return null;
    }

    public void listarAcervo() {
        midias.forEach(System.out::println);
    }
    
    private String converterGenero(int genero) {
        switch (genero) {
            case 1: return "Ação";
            case 2: return "Romance";
            case 3: return "Ficção";
            case 4: return "História";
            case 5: return "Técnico";
            case 6: return "CD-ROM";
            case 7: return "CD Audio";
            default: return null;
        }
    }

    public void cadastrarLivro(String titulo, String autor, int genero, int numPaginas, String editora) {
        try {
            midias.add(new Livro(titulo, autor, converterGenero(genero), true, numPaginas, editora));
        } catch (NullPointerException nullPointer) {
            throw new NullPointerException("Ocorreu um erro: " + nullPointer);
        } finally {
            System.out.println("Livro cadastrado com sucesso");
        }
    }

    public void cadastrarRevista(String titulo, String autor, int genero, int numPaginas, String materia) {
        try {
            midias.add(new Revista(titulo, autor, converterGenero(genero), true, numPaginas, materia));
        } catch (NullPointerException nullPointer) {
            throw new NullPointerException("Ocorreu um erro: " + nullPointer);
        } finally {
            System.out.println("Revista cadastrada com sucesso");
        }
    }

    public void cadastrarDisco(String titulo, String autor, int genero, int duracao, String formato) {
        try {
            midias.add(new Disco(titulo, autor, converterGenero(genero), true, duracao, formato));
        } catch (NullPointerException nullPointer) {
            throw new NullPointerException("Ocorreu um erro: " + nullPointer);
        } finally {
            System.out.println("Disco cadastrado com sucesso");
        }
    }

    public boolean emprestar(int codigo) {
        Midia midia = pesquisar(codigo);
        if (midia == null) {
            return false;
        }

        if (midia.isDisponivel() == false) {
            return false;
        } else {
            midia.setDisponivel(false);
            return true;
        }
    }

    public boolean devolver(int codigo) {
        Midia midia = pesquisar(codigo);
        if (midia == null) {
            return false;
        }

        if (midia.isDisponivel() == true) {
            return false;
        } else {
            midia.setDisponivel(true);
            return true;
        }
    }

    public boolean editarLivro(int codigo, String novoTitulo, String novoAutor, int novoGenero, int numPaginas, String novaEditora) {
        Livro livro = (Livro) pesquisar(codigo);
        if (livro == null) {
            return false;
        }
        if (novoTitulo != null && !novoTitulo.equals("")) {
            livro.setTitulo(novoTitulo);
        }
        if (novoTitulo != null && !novoAutor.equals("")) {
            livro.setAutor(novoAutor);
        }
        if (novoGenero != 0) {
            livro.setGenero(converterGenero(novoGenero));
        }
        if (numPaginas != 0) {
            livro.setNumPaginas(numPaginas);
        }
        if (novaEditora != null && !novaEditora.equals("")) {
            livro.setEditora(novaEditora);
        }
        return true;
    }

    public boolean editarRevista(int codigo, String novoTitulo, String novoAutor, int novoGenero, int numPaginas, String novaMateria) {
        Revista revista = (Revista)pesquisar(codigo);
        if (revista == null) {
            return false;
        }
        if (novoTitulo != null && !novoTitulo.equals("")) {
            revista.setTitulo(novoTitulo);
        }
        if (novoTitulo != null && !novoAutor.equals("")) {
            revista.setAutor(novoAutor);
        }
        if (novoGenero != 0) {
            revista.setGenero(converterGenero(novoGenero));
        }
        if (numPaginas != 0) {
            revista.setNumPaginas(numPaginas);
        }
        if (novaMateria != null && !novaMateria.equals("")) {
            revista.setMaterias(novaMateria);
        }
        return true;
    }

    public boolean editarDisco(int codigo, String novoTitulo, String novoAutor, int novoGenero, int duracao, String novoFormato) {
        Disco disco = (Disco)pesquisar(codigo);
        if (disco == null) {
            return false;
        }
        if (novoTitulo != null && !novoTitulo.equals("")) {
            disco.setTitulo(novoTitulo);
        }
        if (novoTitulo != null && !novoAutor.equals("")) {
            disco.setAutor(novoAutor);
        }
        if (novoGenero != 0) {
            disco.setGenero(converterGenero(novoGenero));
        }
        if (duracao != 0) {
            disco.setDuracao(duracao);
        }
        if (novoFormato != null && !novoFormato.equals("")) {
            disco.setFormato(novoFormato);
        }
        return true;
    }
    
    public void excluir(int codigo) {
        try {
            midias.removeIf(m -> m.getCodigo() == codigo);
        } catch (NullPointerException nullPointer) {
            System.out.println("Não foi possivel deletar: " + nullPointer);
        } finally {
            System.out.println("Midia excluida com sucesso!");
        }
    }
}
