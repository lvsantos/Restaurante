package com.example.lucas.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bancodados.BancoDados;
import bancodados.ClienteDAO;
import bancodados.ComandaDAO;
import bancodados.MesaDAO;
import bancodados.RestauranteDAO;
import model.Cardapio;
import model.Cliente;
import model.ClienteLogado;
import model.Endereco;
import model.ItemCardapio;
import model.Mesa;
import model.Restaurante;

public class AbrirComandaActivity extends AppCompatActivity
{

    //private ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));
    private ComandaDAO comanDAO = new ComandaDAO(new BancoDados(this));
    private MesaDAO mesaDAO = new MesaDAO(new BancoDados(this));
    private Intent intent;
    private Cliente cliLogado = ClienteLogado.clienteLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_comanda);
        System.out.println("Método onCreate da classe AbrirComandaActivity");
        //cliLogado = cliDao.pesquisarClienteId(getIntent().getIntExtra("idCli", 0));
    }

    public void abrirComanda(View view)
    {
        System.out.println("Function abrirComanda() da tela AbrirComandaActivity");
        EditText idMesa = (EditText) findViewById(R.id.idMesa);
        //Verifica se a mesa digitada existe
        Mesa mesa = mesaDAO.pesquisarMesaId(Integer.parseInt(idMesa.getText().toString()));
        if(mesa != null)
        {
            cliLogado.abrirComandaIndiv(mesa);
            int idComanda = comanDAO.inserirComanda(cliLogado.getComandaAberta(), cliLogado.getId());
            cliLogado.getComandaAberta().setId(idComanda);
            ClienteLogado.clienteLogado = cliLogado;
            cardapioActivity();
        }
        else
        {
            errorMessage("Mesa inexistente");
        }
    }

    public void cardapioActivity()
    {
        intent = new Intent(this, CardapioActivity.class);
        //intent.putExtra("idCli", idCliente);
        intent.putExtra(CardapioActivity.intent1, 0);
        startActivity(intent);
    }

    public void errorMessage(String message)
    {
        Toast.makeText(AbrirComandaActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
