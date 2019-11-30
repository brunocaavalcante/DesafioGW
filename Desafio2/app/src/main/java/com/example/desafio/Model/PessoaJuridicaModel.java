package com.example.desafio.Model;

import java.util.Date;

public class PessoaJuridicaModel extends PessoaModel {

    private String DataCriacao;
    private String cnpj;


    public String getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
