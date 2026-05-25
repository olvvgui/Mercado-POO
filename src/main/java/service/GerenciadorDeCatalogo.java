package service;

import entity.Produto;

import java.util.List;

public class GerenciadorDeCatalogo {

    public Produto cadastrarProduto(String nome, Double valor, int quantidade) {
        Produto novoProduto = new Produto(nome,valor,quantidade);
        //Acessa o banco de dados
        return novoProduto;
    }

    public void editarProduto() {
        //Acessa o banco de dados
    }

    public Produto excluirProduto(int id) {
        //Acessa o banco de dados
    }

    public List<Produto> pesquisarProduto() {
        //Acessa o banco de dados
    }
}
