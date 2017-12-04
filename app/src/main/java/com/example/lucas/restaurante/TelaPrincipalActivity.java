package com.example.lucas.restaurante;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;

import bancodados.BancoDados;
import bancodados.ClienteDAO;
import model.Cliente;
import model.ClienteLogado;

public class TelaPrincipalActivity extends AppCompatActivity {
    private ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));
    private Intent intent;
    private Cliente cliLogado = ClienteLogado.clienteLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        //cliLogado = cliDao.pesquisarClienteId(getIntent().getIntExtra("idCli", 0));
        Toast.makeText(TelaPrincipalActivity.this,
                "Olá " + cliLogado.getNomeComp() + "\nSeja bem vindo a TelaPrincipalActivity",
                Toast.LENGTH_LONG).show();
    }

    public void abrirComanda(View view)
    {
        abrirComandaActivity();
    }

    public void abrirComandaActivity()
    {
        intent = new Intent(this, AbrirComandaActivity.class);
        startActivity(intent);
    }
}
