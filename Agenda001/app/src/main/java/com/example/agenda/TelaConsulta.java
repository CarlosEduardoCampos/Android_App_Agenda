package com.example.agenda;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TelaConsulta extends AppCompatActivity {

    EditText et_pes_nome, et_pes_telefone;
    TextView tv_pes_id;
    Button btn_anterior, btn_proximo, btn_voltar;
    Cursor cursor;
    BancoDados banco = new BancoDados(this);
    Contatos contatos = new Contatos(this, banco);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);

        // Relacionando elementos as variaveis

        //EditText -> elemetos de imput de texto
        et_pes_nome = findViewById(R.id.et_pes_nome);
        et_pes_telefone = findViewById(R.id.et_pes_telefone);

        //TextView
        tv_pes_id = findViewById(R.id.tv_pes_id);

        //Button -> elementos clicaveis
        btn_anterior = findViewById(R.id.btn_anteriro);
        btn_proximo = findViewById(R.id.btn_proximo);
        btn_voltar = findViewById(R.id.btn_voltar);

        // Reliza busca de todos o contatos
        cursor = contatos.buscarTodos();

        // Testa se os contatos foram encontrados
        if(cursor.getCount() != 0){
            // Aponta e mostra o primeiro contato
            cursor.moveToFirst();
            mostrarDados();
        }
        else{
            CxMsg.erro(this,"Nenhum registro encontrado");
        }
    }

    // Aponta e mostra o proximo contato
    public void proximoRegistro(View v){
        Toast.makeText(v.getContext(),"O Botão PROXIMO foi selecionado", Toast.LENGTH_SHORT).show();
        try {
            cursor.moveToNext();
            mostrarDados();
        }
        catch (Exception ex){
            if (cursor.isAfterLast()){
                CxMsg.erro(this, "Não existem mais registros");
            }
            else {
                CxMsg.erroExecucao(this,"Erro ao buscar dados", ex);
            }
        }
    }

    // Aponta e mostra o contato anterior
    public void anteriorRegistro(View v){
        Toast.makeText(v.getContext(),"O Botão ANTERIOR  foi selecionado", Toast.LENGTH_SHORT).show();
        try {
            cursor.moveToPrevious();
            mostrarDados();
        }
        catch (Exception ex){
            if (cursor.isBeforeFirst()){
                CxMsg.erro(this, "Não existem mais registros");
            }
            else {
                CxMsg.erroExecucao(this,"Erro ao buscar dados", ex);
            }
        }
    }

    // Imprime na tela qual o contato que esta sendo apontado
    public void mostrarDados(){
        // Atualiza as informações dos campos
        tv_pes_id.setText("Codigo >>> " + cursor.getInt(0));// id
        et_pes_nome.setText(cursor.getString(1));// nome
        et_pes_telefone.setText(cursor.getString(2));// telefone

        // Recebe o indetificador para realiza deletar() e editar()
        contatos.setId(cursor.getInt(0));
    }

    // Chama uma função que apaga do banco o contato mostrado na tela
    public void deletarContato(View v){
        // Apaga o contato que esta setato atualmente
        contatos.deletar();

        // Atualiza as informações da tela
        cursor = contatos.buscarTodos();
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            mostrarDados();
        }
        else{
            CxMsg.erro(this,"Ultimo registro Deletado");
        }
    }

    // Chama uma função que edita o contato mostrado na tela
    public void editarContatos(View v){

        // Novos parametros
        int ponteiro  = cursor.getPosition();
        contatos.setNome(et_pes_nome.getText().toString());
        contatos.setTelefone(et_pes_telefone.getText().toString());

        // Edita banco de dados
        contatos.editar();

        // Atualiza as informações da tela
        cursor = contatos.buscarTodos();
        cursor.move(ponteiro + 1);
        mostrarDados();
    }

    // Volta para a tela anterior matando a tela atual
    public void finalizarTela(View v){
        banco.fecharDB();
        this.finish();
    }
}