package com.example.lucas.restaurante;

/**
 * Created by luiz on 03/12/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bancodados.BancoDados;
import bancodados.ClienteDAO;

public class LoginActivity extends AppCompatActivity
{
    private ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));
    private Intent intent;
    public static String intent1 = "login";
    public static String intent2 = "senha";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //System.out.println("Método onCreate da MainActivity()");
        //intent = new Intent(this, TelaPrincipalActivity.class);
        //gerarBaseDados();
    }

    public void verifyLogin(View view)
    {
        //System.out.println("Método login() da MainActivity()");
        //Cliente cli = cliDao.pesquisarClienteLogin("lvsantos");
    }
}
