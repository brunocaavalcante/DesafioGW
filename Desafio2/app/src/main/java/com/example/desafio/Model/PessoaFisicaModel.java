package com.example.desafio.Model;


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

    @Override
    public String toString() {
        return

                "\nID: "+getId()+"\n"+
                "Nome: " + getNome() + '\n' +
                "Data Nascimento: " + dataNascimento + '\n' +
                "CPF:" + cpf + '\n'+
                "CEP: "+getEndereco().getCep()+"\n"+
                "Bairro: "+getEndereco().getBairro() +'\n'+
                "Lougradouro: "+getEndereco().getLogradouro()+'\n';
    }
}
