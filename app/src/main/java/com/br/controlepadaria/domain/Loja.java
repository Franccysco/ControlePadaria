package com.br.controlepadaria.domain;

import com.orm.SugarRecord;

public class Loja extends SugarRecord {

    private String nome;
    private String endereco;
    private int numero;
    private String telefone;


    public Loja() {
    }

    public Loja(String nome, String endereco, int numero, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.numero = numero;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String gettelefone() {
        return telefone;
    }

    public void settelefone(String telefone) {
        this.telefone = telefone;
    }



    @Override
    public String toString() {
        return this.getNome();
    }
}
