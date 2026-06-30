package entity;

public abstract class Pessoa {
    protected Integer id;
    protected String nome;

    public Pessoa(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() { return id; }
    public String getNome() { return nome; }
}