package com.example.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.desafio.Controller.PessoaController;
import com.example.desafio.Model.Endereco;
import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.dbHelper.ConexaoSQLite;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private String pessoa;
    private Spinner spnTipoEndereco;
    private List<Endereco> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ConexaoSQLite con = ConexaoSQLite.getInstancia(this);

        Bundle extras = getIntent().getExtras();
        pessoa = extras!= null ? extras.getString("Pessoa"):"";
        PessoaFisicaModel pessoaFisicaModel = new PessoaFisicaModel();
        PessoaController pessoaController = new PessoaController(con);
        pessoaFisicaModel.setId(14);
        pessoaFisicaModel.setNome("Teste");
        pessoaFisicaModel.setCpf("015455005");
        pessoaFisicaModel.setDataNascimento("1996-07-26");
        long result = pessoaController.inserirPessoaFisica(pessoaFisicaModel);
        if(result > 0){
            System.out.println("resultado "+ result);
        }
    }
}
