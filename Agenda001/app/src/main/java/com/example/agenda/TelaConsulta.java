package com.example.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TelaConsulta extends AppCompatActivity {

    EditText et_pes_nome, et_pes_telefone;
    Button btn_anterior, btn_proximo, btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        // Relacionando elementos as variaveis

        //EditText -> elemetos de imput de texto
        et_pes_nome = findViewById(R.id.et_pes_nome);
        et_pes_telefone = findViewById(R.id.et_pes_telefone);

        //Button -> elementos clicaveis
        btn_anterior = findViewById(R.id.btn_anteriro);
        btn_proximo = findViewById(R.id.btn_proximo);
        btn_voltar = findViewById(R.id.btn_voltar);

        // Mensagem de cofirmação do click
        btn_anterior.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"O Botão ANTERIOR  foi selecionado", Toast.LENGTH_SHORT).show();
            }
        });

        btn_proximo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"O Botão PROXIMO foi selecionado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Volta para a tela anterior matando a tela atual
    public void finalizarTela(View v){
        this.finish();
    }
}