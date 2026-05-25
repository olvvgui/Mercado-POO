import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
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
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Sucesso! Conectado ao PostgreSQL via Docker Compose.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados:");
            e.printStackTrace();
        }
    }
}