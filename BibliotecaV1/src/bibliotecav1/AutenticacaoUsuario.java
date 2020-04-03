package bibliotecav1;

/**
 *
 * @author Guilherme Gules Moreira
 */
public class AutenticacaoUsuario {
    private static final String USUARIO_ADMIN = "admin";
    private static final String SENHA_ADMIN = "admin";
    
    public static boolean estaLiberado(String usuario, String senha) {
        if(usuario.equals(USUARIO_ADMIN) && senha.equals(SENHA_ADMIN)) {
            return true;
        }
        return false;
    }
}
