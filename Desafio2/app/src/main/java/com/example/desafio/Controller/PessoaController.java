package com.example.desafio.Controller;

import com.example.desafio.DAO.PessoaDAO;
import com.example.desafio.Model.Endereco;
import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.Model.PessoaJuridicaModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

public class PessoaController {

    private final PessoaDAO pessoaDAO;

    public PessoaController(ConexaoSQLite conexaoSQLite){
        pessoaDAO = new PessoaDAO(conexaoSQLite);
    }

    public long inserirPessoaFisica(PessoaFisicaModel pessoa, Endereco endereco){
        return pessoaDAO.insertPessoaFisica(pessoa,endereco);
    }

    public long inserirPessoaJuridica(PessoaJuridicaModel pessoaJuridicaModel, Endereco endereco){
        return pessoaDAO.insertPessoaJuridica(pessoaJuridicaModel,endereco);
    }
}
