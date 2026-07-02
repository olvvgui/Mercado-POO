package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entity.Produto;
import exception.EstoqueInsuficienteException;

public class ProdutoTest {

    private Produto produto;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Notebook", 3500.00, 10);
    }

    @Test
    @DisplayName("Deve debitar o estoque corretamente quando houver quantidade suficiente")
    public void testDebitarEstoqueComSucesso() throws EstoqueInsuficienteException {
        // Debitar 4 unidades de um estoque de 10
        produto.atualizarEstoque(4, 2);

        // Verificação: O estoque deve cair para 6 e o produto NÃO pode estar em falta
        assertEquals(6, produto.getQtdEstoque());
        assertFalse(produto.isEmFalta());
    }

    @Test
    @DisplayName("Deve atualizar o status estaEmFalta para true quando o estoque zerar")
    public void testDebitarEstoqueAteZerar() throws EstoqueInsuficienteException {
        // Debitar todas as 10 unidades
        produto.atualizarEstoque(10, 2);

        // Verificação: O estoque deve ser 0 e o status de falta deve ser verdadeiro
        assertEquals(0, produto.getQtdEstoque());
        assertTrue(produto.isEmFalta());
    }

    @Test
    @DisplayName("Deve lançar EstoqueInsuficienteException ao tentar debitar mais do que o disponível")
    public void testDebitarEstoqueInsuficienteLancaExcecao() {
        // Tentar debitar 15 de um estoque de 10 deve gerar Erro
        Exception exception = assertThrows(EstoqueInsuficienteException.class, () -> {
        produto.atualizarEstoque(15, 2);
        });

        // Confirma se a mensagem de erro está correta (opcional, mas recomendado)
        String mensagemEsperada = "Erro: O produto não possui unidades suficientes em estoque.";
        assertEquals(mensagemEsperada, exception.getMessage());
        
        // Garante que o estoque não foi alterado indevidamente
        assertEquals(10, produto.getQtdEstoque());
    }
}