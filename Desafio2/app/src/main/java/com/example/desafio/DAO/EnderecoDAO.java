package com.example.desafio.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.desafio.Model.EnderecoModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private final ConexaoSQLite con;

    public EnderecoDAO(ConexaoSQLite con) {
        this.con = con;
    }

    public long insertEndereco(EnderecoModel endereco){
        SQLiteDatabase db = con.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("id_usuario" ,endereco.getId_user());
            values.put("bairro",endereco.getBairro());
            values.put("cep",endereco.getCep());
            values.put("complemento",endereco.getComplemento());
            values.put("numero",endereco.getNumero());
            values.put("uf", endereco.getUf());
            values.put("logradouro",endereco.getLogradouro());

            return db.insert("endereco",null,values);

        }catch (Exception e){

        }
        return 0;
    }

    public List<EnderecoModel> listEndereco(){
        SQLiteDatabase db = con.getWritableDatabase();
        List<EnderecoModel> enderecos = new ArrayList<>();
        Cursor cursor = db.query("endereco",new String[]{"id","id_usuario","bairro","cep","complemento","numero","uf","logradouro" },//Classe Curso é como se fosse um ponteiro que aponta para uma tabela entao estamos retornando linhas do db e o cursor esta apontando para elas
                null,null,null,null,null);

        while(cursor.moveToNext()){
            EnderecoModel ps = new EnderecoModel();
            ps.setId(cursor.getInt(0));
            ps.setId_user(cursor.getInt(1));
            ps.setBairro(cursor.getString(2));
            ps.setCep(cursor.getString(3));
            ps.setComplemento(cursor.getString(4));
            ps.setNumero(cursor.getInt(5));
            ps.setUf(cursor.getString(6));
            ps.setLogradouro(cursor.getString(7));
            enderecos.add(ps);
        }
        return enderecos;
    }

    public EnderecoModel getEnderecoById(long id){
        SQLiteDatabase db = con.getWritableDatabase();
        EnderecoModel ps = new EnderecoModel();
        Cursor cursor = db.rawQuery("SELECT * FROM endereco WHERE id = " + id, null);

        while(cursor.moveToNext()){
            ps.setId(cursor.getInt(0));
            ps.setId_user(cursor.getInt(1));
            ps.setBairro(cursor.getString(2));
            ps.setCep(cursor.getString(3));
            ps.setComplemento(cursor.getString(4));
            ps.setNumero(cursor.getInt(5));
            ps.setUf(cursor.getString(6));
            ps.setLogradouro(cursor.getString(7));

        }
        return ps;
    }
}
