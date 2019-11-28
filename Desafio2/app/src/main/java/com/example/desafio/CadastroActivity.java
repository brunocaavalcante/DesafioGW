package com.example.desafio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CadastroActivity extends AppCompatActivity {

    private String pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Bundle extras = getIntent().getExtras();
        pessoa = extras!= null ? extras.getString("Pessoa"):"";

    }
}
