package com.example.agenda;

import android.app.AlertDialog;
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

    SQLiteDatabase db=null;

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
        abrirDB();
        abrirTabela();
        fecharDB();
    }

    // Abre o acesso ou cria um banco de dados
    public void abrirDB(){
        try{
            db = openOrCreateDatabase("bancoAgenda", MODE_PRIVATE, null);
        }catch (Exception ex){
            msg("Erro ao tentar abrir ou cria o banco de dados" + "\n>>>> DESCRIÇÃO >>>>\n" + ex);
        }
        finally {
            Toast.makeText(this,"Banco de dados foi iniciado com sucesso", Toast.LENGTH_SHORT).show();
        }
    }

    // Abre ou cria uma tabela
    public void abrirTabela(){
        // Se ainda não existir cria a tabela de contatos
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS contatos(id INTERGER PRIMARY KEY AUTOINCREMENT, nome TEXT, fone TEXT);");
        }
        catch (Exception ex){
            msg("Erro ao tentar cria a tabela de contatos" + "\n>>>> DESCRIÇÃO >>>>\n" + ex);
        }
        finally {
            Toast.makeText(this, "Tabela criada com sucesso !!!", Toast.LENGTH_SHORT).show();
        }
    }

    // Fecha o acesso ao banco de dados
    public void fecharDB(){
        db.close();
        Toast.makeText(this, "Banco de dados esta deligado", Toast.LENGTH_SHORT).show();
    }

    // Cadastra um novo contato na Agenda
    public void inserirRegistro(View v){

        // Resebe valores digitados
        String st_nome = et_nome.getText().toString();
        String st_telefone = et_telefone.getText().toString();

        // Verifica se todos os campos estão preenchidos
        if(st_nome.equals("") || st_telefone.equals("")){
            // Mosta uma mesagem de Erro ao usuario
            AlertDialog.Builder campo_alerta= new AlertDialog.Builder(this);
            campo_alerta.setTitle("!!! Erro Humano !!!");
            campo_alerta.setMessage("Campos não podem estar vazios");
            campo_alerta.show();
            return;
        }

        // Reliza um cadastro no banco de dados
        try{
            abrirDB();
            db.execSQL("INSERT INTO contatos(nome, fone) VALUES('" + st_nome + "', '" + st_telefone + "')");
            fecharDB();
        }
        catch (Exception ex){
            msg("Erro ao tentar cadastrar um novo contato" + "\n>>>> DESCRIÇÃO >>>>\n" + ex);
        }
        finally {
            // Limpa os campos de digitação
            et_nome.setText("");
            et_telefone.setText("");

            // Mesagem de sucesso
            Toast.makeText(this,"Contato gravado com sucesso",Toast.LENGTH_SHORT).show();
        }
    }

    // Mesagem padrão para erros(try/catch)
    public void msg(String txt){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("!!! Error de Execução !!!");
        alerta.setMessage(txt);
        alerta.setNeutralButton("OK", null);
        alerta.show();
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