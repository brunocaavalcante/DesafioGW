package com.example.desafio.Controller;

import com.example.desafio.DAO.EnderecoDAO;
import com.example.desafio.Model.EnderecoModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

import java.util.List;

public class EnderecoController {
    private final EnderecoDAO enderecoDAO;

    public EnderecoController(ConexaoSQLite conexaoSQLite){
        enderecoDAO = new EnderecoDAO(conexaoSQLite);
    }

    public long inserirEndereco(EnderecoModel enderecoModel){
        return enderecoDAO.insertEndereco(enderecoModel);
    }

    public List<EnderecoModel> listarEndereco(){
        return enderecoDAO.listEndereco();
    }

    public  EnderecoModel getEnderecoById(long id){
        return enderecoDAO.getEnderecoById(id);
    }
}
