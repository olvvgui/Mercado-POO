package exception;

public class EstoqueInsuficienteException extends Exception {
    public EstoqueInsuficienteException(String nomeProduto, Integer qtdEstoque) {
        super("Erro na venda: Produto " + nomeProduto + " possui apenas " + qtdEstoque + " unidades em estoque.");
    }
}