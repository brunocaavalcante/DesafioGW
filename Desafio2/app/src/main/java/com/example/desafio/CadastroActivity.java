package com.example.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.desafio.Controller.PessoaController;
import com.example.desafio.Model.Endereco;
import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.Model.PessoaJuridicaModel;
import com.example.desafio.Service.ZipCodeListener;
import com.example.desafio.dbHelper.ConexaoSQLite;

import java.util.List;

public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastrarUser;
    private Button btnCancelarCadUser;
    private String requestPessoa;
    private Spinner spnTipoEndereco;
    private List<Endereco> list;
    private EditText txtNome;
    private EditText txtData;
    private EditText txtCpfCnpj;
    private EditText txtComplemento;
    private EditText txtCep;
    private EditText txtBairro;
    private EditText txtUf;
    private EditText txtLogradouro;
    private EditText txtNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ConexaoSQLite con = ConexaoSQLite.getInstancia(this);

        txtNome = findViewById(R.id.editTextNome);
        txtBairro = findViewById(R.id.editTextBairro);
        txtCpfCnpj = findViewById(R.id.editTextCpfCnpj);
        txtData = findViewById(R.id.editTextData);
        txtCep = findViewById(R.id.editTextCep);
        txtNumero = findViewById(R.id.editTextNumero);
        txtUf = findViewById(R.id.editTextUf);
        txtComplemento = findViewById(R.id.editTextComplemento);
        txtLogradouro = findViewById(R.id.editTextLogradouro);
        spnTipoEndereco = findViewById(R.id.spinnerTipoEndereco);

        Bundle extras = getIntent().getExtras();
        requestPessoa = extras!= null ? extras.getString("Pessoa"):"";
        if(!requestPessoa.isEmpty() && requestPessoa.equals("Pessoa Juridica")){
            txtCpfCnpj.setHint("CNPJ:");
        }else{
            txtCpfCnpj.setHint("CPF:");
        }
        final PessoaFisicaModel pessoaFisicaModel = new PessoaFisicaModel();
        final PessoaJuridicaModel pessoaJuridicaModel = new PessoaJuridicaModel();
        final Endereco endereco = new Endereco();
        final PessoaController pessoaController = new PessoaController(con);

        /*Eventos*/
        txtCep.addTextChangedListener(new ZipCodeListener(this));

        btnCadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()){
                    long result = 0;
                    endereco.setBairro(txtBairro.getText().toString());
                    endereco.setCep(txtCep.getText().toString());
                    endereco.setComplemento(txtComplemento.getText().toString());
                    endereco.setNumero(Integer.parseInt(txtNumero.getText().toString()));
                    endereco.setUf(txtUf.getText().toString());

                    if(requestPessoa.equals("Pessoa Juridica")){
                        pessoaJuridicaModel.setNome(txtNome.getText().toString());
                        pessoaJuridicaModel.setCnpj(txtCpfCnpj.getText().toString());
                        pessoaJuridicaModel.setDataCriacao(txtData.getText().toString());
                        pessoaJuridicaModel.setEndereco(endereco);
                        result = pessoaController.inserirPessoaJuridica(pessoaJuridicaModel,endereco);
                    }else {
                        pessoaFisicaModel.setNome(txtNome.getText().toString());
                        pessoaFisicaModel.setCpf(txtCpfCnpj.getText().toString());
                        pessoaFisicaModel.setDataNascimento(txtData.getText().toString());
                        pessoaFisicaModel.setEndereco(endereco);
                        result = pessoaController.inserirPessoaFisica(pessoaFisicaModel, endereco);
                    }

                    if(result > 0){
                        System.out.println("resultado "+ result);
                    }
                }
            }
        });


    }

    private boolean validaCampos(){
        if(!txtNumero.getText().toString().isEmpty()){
            return false;
        }
        else if(!txtNome.getText().toString().isEmpty()){
            return false;
        }
        else if(!txtBairro.getText().toString().isEmpty()){
            return false;
        }
        else if(!txtCpfCnpj.getText().toString().isEmpty()){
            return false;
        }
        else if(!txtCep.getText().toString().isEmpty()){
            return false;
        }
        else if(!txtData.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public String getUrl(){
        return "viacep.com.br/ws/"+txtCep.getText()+"/json/";
    }

    public void setDataViews(Endereco endereco){
        txtComplemento.setText(endereco.getComplemento());
        txtBairro.setText(endereco.getBairro());
        setSpinner(R.array.states, endereco.getUf());
    }

    public void lockFields(boolean isToLock){
        txtBairro.setEnabled(!isToLock);
        txtUf.setEnabled(!isToLock);
        txtLogradouro.setEnabled(isToLock);
    }

    private void setSpinner(int arrayId, String data ){
        String[] itens = getResources().getStringArray(arrayId);

        for( int i = 0; i < itens.length; i++ ){

            if( itens[i].endsWith( "("+data+")" ) ){
                spnTipoEndereco.setSelection(i);
                return;
            }
        }
        spnTipoEndereco.setSelection( 0 );
    }
}
