package com.br.controlepadaria.domain;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Pedido extends SugarRecord implements Serializable {

    private String data;
    private Double valor;
    private Loja loja;


    public Pedido() {
    }

    public Pedido(String data, Double valor, Loja loja) {
        this.data = data;
        this.valor = valor;
        this.loja = loja;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
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



    @Override
    public String toString() {
        return this.getData();
    }
}
