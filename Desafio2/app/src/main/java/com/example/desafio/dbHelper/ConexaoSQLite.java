package com.example.desafio.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.desafio.Model.PessoaModel;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final  String NOME_DB = "desafio";

    public ConexaoSQLite(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    public static ConexaoSQLite getInstancia(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        PessoaModel user = new PessoaModel();
        user.getNome();

        String tableUsuario = "CREATE TABLE IF NOT EXISTS usuario(" +
                "id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL, " +
                "cpf TEXT," +
                "cnpj TEXT," +
                "data_nascimento TEXT," +
                "data_criacao TEXT)";

        String tableEndereco= "CREATE TABLE IF NOT EXISTS endereco(" +
                "id INTEGER PRIMARY KEY," +
                "id_usuario INTEGER," +
                "bairro TEXT," +
                "cep TEXT," +
                "complemento TEXT, " +
                "numero INTEGER," +
                "tipo TEXT," +
                "uf TEXT, " +
                "logradouro TEXT )";

        db.execSQL(tableUsuario);
        db.execSQL(tableEndereco);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
