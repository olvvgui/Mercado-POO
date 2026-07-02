package entity.dto;

public class ProdutoDTO {
    private Integer id;
    private String nome;
    private Double valor;
    private Integer quantidade;

    public ProdutoDTO(Integer id, String nome, Double valor,  Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
    }


    public Integer getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Double getValor() {
        return valor;
    }
    public Integer getQuantidade() {
        return quantidade;
    }    

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }



    
}
