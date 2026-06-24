package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.dto.ItemVenda;
import entity.mapper.ProdutoMapper;

// OK
public class Venda {
    private static int contadorId = 0;

    private int id;
    private LocalDateTime data;
    private double valorTotal;
    private String descricao;
    private List<ItemVenda> itensVenda;
    private StatusVenda status;

    public Venda(String descricao) {
        contadorId += 1;
        this.id = contadorId;
        this.itensVenda = new ArrayList<>();
        this.data = LocalDateTime.now();
        this.valorTotal = 0;
        this.descricao = descricao;
    }

    public Venda() {
        contadorId += 1;
        this.id = contadorId;
        this.itensVenda = new ArrayList<>();
        this.data = LocalDateTime.now();
        this.valorTotal = 0;
        this.descricao = "";
    }

    // Ok
    public void adicionarItem(Produto produto, int quantidade) {
        itensVenda.add(ProdutoMapper.toItemVenda(produto,quantidade));
    }

    // Ok
    public void fecharVenda() {
        calcularValorTotal();
        this.status = StatusVenda.FINALIZADA;
        this.data = LocalDateTime.now();
    }

    // Ok
    public double calcularValorTotal() {
        this.valorTotal = 0.0;
        itensVenda.forEach(i-> valorTotal += i.getPrecoTotal());
        return this.valorTotal;
    }

    // Ok
    public static int getContadorId() {
        return contadorId;
    }

    // Ok
    public static void setContadorId(int contadorId) {
        Venda.contadorId = contadorId;
    }

    // Ok
    public int getId() {
        return this.id;
    }

    // Ok
    public void setId(int id) {
        this.id = id;
    }

    // Ok
    public LocalDateTime getData() {
        return this.data;
    }

    // Ok
    public void setData(LocalDateTime data) {
        this.data = data;
    }

    // Ok
    public double getValorTotal() {
        return this.valorTotal;
    }

    // Ok
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    // Ok
    public String getDescricao() {
        return this.descricao;
    }

    // Ok
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Ok
    public List<ItemVenda> getItensVenda() {
        return this.itensVenda;
    }

    // Ok
    public void setItensVenda(List<ItemVenda> itensVenda) {
        this.itensVenda = itensVenda;
    }

    // Ok
    public StatusVenda getStatus() {
        return this.status;
    }

    // Ok
    public void setStatus(StatusVenda status) {
        this.status = status;
    }
}
