package entity;

import entity.dto.ItemVenda;
import entity.mapper.ProdutoMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private static int contadorId = 0;

    private int id;
    private LocalDateTime data;
    private double valorTotal;
    private String descricao;
    private List<ItemVenda> itensVenda = new ArrayList<>();
    private StatusVenda status;

    public Venda() {
        contadorId += 1;
        id = contadorId;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        itensVenda.add(ProdutoMapper.toItemVenda(produto,quantidade));
    }

    public void fecharVenda() {
        calcularValorTotal();
        status = StatusVenda.FINALIZADA;
        data = LocalDateTime.now();
    }

    public double calcularValorTotal() {
        this.valorTotal = 0.0;
        itensVenda.forEach(i-> valorTotal += i.getPrecoTotal());
        return valorTotal;
    }


}
