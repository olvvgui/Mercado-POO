import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Ok
public class GerenciadorUsuario {
    private final List<Usuario> usuariosRepositorio;

    // Ok
    public GerenciadorUsuario() {
        this.usuariosRepositorio = new ArrayList<>();
    }

    // Ok
    public void criarUsuario(String nome, String email, String senha, PerfilUsuario perfil) {
        Usuario usr = new Usuario(nome, email, senha, perfil);
        this.usuariosRepositorio.add(usr);
    }

    // Ok
    public Usuario buscarUsuario(String email) {
        return this.usuariosRepositorio.stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    // Ok
    public boolean desativarUsuario(int id) {
        Optional<Usuario> usuarioEncontrado = usuariosRepositorio.stream()
                .filter(usuario -> usuario.getId().equals(id))
                .findFirst();

        if (usuarioEncontrado.isPresent()) {
            usuarioEncontrado.get().desativar();
            return true;
        }

        return false;
    }
}