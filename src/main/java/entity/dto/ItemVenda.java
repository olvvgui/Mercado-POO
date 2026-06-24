package entity.dto;

import entity.Produto;

// Ok
public class ItemVenda {
    private int codigo;
    private int quantidade;
    private Double precoUnitario;
    private Produto produto;

    public ItemVenda(Integer codigo, Produto produto, Integer quantidade, Double precoUnitario) {
        this.codigo = codigo;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    // Ok
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Ok
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Ok
    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    // Ok
    public double getPrecoTotal() {
        return quantidade * precoUnitario;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public Double getPrecoUnitario() {
        return this.precoUnitario;
    }

    public Produto getProduto() {
        return this.produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
