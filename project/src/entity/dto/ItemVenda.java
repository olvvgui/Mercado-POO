package entity.dto;

public class ItemVenda {
    private int codigo;
    private int quantidade;
    private Double precoUnitario;


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getPrecoTotal() {
        return quantidade * precoUnitario;
    }


}
