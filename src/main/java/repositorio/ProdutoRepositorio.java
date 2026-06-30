package repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Produto;

public class ProdutoRepositorio {

    private Connection conn;

    public ProdutoRepositorio() {
        // Busca a variável de ambiente 'DB_HOST'. Se for nula, usa 'localhost' (para testes na IDE)
        String dbHost = System.getenv("DB_HOST");
        if (dbHost == null) {
            dbHost = "localhost";
        }

        String url = "jdbc:postgresql://" + dbHost + ":5432/postgres";
        String user = "postgres";
        String password = "senha123";

        System.out.println("Tentando conectar ao banco em: " + url);

        try {
            this.conn = DriverManager.getConnection(url, user, password);
            if (this.conn != null) {
                System.out.println("Sucesso! Conectado ao PostgreSQL via Docker Compose.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace(System.err);
        }
    }

    public void cadastrarProduto(Produto novoProduto) {
        if (this.conn == null) {
            System.err.println("Sem conexão com o banco de dados.");
            return;
        }

        String sql = "INSERT INTO produtos (nome, valor, quantidade_estoque, esta_em_falta) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, novoProduto.getNome());
            stmt.setDouble(2, novoProduto.getValor());
            stmt.setInt(3, novoProduto.getQtdEstoque());
            stmt.setBoolean(4, novoProduto.isEmFalta());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Produto cadastrado com sucesso no PostgreSQL!");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar o produto no banco de dados:");
            e.printStackTrace(System.err);
        }
    }
}