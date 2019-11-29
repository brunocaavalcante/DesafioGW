package com.example.desafio.Model;

import java.util.Date;

public class PessoaJuridicaModel extends PessoaModel {

    private Date DataCriacao;
    private String cnpj;


    public Date getDataCriacao() {
        return DataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        DataCriacao = dataCriacao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

}
