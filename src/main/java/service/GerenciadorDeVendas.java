package service;

import entity.Venda;
import entity.dto.ItemVenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class GerenciadorDeVendas {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "senha123";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public Venda iniciarVenda() {
        return new Venda();
    }

    public void processarFechamento(Venda venda) {
        // Finalizar venda
        venda.fecharVenda();

        String sqlVenda = "INSERT INTO vendas (data_hora, valor_total, descricao) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO itens_venda (venda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
        String sqlAtualizarEstoque = "UPDATE produtos SET quantidade = quantidade - ? WHERE id = ?";

        Connection conn = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);

            // Salva a venda
            int vendaIdGerada = 0;
            try (PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

                stmtVenda.setTimestamp(1, Timestamp.valueOf(venda.getData()));
                stmtVenda.setDouble(2, venda.getValorTotal());
                stmtVenda.setString(3, venda.getDescricao());

                stmtVenda.executeUpdate();

                try (ResultSet rs = stmtVenda.getGeneratedKeys()) {
                    if (rs.next()) {
                        vendaIdGerada = rs.getInt(1);
                    } else {
                        throw new SQLException("Falha ao criar venda, ID não obtido.");
                    }
                }
            }

            // Salva os produtos da venda e subtrai do Estoque
            try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
                 PreparedStatement stmtEstoque = conn.prepareStatement(sqlAtualizarEstoque)) {

                for (ItemVenda item : venda.getItensVenda()) {

                    stmtItem.setInt(1, vendaIdGerada);
                    stmtItem.setInt(2, item.getProduto().getId());
                    stmtItem.setInt(3, item.getQuantidade());
                    stmtItem.setDouble(4, item.getPrecoUnitario());
                    stmtItem.addBatch(); // otimizar múltiplos inserts

                    // Update de baixa de estoque
                    stmtEstoque.setInt(1, item.getQuantidade());
                    stmtEstoque.setInt(2, item.getProduto().getId());
                    stmtEstoque.addBatch();
                }

                // Executa inserts e updates de uma vez
                stmtItem.executeBatch();
                stmtEstoque.executeBatch();
            }

            // Confirma a transação no BD se deu certo
            conn.commit();
            System.out.println("Venda " + vendaIdGerada + " fechada e salva com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao processar fechamento da venda: " + e.getMessage());

            // Se der erro, desfaz tudo.
            if (conn != null) {
                try {
                    System.err.println("Desfazendo a transação...");
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Erro em desfazer a transação: " + ex.getMessage());
                }
            }
        } finally {
            // Fecha a conexão
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Volta pro padrão
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}