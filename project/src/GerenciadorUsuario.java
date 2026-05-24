import java.util.List;

public class GerenciadorUsuario {
    private List<Usuario> usuariosRepositorio;

    public Usuario criarUsuario(String nome, String email, String senha, PerfilUsuario perfil) {
        Usuario usr = new Usuario(nome, email, senha, perfil);
        return usr;
    }

    public Usuario buscarUsuario(String email) {

    }

    public boolean desativarUsuario(int id) {
        return false;
    }

}
