package entity.mapper;

import entity.Produto;
import entity.dto.ItemVenda;

public class ProdutoMapper {

    public static ItemVenda toItemVenda(Produto produto, int quantidade) {
        ItemVenda itemVenda = new ItemVenda(produto.getId(), produto, quantidade, produto.getValor());
        itemVenda.setCodigo(produto.getId());
        itemVenda.setPrecoUnitario(produto.getValor());
        itemVenda.setQuantidade(quantidade);

        return itemVenda;
    }

}
