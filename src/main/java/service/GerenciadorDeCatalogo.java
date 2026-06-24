package service;

import entity.Produto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeCatalogo {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "senha123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Ok
    public Produto cadastrarProduto(String nome, Double valor, int quantidade) {
        String sql = "INSERT INTO produtos (nome, valor, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nome);
            stmt.setDouble(2, valor);
            stmt.setInt(3, quantidade);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            Integer idGerado = null;
            if (rs.next()) {
                idGerado = rs.getInt(1);
            }

            return new Produto(idGerado, nome, valor, quantidade);

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto: " + e.getMessage());
            return null;
        }
    }

    // Ok
    public void editarProduto(Integer id, String nome, Double valor, int quantidade) {
        String sql = "UPDATE produtos SET nome = ?, valor = ?, quantidade = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setDouble(2, valor);
            stmt.setInt(3, quantidade);
            stmt.setInt(4, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                System.out.println("Produto com ID " + id + " não encontrado.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    // Ok
    public Produto excluirProduto(Integer id) {
        Produto produtoExcluido = buscarProdutoPorId(id);

        if (produtoExcluido != null) {
            String sql = "DELETE FROM produtos WHERE id = ?";

            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                stmt.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Erro ao excluir produto: " + e.getMessage());
                return null;
            }
        }

        return produtoExcluido;
    }

    // Ok
    public List<Produto> pesquisarProduto() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, valor, quantidade FROM produtos";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                Double valor = rs.getDouble("valor");
                int quantidade = rs.getInt("quantidade");

                Produto produto = new Produto(id, nome, valor, quantidade);
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar produto: " + e.getMessage());
        }

        return produtos;
    }

    // Ok
    private Produto buscarProdutoPorId(Integer id) {
        String sql = "SELECT id, nome, valor, quantidade FROM produtos WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    Double valor = rs.getDouble("valor");
                    int quantidade = rs.getInt("quantidade");
                    return new Produto(id, nome, valor, quantidade);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto por ID: " + e.getMessage());
        }
        return null;
    }
}