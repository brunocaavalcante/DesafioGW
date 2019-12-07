package com.example.desafio.Controller;

import com.example.desafio.DAO.PessoaDAO;
import com.example.desafio.Model.EnderecoModel;
import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.Model.PessoaJuridicaModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

import java.util.List;

public class PessoaController {

    private final PessoaDAO pessoaDAO;

    public PessoaController(ConexaoSQLite conexaoSQLite){
        pessoaDAO = new PessoaDAO(conexaoSQLite);
    }

    public long inserirPessoaFisica(PessoaFisicaModel pessoa){
        return pessoaDAO.insertPessoaFisica(pessoa);
    }

    public long inserirPessoaJuridica(PessoaJuridicaModel pessoaJuridicaModel){
        return pessoaDAO.insertPessoaJuridica(pessoaJuridicaModel);
    }

    public List<PessoaFisicaModel> pessoaFisicaModelList(){
        return pessoaDAO.listPessoaFisica();
    }
}
