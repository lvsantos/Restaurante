package com.example.lucas.restaurante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import bancodados.BancoDados;
import model.CartaoCredito;

public class MainActivity extends AppCompatActivity
{
    BancoDados bd = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Aqui foi cambada");
        bd.addCartaoCredito(new CartaoCredito(445645454, 4645, 2, "Lucas Santos"));
        Toast.makeText(MainActivity.this, "Salvo coom sucesso", Toast.LENGTH_LONG).show();
        System.out.println("Salvo com sucesso");
    }
}
