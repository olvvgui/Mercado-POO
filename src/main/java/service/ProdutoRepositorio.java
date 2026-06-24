package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import entity.Produto;

public class ProdutoRepositorio {

    private Connection conn;

    public ProdutoRepositorio(){
        // Busca a variável de ambiente 'DB_HOST'. Se for nula, usa 'localhost' (para testes na IDE)
        String dbHost = System.getenv("DB_HOST");
        if (dbHost == null) {
            dbHost = "localhost";
        }

        String url = "jdbc:postgresql://" + dbHost + ":5432/postgres";
        String user = "postgres";
        String password = "senha123";

        System.out.println("Tentando conectar ao banco em: " + url);

        // Tenta estabelecer a conexão
        try (
                Connection connection = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Sucesso! Conectado ao PostgreSQL via Docker Compose.");
                this.conn = connection;
            }
        } catch (
                SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }
    }

    public void cadastrarProduto(Produto novoProduto){
        //this.conn.prepareStatement(

        //)
    }
}
