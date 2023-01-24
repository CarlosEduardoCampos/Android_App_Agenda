package com.example.agenda;

import android.app.Activity;
import android.app.AlertDialog;

public class CxMsg {
    Activity activity;

    //Metodos construtor
    public CxMsg(Activity act){
        this.activity = act;
    }
    // Mesagem padrão para erros(try/catch)
    public void msg(String txt){
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);

        alerta.setTitle("!!! Error de Execução !!!");
        alerta.setMessage(txt);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }

    // Mesagem padrão para erros(try/catch)
    public void msgErro(String txt, Exception erro){
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);

        alerta.setTitle("!!! Error de Execução !!!");
        alerta.setMessage(txt + "\n<<< DESCRIÇÃO >>>\n" + erro);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }

    // Mensagem para erros ocorridos quando o usuario fazer merda
    public void msgErroH(String txt){
        AlertDialog.Builder campo_alerta= new AlertDialog.Builder(activity);
        campo_alerta.setTitle("!!! Erro Humano !!!");
        campo_alerta.setMessage(txt);
        campo_alerta.show();
    }
}
