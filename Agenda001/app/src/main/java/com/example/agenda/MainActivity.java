package com.example.agenda;

import static com.example.agenda.CxMsg.*;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;//Banco de dados
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_nome, et_telefone;
    Button btn_gravar, btn_consultar, btn_fechar;
    BancoDados db = new BancoDados(this);
    Contatos contatos = new Contatos(this, db);

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

        // Iniciando o banco de dados
        //contatos.deletarTabela();
        contatos.criarTabela();
    }

    // Chama a função que cadastra um novo contato
    public void inserirRegistro(View v){
        contatos.setNome(et_nome.getText().toString());
        contatos.setTelefone(et_telefone.getText().toString());
        contatos.inserir();
    }

    // Cria uma nova tela acima da atual
    public void abrirTelaConsulta(View v){
        Intent it_consulta = new Intent(this, TelaConsulta.class);
        startActivity(it_consulta);
    }

    // Volta para a tela anterior matando a tela atual
    public void finalizarTela(View v){
        this.finish();
    }
}