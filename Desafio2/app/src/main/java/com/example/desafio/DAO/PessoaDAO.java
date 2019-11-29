package com.example.desafio.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.Model.PessoaJuridicaModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

public class PessoaDAO {
    private final ConexaoSQLite con;

    public PessoaDAO(ConexaoSQLite con) {
        this.con = con;
    }

    public long insertPessoaFisica(PessoaFisicaModel pessoaFisicaModel){
        SQLiteDatabase db = con.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("id",pessoaFisicaModel.getId());
            values.put("cpf",pessoaFisicaModel.getCpf());
            values.put("nome",pessoaFisicaModel.getNome());
            values.put("data_nascimento",pessoaFisicaModel.getDataNascimento().toString());

           return db.insert("usuario",null,values);

        }catch (Exception e){

        }
        return 0;
    }

    /*public boolean insertPessoaJuridica(PessoaJuridicaModel pessoaJuridicaModel){

    }*/
}
