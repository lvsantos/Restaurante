package com.example.lucas.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import bancodados.BancoDados;
import bancodados.ClienteDAO;
import model.Cliente;

public class CardapioActivity extends AppCompatActivity
{
    private ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));
    private Intent intent;
    private Cliente cliLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        cliLogado = cliDao.pesquisarClienteId(getIntent().getIntExtra("idCli", 0));
        Toast.makeText(CardapioActivity.this, "Olá " + cliLogado.getNomeComp() + "\n" +
                        "A sua comanda de número " + cliLogado.getComandaAberta().getId() + "\n" +
                        "está na mesa " + cliLogado.getComandaAberta().getMesa().getId(), Toast.LENGTH_LONG).show();
    }


}
