package entity;

// Ok
public class Produto {
    private static int contadorId = 0;
    
    private Integer id;
    private String nome;
    private Double valor;
    private Integer qtdEstoque;
    public boolean emFalta;

    public Produto(String nome, Double valor, int qtdEstoque) {
        contadorId += 1;
        this.id = contadorId;
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
        this.emFalta = false;
    }

    // Ok
    public Integer getId() {
        return this.id;
    }

    // Ok
    public String getNome() {
        return this.nome;
    }

    // Ok
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Ok
    public Double getValor() {
        return this.valor;
    }

    // Ok
    public Integer getQtdEstoque() {
        return this.qtdEstoque;
    }

    // Ok
    public boolean isEmFalta() {
        return this.emFalta;
    }

    // Ok
    public void atualizarEstoque(int quantidade, int opcao) {
        /* voce passa a quantidade e a opção
         *      1. aumentar:  soma com o estoque todo
         *      2. diminuir: subtrai qtd do estoque
         *      3. atualizar: atualiza tudo
         * */

        int temp = this.qtdEstoque;

        switch (opcao) {
            case 1:
                this.qtdEstoque += quantidade;
                break;
            case 2:
                this.qtdEstoque -= quantidade;
                break;
            case 3:
                this.qtdEstoque = quantidade;
                break;
            default:
                break;
        }
        System.out.printf("O estoque estava com %d %s's\n\n", temp, this.nome);
        System.out.printf("E agora está com %d %s's\n\n", this.qtdEstoque, this.nome);

    }

    // Ok
    public void atualizarValor(double valor, int opcao) {

        double temp = this.valor;
        switch (opcao) {
            case 1:
                this.valor += valor;
                break;
            case 2:
                this.valor -= valor;
                break;
            case 3:
                this.valor = valor;
                break;
            default:
                break;
        }

        System.out.printf("O item estava %.2f R$\n\n", temp);
        System.out.printf("E agora está %.2f R$\n\n", this.valor);
    }

}
