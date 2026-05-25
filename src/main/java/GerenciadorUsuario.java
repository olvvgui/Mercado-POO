import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GerenciadorUsuario {
    private List<Usuario> usuariosRepositorio;

    public GerenciadorUsuario() {
        this.usuariosRepositorio = new ArrayList<>();
    }

    public void criarUsuario(String nome, String email, String senha, PerfilUsuario perfil) {
        Usuario usr = new Usuario(nome, email, senha, perfil);
        this.usuariosRepositorio.add(usr);
    }

    public Usuario buscarUsuario(String email) {
        return this.usuariosRepositorio.stream()
                .filter(usuario -> usuario.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

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