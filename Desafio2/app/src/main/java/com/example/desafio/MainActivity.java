package com.example.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.desafio.dbHelper.ConexaoSQLite;

public class MainActivity extends AppCompatActivity {

    private Button btnPessoaJuridica;
    private Button btnPessoaFisica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexaoSQLite con =  ConexaoSQLite.getInstancia(this);

        btnPessoaFisica = (Button) findViewById(R.id.btnCadPessoaFisica);
        btnPessoaJuridica = (Button) findViewById(R.id.btnCadPessoaJuridica);

        btnPessoaFisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                it.putExtra("Pessoa", "Pessoa Fisica");
                startActivity(it);
            }
        });

        btnPessoaJuridica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                it.putExtra("Pessoa", "Pessoa Juridica");
                startActivity(it);
            }
        });
    }
}
