package com.br.controlepadaria.domain;

import com.orm.SugarRecord;

public class ItensPedido extends SugarRecord {

    private int quantidade;
    private Double precoVenda;
    private Pedido pedido;
    private Produto produto;

    public ItensPedido() {
    }

    public ItensPedido(int quantidade, Double precoVenda, Pedido pedido, Produto produto) {
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
        this.pedido = pedido;
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
