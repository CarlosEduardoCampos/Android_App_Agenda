package com.example.agenda;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;//Banco de dados
import android.widget.Toast;
import android.content.ContextWrapper;
import static android.content.Context.MODE_PRIVATE;

public class BancoDados {
    SQLiteDatabase db = null; // Banco de dados
    Activity act = null;// Tela em que a função foi chamada

    // Costrutor da classe
    public BancoDados(Activity activity) {
        this.act = activity;
    }

    // Executa no banco de dados um insert
    public void inserir(String txt){
        abrirDB();
        db.execSQL("INSERT INTO "+ txt);
        fecharDB();
    }

    // Executa no banco de dados um update
    public void editar(Integer id, String nome_tabela, ContentValues valores_tabela){
        abrirDB();
        db.update(nome_tabela,valores_tabela,"id=?", new String[]{String.valueOf(id)});
    }

    //Executa no banco de dados um delete
    public void deletar(String nome_tabela, Integer id){
        abrirDB();
        db.delete(nome_tabela, "id=?", new String[]{String.valueOf(id)});
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

    // Executa um create table no banco de dados
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

    // Executa um drop table no banco de dados
    public void deletarTabela(String nome_tabela){
        try {
            abrirDB();
            db.execSQL("DROP TABLE IF EXISTS " + nome_tabela);
            fecharDB();
        }
        catch (Exception ex){
            CxMsg.erroExecucao(act,"Erro ao apagar a tabela", ex);
        }
        finally {
            Toast.makeText(act, "Tabela "+ nome_tabela + "foi deletada", Toast.LENGTH_LONG).show();
        }
    }

    // Executa uma query de pesquisa no banco de dados
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
