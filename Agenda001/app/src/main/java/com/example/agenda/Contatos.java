package com.example.agenda;

import android.app.Activity;
import android.database.Cursor;
import android.widget.Toast;

public class Contatos {
    protected String nome;
    protected String telefone;
    private Activity act = null;
    private BancoDados db;

    public Contatos(Activity activity, BancoDados banco){
        this.act = activity;
        this.db = banco;
    }

    // GET - SET -> nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    // GET - SET -> telefone
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    // Cria a tabela de Contatos
    public void criarTabela(){
        try {
            db.abrirTabela(
            "contatos(" +
                "id INTERGER PRIMARY KEY," +
                "nome TEXT," +
                "fone TEXT" +
                ");"
            );
        }
        catch (Exception ex){
            CxMsg.erroExecucao(act, "Erro ao tentar criar tabela", ex);
        }
        finally {
            Toast.makeText(act,"Tabela contatos criada com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    // Cadastra um nono contato na agenda
    public void inserir(Contatos pes, BancoDados db){

        // Verifica se todos os campos estão preenchidos
        if(pes.nome.equals("") || pes.telefone.equals("")){
            CxMsg.erroHumano(act,"Não e possivel realizar cadastro com capos vazios");
            return;
        }

        // Reliza um cadastro no banco de dados
        try{
            db.inserir("contatos(id, nome, fone) VALUES('" + nome + "', '" + telefone + "')");
        }
        catch (Exception ex){
            CxMsg.erroExecucao(act,"Erro ao tentar cadastrar um novo contato" , ex);
        }
        finally {
            // Mesagem de sucesso
            Toast.makeText(act,"Contato gravado com sucesso",Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor buscarTodos(){
        db.abrirDB();
        return db.buscarDados("contatos", new String[]{"id", "nome", "fone"});
    }
}
