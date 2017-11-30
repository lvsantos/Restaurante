package com.example.lucas.restaurante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import bancodados.BancoDados;
import bancodados.ClienteDAO;
import model.CartaoCredito;
import model.Cliente;
import model.Endereco;

public class MainActivity extends AppCompatActivity
{
    ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Método onCreate da MainActivity()");
        /*String query = "INSERT INTO Cliente(nomeComp, cpf, email, login, senha, dataNasc";
        bd.insertQuery(query);*/
    }

    public void login(View view)
    {
        System.out.println("Método login() da MainActivity()");
        cliDao.inserirCliente(new Cliente("Lucas Valtudes Santos", 1986450, "lvsanto", "123", "gmail.com",
                new Endereco("30640", "Caetano", 94, "Apto", "BH", "MG"), "22/08/1992", 'M', "646",
                new CartaoCredito(4554, 48, 1, "Lu Santos")));
        Toast.makeText(MainActivity.this, "Salvo coom sucesso", Toast.LENGTH_LONG).show();
    }
}
