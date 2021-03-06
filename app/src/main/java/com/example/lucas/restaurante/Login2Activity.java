package com.example.lucas.restaurante;

/**
 * Created by luiz on 03/12/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bancodados.BancoDados;
import bancodados.ClienteDAO;
import model.Cliente;
import model.ClienteLogado;

public class Login2Activity extends AppCompatActivity
{
    private ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));
    private Intent intent;
    public static String intent1 = "login";
    public static String intent2 = "senha";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        //System.out.println("Método onCreate da MainActivity()");
        //intent = new Intent(this, TelaPrincipalActivity.class);
        //gerarBaseDados();
    }

    private void changeActivity()
    {
        if (intent != null)
        {
            finish();
            startActivity(intent);
        }
    }

    private void abrirComandaActivity()
    {
        intent = new Intent(this, AbrirComandaActivity.class);
        changeActivity();
    }


    public void redirecionaCadastroCliente(View view)
    {
        intent = new Intent(this, CadastroActivity.class);
        changeActivity();
    }

    private void errorMessage(String message)
    {
        Toast.makeText(Login2Activity.this, message, Toast.LENGTH_LONG).show();
    }

    public void verifyLogin(View view)
    {
        //System.out.println("Método login() da MainActivity()");
        EditText login = (EditText) findViewById(R.id.usuario);
        EditText senha = (EditText) findViewById(R.id.input_password);

        Cliente cli = cliDao.pesquisarClienteLogin(login.getText().toString(), senha.getText().toString());
        if(cli != null)
        {
            ClienteLogado.clienteLogado = cli;
            abrirComandaActivity();
        }
        else
        {
            errorMessage("Login incorreto");
        }
    }
}
