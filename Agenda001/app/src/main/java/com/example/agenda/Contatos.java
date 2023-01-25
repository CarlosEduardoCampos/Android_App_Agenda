package com.example.agenda;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

public class Contatos {
    // Titulo e nomes das colunas da tabela
    private final String TITULO_TABELA = "contatos";
    private final String COLUNA_ID = "id";
    private final String COLUNA_NOME = "nome";
    private final String COLUNA_FONE = "fone";

    // Variaveis
    private Integer id;// Indentificador(unico)
    protected String nome = "";// nome do contato
    protected String telefone = "";// telefone do contato
    private Activity act = null;// tela onde a classe foi chamada
    private BancoDados db;// Banco de dados da aplicação

    // Metodo costrutor da classe contatos
    public Contatos(Activity activity, BancoDados banco){
        this.act = activity;
        this.db = banco;
    }

    // GET - SET -> id
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
                TITULO_TABELA + "(" +
                COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUNA_NOME + " TEXT," +
                COLUNA_FONE + " TEXT" +
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

    // Apaga a tabela de contatos completamente
    public void deletarTabela(){
        db.deletarTabela(TITULO_TABELA);
    }

    // Cadastra um nono contato na agenda
    public void inserir(){

        // Verifica se todos os campos estão preenchidos
        if(nome.equals("") || telefone.equals(""))
        {
            CxMsg.erroHumano(act,"Não e possivel realizar cadastro com capos vazios");
            return;
        }

        // Cadastra um contato no banco de dados
        try{
            db.inserir(
                TITULO_TABELA +"("+
                COLUNA_NOME +", "+
                COLUNA_FONE +") VALUES('"+
                nome +"', '"+
                telefone +"')"
            );
        }
        catch (Exception ex){
            CxMsg.erroExecucao(act,"Erro ao tentar cadastrar um novo contato" , ex);
        }
        finally {
            // Mesagem de sucesso
            Toast.makeText(act,"Contato gravado com sucesso",Toast.LENGTH_SHORT).show();
        }
    }

    // Apaga a um contado no banco e dados
    public void deletar(){
        try{
            db.deletar(
                    TITULO_TABELA,
                    id
            );
        }
        catch (Exception ex){
            CxMsg.erroExecucao(act,"Erro ao tentar deletar um contato" , ex);
        }
        finally {
            Toast.makeText(act,"Contato deletado com sucesso",Toast.LENGTH_SHORT).show();
        }
    }

    // Edita o contato no banco de dados
    public void editar(){
        try{
            ContentValues valores = new ContentValues();
            valores.put(COLUNA_NOME, nome);
            valores.put(COLUNA_FONE, telefone);

            db.editar(
                    id,
                    TITULO_TABELA,
                    valores
            );
        }
        catch (Exception ex){
            CxMsg.erroExecucao(act,"Erro ao tentar deletar um contato" , ex);
        }
        finally {
            Toast.makeText(act,"Contato deletado com sucesso",Toast.LENGTH_SHORT).show();
        }
    }

    // Realiza uma busca de todos os contatos no banco de dados
    public Cursor buscarTodos(){
        db.abrirDB();
        return db.buscarDados(TITULO_TABELA, new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_FONE});
    }
}
