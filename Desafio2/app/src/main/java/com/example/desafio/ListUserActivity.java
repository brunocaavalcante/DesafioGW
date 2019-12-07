package com.example.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.desafio.Controller.EnderecoController;
import com.example.desafio.Controller.PessoaController;
import com.example.desafio.Model.EnderecoModel;
import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.Model.PessoaJuridicaModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

import java.util.List;

public class ListUserActivity extends AppCompatActivity {

    private ListView listView;
    private PessoaController controller;
    private List<PessoaFisicaModel> pessoaFisicaModelList;
    private List<PessoaJuridicaModel>pessoaJuridicaModelList;
    private EnderecoController enderecoController;
    private List<EnderecoModel>enderecoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        ConexaoSQLite con = ConexaoSQLite.getInstancia(this);
        controller = new PessoaController(con);
        enderecoController = new EnderecoController(con);

        listView = findViewById(R.id.lvListaUsuarios);
        pessoaFisicaModelList = controller.pessoaFisicaModelList();

        setEndereco();

        ArrayAdapter<PessoaFisicaModel> adapterPessoaFisica = new ArrayAdapter<PessoaFisicaModel>(this,android.R.layout.simple_list_item_1,pessoaFisicaModelList);

        listView.setAdapter(adapterPessoaFisica);
    }

    private void setEndereco(){
        for (PessoaFisicaModel pessoaFisicaModel: pessoaFisicaModelList){
            pessoaFisicaModel.setEndereco(enderecoController.getEnderecoById(pessoaFisicaModel.getId()));
        }
    }
}
