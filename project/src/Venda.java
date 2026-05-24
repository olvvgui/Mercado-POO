
import java.time.LocalDateTime;

public class Venda {
    private static int contadorId = 0;

    private int id;
    private LocalDateTime data;
    private Double valorTotal;
    private String descricao;

    public Venda() {
        contadorId += 1;
        id = contadorId;
        data = LocalDateTime.now();
    }

    void adicionarItem(Produto produto, int quantidade) {

    }

    void fecharVenda() {

    }

    Double calcularValorTotal(Double valor, int quantidade) {
        return valor * quantidade;
    }


}
