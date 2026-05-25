package service;

import entity.Venda;

public class GerenciadorDeVendas {


    public Venda iniciarVenda() {
        return new Venda();
    }

    public void processarFechamento(Venda venda) {
        venda.fecharVenda();
    }


}
