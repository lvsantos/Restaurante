package com.example.lucas.restaurante;

/**
 * Created by luiz on 03/12/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bancodados.BancoDados;
import bancodados.CardapioDAO;
import bancodados.ClienteDAO;

import bancodados.ItemCardapioDAO;
import bancodados.MesaDAO;
import bancodados.RestauranteDAO;
import model.Cardapio;
import model.CartaoCredito;
import model.Cliente;
import model.Endereco;
import model.ItemCardapio;
import model.Mesa;
import model.Restaurante;

public class LoginActivity extends AppCompatActivity{
    private ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
