package bibliotecav1;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 *
 * @author Guilherme Gules Moreira
 */
public class Principal {
    
    private static final Scanner LEITOR = new Scanner(System.in, StandardCharsets.ISO_8859_1.name());
    private static Biblioteca biblioteca; 
    
    public static void main(String[] args) {
        biblioteca = new Biblioteca(200);
        String usuario;
        String senha;
        int option = 0;
        do {
            System.out.println("Digite o seu usuário: ");
            usuario = LEITOR.next();
            System.out.println("Digite a sua senha: ");
            senha = LEITOR.next();
            if(!AutenticacaoUsuario.estaLiberado(usuario, senha)) {
                System.out.println("Usuário incorreto, digite novamente");
            }
        } while(!AutenticacaoUsuario.estaLiberado(usuario, senha));
        while (option != 7) {
            System.out.println("Bem-vindo a Biblioteca virtual QI");
            System.out.println("Escolha a sua opção: "
                    + "\n1-Cadastrar livro"
                    + "\n2-Pesquisar livro"
                    + "\n3-Emprestar livro"
                    + "\n4-Devolver livro"
                    + "\n5-Editar Livro"
                    + "\n6-Remover livro"
                    + "\n7-Sair");
            option = LEITOR.nextInt();
            LEITOR.nextLine();
            switch(option) {
                case 1:
                    System.out.println("-------Cadastro-------");
                    System.out.println("Digite o titulo do livro: ");
                    String titulo = LEITOR.nextLine();
                    System.out.println("Digite o autor do livro: ");
                    String autor = LEITOR.nextLine();
                    System.out.println("Escolha um gênero do livro(númeral): "
                            + "\n1-Ação"
                            + "\n2-Romance"
                            + "\n3-Ficção"
                            + "\n4-História"
                            + "\n5-Técnico");
                    int genero = LEITOR.nextInt();
                    LEITOR.nextLine();
                    biblioteca.cadasrarLivro(titulo, autor, genero);
                    break;
                case 2:
                    System.out.println("-------Pesquisa-------");
                    System.out.println("Escolha o tipo de pesquisa"
                            + "\n1-Pesquisar por titulo"
                            + "\n2-Pesquisar por código");
                    int opcao = LEITOR.nextInt();
                    LEITOR.nextLine();
                    switch (opcao) {
                        case 1:
                            System.out.println("Digite o titulo do livro");
                            String pesquisaTitulo = LEITOR.nextLine();
                            Livro[] livros = biblioteca.pesquisar(pesquisaTitulo);
                            if(livros == null) System.out.println("Livro não encontrado");
                            for (int i = 0; i < livros.length; i++) {
                                if(livros[i] != null) {
                                    System.out.println("Livros pesquisados: " 
                                        + "\nID: " + livros[i].getCodigo() 
                                        + "\nTitulo: " + livros[i].getTitulo()
                                        + "\nAutor: " + livros[i].getAutor()
                                        + "\nEstá disponivel: " + livros[i].isDisponivel());
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Digite o código do livro");
                            long codigo = LEITOR.nextInt();
                            LEITOR.nextLine();
                            Livro livro = biblioteca.pesquisar(codigo);
                            if(livro == null) System.out.println("Código inválido");
                            System.out.println("Livros pesquisados: \n"
                                    + "ID: " + livro.getCodigo()
                                    + "\nTitulo " + livro.getTitulo()
                                    + "\nAutor: " + livro.getAutor()
                                    + "\nEstá disponível: " + livro.isDisponivel());
                            break;
                        default:
                            System.out.println("Opção inválida");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("-------Empréstimo-------");
                    System.out.println("Digite o código do livro para empréstimo: ");
                    int codigoEmprestimo = LEITOR.nextInt();
                    LEITOR.nextLine();
                    boolean sucessoEmprestimo = biblioteca.emprestarLivro(codigoEmprestimo);
                    if(sucessoEmprestimo) System.out.println("Livro emprestado com sucesso!");
                    else System.out.println("Não foi possível emprestar o livro!!!");
                    break;
                case 4: 
                    System.out.println("-------Devolução-------");
                    System.out.println("Digite o código do livro para devoluçao: ");
                    int codigoDevolucao = LEITOR.nextInt();
                    LEITOR.nextLine();
                    boolean sucessoDevolucao = biblioteca.devolverLivro(codigoDevolucao);
                    if(sucessoDevolucao) System.out.println("Devolução realizada com sucesso!");
                    else System.out.println("Não foi possível devolver o livro!!!");   
                    break;
                case 5:
                    System.out.println("-------Editar------");
                    System.out.println("Digite o código do livro: ");
                    int codigoEdicao = LEITOR.nextInt();
                    LEITOR.nextLine();
                    System.out.println("Digite o novo titulo, ou deixe em branco: ");
                    String novoTitulo = LEITOR.nextLine();
                    System.out.println("Digite um novo autor, ou deixe em branco: ");
                    String novoAutor = LEITOR.nextLine();
                    System.out.println("Digite um novo gênero: "
                            + "\n0 - Manter o atual"
                            + "\n1 - Ação"
                            + "\n2 - Ficção"
                            + "\n3 - História"
                            + "\n4 - Técnico");
                    int novoGenero = LEITOR.nextInt();
                    boolean sucessoEdicao = biblioteca.editarLivro(codigoEdicao, novoTitulo, novoAutor, novoGenero);
                    if(sucessoEdicao) System.out.println("Edição realizada com sucesso!!!");
                    else System.out.println("Não foi possível alterar, livro não encontrado!");
                    break;
                case 6:
                    System.out.println("-------Remover------");
                    System.out.println("Digite o código do livro");
                    int codigoExclusao = LEITOR.nextInt();
                    LEITOR.nextLine();
                    boolean sucessoExclusao  = biblioteca.excluirLivro(codigoExclusao);
                    if(sucessoExclusao) System.out.println("Livro excluído com sucesso!");
                    else System.out.println("Não foi possível excluír, livro não encontrado");
                    break;
                case 7:
                    System.out.println("Programa encerrado");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } 
        
    }
}
