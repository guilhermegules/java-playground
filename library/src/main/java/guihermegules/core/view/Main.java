package guihermegules.core.view;

import guihermegules.core.authentication.AutenticacaoUsuario;
import guihermegules.core.controller.Biblioteca;
import guihermegules.core.model.Disco;
import guihermegules.core.model.Livro;
import guihermegules.core.model.Midia;
import guihermegules.core.model.Revista;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 *
 * @author Guilherme Gules Moreira
 * @version 2.0
 */
public class Main {

    private static Scanner reader = new Scanner(System.in, StandardCharsets.ISO_8859_1.name());
    private static Biblioteca biblioteca;

    public static void main(String[] args) {
        biblioteca = new Biblioteca();
        String usuario;
        String senha;
        int option = 0;
        do {
            System.out.println("Digite o seu usuário: ");
            usuario = reader.next();
            System.out.println("Digite a sua senha: ");
            senha = reader.next();
            if (!AutenticacaoUsuario.estaLiberado(usuario, senha)) {
                System.out.println("Usuário incorreto, digite novamente");
            }
        } while (!AutenticacaoUsuario.estaLiberado(usuario, senha));
        while (option != 11) {
            System.out.println("Bem-vindo a Biblioteca virtual QI");
            System.out.println("Escolha a sua opção: "
                    + "\n1 - Cadastrar livro"
                    + "\n2 - Cadastrar Revista"
                    + "\n3 - Cadastrar Disco"
                    + "\n4 - Pesquisar"
                    + "\n5 - Emprestar"
                    + "\n6 - Devolver"
                    + "\n7 - Editar Livro"
                    + "\n8 - Editar Revista"
                    + "\n9 - Editar Disco"
                    + "\n10 - Listar Acervo" 
                    + "\n11 - Listar detalhes"
                    + "\n12 - Remover"
                    + "\n13 - Sair");
            option = reader.nextInt();
            reader.nextLine();
            switch (option) {
                case 1:
                    System.out.println("-------Cadastro De Livros-------");
                    System.out.println("Digite o titulo do livro: ");
                    String tituloLivro = reader.nextLine();
                    System.out.println("Digite o autor do livro: ");
                    String autorLivro = reader.nextLine();
                    System.out.println("Escolha um gênero do livro(númeral): "
                            + "\n1 - Ação"
                            + "\n2 - Romance"
                            + "\n3 - Ficção"
                            + "\n4 - História"
                            + "\n5 - Técnico");
                    int generoLivro = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite o número de páginas: ");
                    int numPaginasLivro = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite o nome da editora: ");
                    String editora = reader.nextLine();
                    biblioteca.cadastrarLivro(tituloLivro, autorLivro, generoLivro, numPaginasLivro, editora);
                    break;
                case 2:
                    System.out.println("-------Cadastro De Revistas-------");
                    System.out.println("Digite o titulo do revista: ");
                    String tituloRevista = reader.nextLine();
                    System.out.println("Digite o autor da revista: ");
                    String autorRevista = reader.nextLine();
                    System.out.println("Escolha um gênero da revista(númeral): "
                            + "\n1 - Ação"
                            + "\n2 - Romance"
                            + "\n3 - Ficção"
                            + "\n4 - História"
                            + "\n5 - Técnico");
                    int generoRevista = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite o número de páginas: ");
                    int numPaginasRevista = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite o nome da materia: ");
                    String materia = reader.nextLine();
                    biblioteca.cadastrarRevista(tituloRevista, autorRevista, generoRevista, numPaginasRevista, materia);
                    break;
                case 3:
                    System.out.println("-------Cadastro De Disco-------");
                    System.out.println("Digite o titulo do disco: ");
                    String tituloDisco = reader.nextLine();
                    System.out.println("Digite o autor do disco: ");
                    String autorDisco = reader.nextLine();
                    System.out.println("Escolha um gênero do disco(númeral): "
                            + "\n1 - Ação"
                            + "\n2 - Romance"
                            + "\n3 - Ficção"
                            + "\n4 - História"
                            + "\n5 - Técnico"
                            + "\n6 - CD-ROM"
                            + "\n7 - CD Audio");
                    int generoDisco = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite a duração: ");
                    int duracao = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite o nome da editora: ");
                    String formato = reader.nextLine();
                    biblioteca.cadastrarDisco(tituloDisco, autorDisco, generoDisco, duracao, formato);
                    break;
                case 4:
                    System.out.println("-------Pesquisa-------");
                    System.out.println("Escolha o tipo de pesquisa"
                            + "\n1-Pesquisar por titulo"
                            + "\n2-Pesquisar por código");
                    int opcao = reader.nextInt();
                    reader.nextLine();
                    switch (opcao) {
                        case 1:
                            System.out.println("Digite o titulo da midia");
                            String pesquisaTitulo = reader.nextLine();
                            System.out.println(biblioteca.pesquisar(pesquisaTitulo));
                            break;
                        case 2:
                            System.out.println("Digite o código da midia");
                            long codigo = reader.nextInt();
                            reader.nextLine();
                            System.out.println(biblioteca.pesquisar(codigo));
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                case 5:
                    System.out.println("-------Empréstimo-------");
                    System.out.println("Digite o código da mídia para empréstimo: ");
                    int codigoEmprestimo = reader.nextInt();
                    reader.nextLine();
                    boolean sucessoEmprestimo = biblioteca.emprestar(codigoEmprestimo);
                    if (sucessoEmprestimo) {
                        System.out.println("Livro emprestado com sucesso!");
                    } else {
                        System.out.println("Não foi possível emprestar o livro!!!");
                    }
                    break;
                case 6:
                    System.out.println("-------Devolução-------");
                    System.out.println("Digite o código da mídia para devoluçao: ");
                    int codigoDevolucao = reader.nextInt();
                    reader.nextLine();
                    boolean sucessoDevolucao = biblioteca.devolver(codigoDevolucao);
                    if (sucessoDevolucao) {
                        System.out.println("Devolução realizada com sucesso!");
                    } else {
                        System.out.println("Não foi possível devolver o livro!!!");
                    }
                    break;
                case 7:
                    System.out.println("-------Editar Livro------");
                    System.out.println("Digite o código do livro: ");
                    int codigoEdicaoLivro = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite o novo titulo, ou deixe em branco: ");
                    String novoTituloLivro = reader.nextLine();
                    System.out.println("Digite um novo autor, ou deixe em branco: ");
                    String novoAutorLivro = reader.nextLine();
                    System.out.println("Digite um novo gênero: "
                            + "\n0 - Manter o atual"
                            + "\n1 - Ação"
                            + "\n2 - Romance"
                            + "\n3 - Ficção"
                            + "\n4 - História"
                            + "\n5 - Técnico");
                    int novoGeneroLivro = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite um novo número de páginas ou deixe em branco: ");
                    int novoNumeroPaginas = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite uma nova editora ou deixe em branco: ");
                    String novaEditora = reader.nextLine();
                    boolean sucessoEdicao = biblioteca.editarLivro(
                            codigoEdicaoLivro,
                            novoTituloLivro,
                            novoAutorLivro,
                            novoGeneroLivro,
                            novoNumeroPaginas,
                            novaEditora
                    );
                    if (sucessoEdicao) {
                        System.out.println("Edição realizada com sucesso!!!");
                    } else {
                        System.out.println("Não foi possível alterar, livro não encontrado!");
                    }
                    break;
                case 8:
                    System.out.println("-------Editar Revista------");
                    System.out.println("Digite o código do livro: ");
                    int codigoEdicaoRevista = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite o novo titulo, ou deixe em branco: ");
                    String novoTituloRevista = reader.nextLine();
                    System.out.println("Digite um novo autor, ou deixe em branco: ");
                    String novoAutorRevista = reader.nextLine();
                    System.out.println("Digite um novo gênero: "
                            + "\n0 - Manter o atual"
                            + "\n1 - Ação"
                            + "\n2 - Romance"
                            + "\n3 - Ficção"
                            + "\n4 - História"
                            + "\n5 - Técnico");
                    int novoGeneroRevista = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite um novo número de páginas ou deixe em branco: ");
                    int novoNumeroPaginasRevista = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite uma nova editora ou deixe em branco: ");
                    String novaMateria = reader.nextLine();
                    boolean sucessoEdicaoRevista = biblioteca.editarRevista(
                            codigoEdicaoRevista,
                            novoTituloRevista,
                            novoAutorRevista,
                            novoGeneroRevista,
                            novoNumeroPaginasRevista,
                            novaMateria
                    );
                    if (sucessoEdicaoRevista) {
                        System.out.println("Edição realizada com sucesso!!!");
                    } else {
                        System.out.println("Não foi possível alterar, livro não encontrado!");
                    }
                    break;
                case 9:
                    System.out.println("-------Editar Disco------");
                    System.out.println("Digite o código do livro: ");
                    int codigoEdicaoDisco = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite o novo titulo, ou deixe em branco: ");
                    String novoTituloDisco = reader.nextLine();
                    System.out.println("Digite um novo autor, ou deixe em branco: ");
                    String novoAutorDisco = reader.nextLine();
                    System.out.println("Digite um novo gênero: "
                            + "\n0 - Manter o atual"
                            + "\n1 - Ação"
                            + "\n2 - Romance"
                            + "\n3 - Ficção"
                            + "\n4 - História"
                            + "\n5 - Técnico");
                    int novoGeneroDisco = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite um novo número de páginas ou deixe em branco: ");
                    int novaDuracao = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Digite uma nova editora ou deixe em branco: ");
                    String novoFormato = reader.nextLine();
                    boolean sucessoEdicaoDisco = biblioteca.editarDisco(
                            codigoEdicaoDisco,
                            novoTituloDisco,
                            novoAutorDisco,
                            novoGeneroDisco,
                            novaDuracao,
                            novoFormato
                    );
                    if (sucessoEdicaoDisco) {
                        System.out.println("Edição realizada com sucesso!!!");
                    } else {
                        System.out.println("Não foi possível alterar, livro não encontrado!");
                    }
                    break;
                case 10: 
                    System.out.println("------Listagem completa do acervo------");
                    biblioteca.listarAcervo();
                    break;
                case 11:
                    System.out.println("-Listagem do acervo-");
                    biblioteca.listarAcervo();
                    System.out.print("Digite o código do item: ");
                    long cod = reader.nextInt();
                    reader.nextLine();
                    Midia item = biblioteca.pesquisar(cod);
                    System.out.println("---Detalhes de item " + cod);
                    System.out.println(
                            item.getTitulo() 
                            + item.getAutor()
                            + item.getGenero());
                    if(item instanceof Livro) {
                        Livro livro = (Livro) item;
                        System.out.println(livro.getNumPaginas() + " pags.");
                    }
                    if(item instanceof Revista) {
                        Revista revista = (Revista) item;
                        System.out.println(revista.getNumPaginas() + " pags.");
                    }
                    if(item instanceof Disco) {
                        Disco disco = (Disco) item;
                        System.out.println(disco.getDuracao() + " min");
                        System.out.println(disco.getFormato());
                    }
                        System.out.println(item.isDisponivel());
                    break;
                case 12:
                    System.out.println("-------Remover------");
                    System.out.println("Digite o código do livro");
                    int codigoExclusao = reader.nextInt();
                    reader.nextLine();
                    biblioteca.excluir(codigoExclusao);
                    break;
                case 13:
                    System.out.println("Programa encerrado");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
