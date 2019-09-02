package com.br.controlepadaria.domain;

import com.orm.SugarRecord;

public class Pedido extends SugarRecord {

    private Long data;
    private Double valor;
    private Loja loja;


    public Pedido() {
    }

    public Pedido(Long data, Double valor, Loja loja) {
        this.data = data;
        this.valor = valor;
        this.loja = loja;
    }

    public Long getData() {
        return data;
    }

    public void setData(Long data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Loja getLoja() {
        return loja;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
}
