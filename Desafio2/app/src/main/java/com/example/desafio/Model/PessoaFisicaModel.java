package com.example.desafio.Model;

import java.util.Date;

public class PessoaFisicaModel extends  PessoaModel {

    private String dataNascimento;
    private String cpf;

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


}
