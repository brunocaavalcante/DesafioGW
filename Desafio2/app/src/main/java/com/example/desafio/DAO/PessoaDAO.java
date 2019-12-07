package com.example.desafio.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.Model.PessoaJuridicaModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {
    private final ConexaoSQLite con;

    public PessoaDAO(ConexaoSQLite con) {
        this.con = con;
    }

    public long insertPessoaFisica(PessoaFisicaModel pessoaFisicaModel){
        SQLiteDatabase db = con.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("cpf",pessoaFisicaModel.getCpf());
            values.put("nome",pessoaFisicaModel.getNome());
            values.put("data",pessoaFisicaModel.getDataNascimento().toString());

           return db.insert("usuario",null,values);


        }catch (Exception e){

        }
        return 0;
    }

    public long insertPessoaJuridica(PessoaJuridicaModel pessoaJuridicaModel){
          SQLiteDatabase db = con.getWritableDatabase();

                try {
                    ContentValues values = new ContentValues();
                    values.put("cnpj",pessoaJuridicaModel.getCnpj());
                    values.put("nome",pessoaJuridicaModel.getNome());
                    values.put("data",pessoaJuridicaModel.getDataCriacao());

                   return db.insert("usuario",null,values);

                }catch (Exception e){

                }
                return 0;
    }

    public List<PessoaFisicaModel> listPessoaFisica(){
        SQLiteDatabase db = con.getWritableDatabase();
        List<PessoaFisicaModel> pessoas = new ArrayList<>();
        Cursor cursor = db.query("usuario",new String[]{"id","nome","cpf","data"},//Classe Curso é como se fosse um ponteiro que aponta para uma tabela entao estamos retornando linhas do db e o cursor esta apontando para elas
                null,null,null,null,null);

        while(cursor.moveToNext()){
            PessoaFisicaModel ps = new PessoaFisicaModel();
            ps.setId(cursor.getInt(0));
            ps.setNome(cursor.getString(1));
            ps.setCpf(cursor.getString(2));
            ps.setDataNascimento(cursor.getString(3));
            pessoas.add(ps);
        }
        return pessoas;
    }


}
