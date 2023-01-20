package com.example.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText et_nome, et_telefone;
    Button btn_gravar, btn_consultar, btn_fechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Relacionando elementos as variaveis
        //EditText -> elemetos de imput de texto
        et_nome = findViewById(R.id.et_nome);
        et_telefone = findViewById(R.id.et_telefone);
        //Button -> elementos clicaveis
        btn_gravar = findViewById(R.id.btn_gravar);
        btn_consultar = findViewById(R.id.btn_consultar);
        btn_fechar = findViewById(R.id.btn_fechar);
    }
}