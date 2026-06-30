package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.PerfilUsuario;
import entity.Usuario;

public class UsuarioRepositorio implements RepositorioInterface<Usuario> {

    private final Connection  conn;

    public UsuarioRepositorio(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void incluir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nome, email, senha, perfil, ativo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenhaCriptografada());
            stmt.setString(4, usuario.getPerfil().name());
            stmt.setBoolean(5, usuario.isAtivo());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void alterar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void excluir(Integer id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public Usuario consultar(Integer id) {
        String sql = "SELECT id, nome, email, senha, perfil FROM usuarios WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer usuarioId = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String senha = rs.getString("senha");
                    
                    String perfilString = rs.getString("perfil_usuario");
                    PerfilUsuario perfil = PerfilUsuario.GERENTE;
                    
                    if (perfilString != null && !perfilString.trim().isEmpty()) {
                        perfil = PerfilUsuario.valueOf(perfilString.trim().toUpperCase()); 
                    }

                    Usuario usuarioEncon = new Usuario(nome, email, senha, perfil);
                    usuarioEncon.setId(usuarioId);

                    return usuarioEncon;
                }
            }
        } catch (SQLException e) {
            System.err.println("Falha ao buscar usuário: " + e.getMessage());
        }
        
        return null;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id, nome, email, senha, perfil FROM usuarios";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Integer usuarioId = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                    
                String perfilString = rs.getString("perfil_usuario");
                PerfilUsuario perfil = PerfilUsuario.GERENTE;
                    
                if (perfilString != null && !perfilString.trim().isEmpty()) {
                    perfil = PerfilUsuario.valueOf(perfilString.trim().toUpperCase()); 
                }

                Usuario usuarioEncon = new Usuario(nome, email, senha, perfil);
                usuarioEncon.setId(usuarioId);

                lista.add(usuarioEncon);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return lista;
    }
}