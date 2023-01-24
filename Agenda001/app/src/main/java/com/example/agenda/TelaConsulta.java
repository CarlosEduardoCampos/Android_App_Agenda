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
    SQLiteDatabase db = null;
    Cursor cursor;
    CxMsg mensagem = new CxMsg(this);

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

        buscarDados();
    }

    // Abre o acesso ou cria um banco de dados
    public void abrirDB(){
        try{
            db = openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);
        }catch (Exception ex){
            mensagem.msgErro("Erro ao tentar abrir ou cria o banco de dados", ex);
        }
        finally {
            Toast.makeText(this,"Banco de dados foi iniciado com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    // Fecha o acesso ao banco de dados
    public void fecharDB(){
        db.close();
        Toast.makeText(this, "Banco de dados esta deligado", Toast.LENGTH_SHORT).show();
    }

    public void buscarDados(){
        abrirDB();
        cursor = db.query(
                "contatos",
                new String[]{"id","nome","fone"},
                null,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            mostrarDados();
        }
        else{
            mensagem.msg("Nenhum registro encontrado");
        }
    }

    public void proximoRegistro(View v){
        Toast.makeText(v.getContext(),"O Botão PROXIMO foi selecionado", Toast.LENGTH_SHORT).show();
        try {
            cursor.moveToNext();
            mostrarDados();
        }
        catch (Exception ex){
            if (cursor.isAfterLast()){
                mensagem.msg("Não existem mais registros");
            }
            else {
                mensagem.msgErro("Erro ao buscar dados", ex);
            }
        }
    }
    public void anteriorRegistro(View v){
        Toast.makeText(v.getContext(),"O Botão ANTERIOR  foi selecionado", Toast.LENGTH_SHORT).show();
        try {
            cursor.moveToPrevious();
            mostrarDados();
        }
        catch (Exception ex){
            if (cursor.isBeforeFirst()){
                mensagem.msg("Não existem mais registros");
            }
            else {
                mensagem.msgErro("Erro ao buscar dados", ex);
            }
        }
    }
    public void mostrarDados(){
        tv_pes_id.setText("Codigo >>> " + cursor.getInt(0));// id
        et_pes_nome.setText(cursor.getString(1));// nome
        et_pes_telefone.setText(cursor.getString(2));// telefone
    }

    // Volta para a tela anterior matando a tela atual
    public void finalizarTela(View v){
        this.finish();
    }
}