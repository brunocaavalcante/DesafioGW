package com.example.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.desafio.Controller.EnderecoController;
import com.example.desafio.Controller.PessoaController;
import com.example.desafio.Model.EnderecoModel;
import com.example.desafio.Model.PessoaFisicaModel;
import com.example.desafio.Model.PessoaJuridicaModel;
import com.example.desafio.Service.AddressService;
import com.example.desafio.dbHelper.ConexaoSQLite;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.util.concurrent.ExecutionException;

public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastrarUser;
    private Button btnCancelarCadUser;
    private String requestPessoa;
    private EditText txtNome;
    private EditText txtData;
    private EditText txtCpfCnpj;
    private EditText txtComplemento;
    private EditText txtCep;
    private EditText txtBairro;
    private EditText txtUf;
    private EditText txtNumero;
    private EditText txtLogradouro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ConexaoSQLite con = ConexaoSQLite.getInstancia(this);

        txtNome = findViewById(R.id.editTextNome);
        txtBairro = findViewById(R.id.editTextBairro);
        txtCpfCnpj = findViewById(R.id.editTextCpfCnpj);
        txtData = findViewById(R.id.editTextData);
        txtLogradouro = findViewById(R.id.editTextLogradouro);
        txtCep = findViewById(R.id.editTextCep);
        txtNumero = findViewById(R.id.editTextNumero);
        txtUf = findViewById(R.id.editTextUf);
        txtComplemento = findViewById(R.id.editTextComplemento);
        btnCadastrarUser = findViewById(R.id.btnCadastraUser);
        btnCancelarCadUser = findViewById(R.id.btnCancelarCadastroUser);

        createMask();

        Bundle extras = getIntent().getExtras();
        requestPessoa = extras!= null ? extras.getString("Pessoa"):"";
        if(!requestPessoa.isEmpty() && requestPessoa.equals("Pessoa Juridica")){
            txtCpfCnpj.setHint("CNPJ:");
        }else{
            txtCpfCnpj.setHint("CPF:");
        }
        final PessoaFisicaModel pessoaFisicaModel = new PessoaFisicaModel();
        final PessoaJuridicaModel pessoaJuridicaModel = new PessoaJuridicaModel();
        final EnderecoModel endereco = new EnderecoModel();
        final PessoaController pessoaController = new PessoaController(con);
        final EnderecoController enderecoController = new EnderecoController(con);

        /*Eventos*/
        txtCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String zipCode = s.toString();

                if(s.length() == 8){
                    AddressService service = new AddressService(zipCode);
                    try {
                        setDataViews(service.execute().get());

                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        btnCadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()){
                    long idEndereco = 0;
                    long id = 0;
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
                        id = pessoaController.inserirPessoaJuridica(pessoaJuridicaModel);
                    }else {
                        pessoaFisicaModel.setNome(txtNome.getText().toString());
                        pessoaFisicaModel.setCpf(txtCpfCnpj.getText().toString());
                        pessoaFisicaModel.setDataNascimento(txtData.getText().toString());
                        id = pessoaController.inserirPessoaFisica(pessoaFisicaModel);
                    }
                    if(id > 0){
                        endereco.setId_user(id);
                        idEndereco = enderecoController.inserirEndereco(endereco);
                        if(idEndereco > 0){
                            Intent it = new Intent(CadastroActivity.this,ListUserActivity.class);
                            startActivity(it);
                        }
                    }
                }
            }
        });

        btnCancelarCadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CadastroActivity.this,MainActivity.class);
                startActivity(it);
            }
        });


    }

    private boolean validaCampos(){
        if(txtNumero.getText().toString().isEmpty()){
            return false;
        }
        else if(txtNome.getText().toString().isEmpty()){
            return false;
        }
        else if(txtBairro.getText().toString().isEmpty()){
            return false;
        }
        else if(txtCpfCnpj.getText().toString().isEmpty()){
            return false;
        }
        else if(txtCep.getText().toString().isEmpty()){
            return false;
        }
        else if(txtData.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void setDataViews(EnderecoModel endereco){
        if(endereco != null){
            txtComplemento.setText(endereco.getComplemento());
            txtBairro.setText(endereco.getBairro());
            txtUf.setText(endereco.getUf());
            txtLogradouro.setText(endereco.getLogradouro());
        }
    }

    /*Criando Mascaras para os campos de cadastro*/
    private void createMask(){
        SimpleMaskFormatter simpleMaskFormatterData = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher maskTextWatcherData = new MaskTextWatcher(txtData,simpleMaskFormatterData);
        txtData.addTextChangedListener(maskTextWatcherData);
    }
}
