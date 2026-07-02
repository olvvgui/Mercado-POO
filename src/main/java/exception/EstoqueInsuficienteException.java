package exception;

public class EstoqueInsuficienteException extends Exception {
    public EstoqueInsuficienteException(String nomeProduto, Integer qtdEstoque) {
        super("Erro: O produto não possui unidades suficientes em estoque.");
    }
}