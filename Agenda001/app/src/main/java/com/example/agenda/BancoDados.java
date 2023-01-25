package com.example.agenda;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;//Banco de dados
import android.widget.Toast;
import android.content.ContextWrapper;
import static android.content.Context.MODE_PRIVATE;

public class BancoDados {
    SQLiteDatabase db = null;
    Activity act = null;

    public BancoDados(Activity activity) {
        this.act = activity;
    }

    public void inserir(String txt){
        abrirDB();
        db.execSQL("INSERT INTO "+ txt);
        fecharDB();
    }

    // Abre o acesso ou cria um banco de dados
    public void abrirDB(){
        try{
            ContextWrapper cw = new ContextWrapper(act);
            db = cw.openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);

        }catch (Exception ex){
            CxMsg.erroExecucao(act, "Erro ao tentar abrir ou cria o banco de dados", ex);
        }
        finally {
            Toast.makeText(act,"Banco de dados foi iniciado com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    // Fecha o acesso ao banco de dados
    public void fecharDB(){
        db.close();
        Toast.makeText(act, "Banco de dados esta deligado", Toast.LENGTH_SHORT).show();
    }

    public void abrirTabela(String txt){
        try {
            abrirDB();
            db.execSQL("CREATE TABLE IF NOT EXISTS " + txt);
            fecharDB();
        }
        catch (Exception ex){
            CxMsg.erroExecucao(act, "Erro ao criar tabela", ex);
        }
        finally {
            Toast.makeText(act,"Lista de tabelas Aberta com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor buscarDados(String nome_tabela, String[] campos_tabela){
        abrirDB();
        return db.query(
                nome_tabela,
                campos_tabela,
                null,
                null,
                null,
                null,
                null,
                null
        );

    }
}
