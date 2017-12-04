package com.example.lucas.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bancodados.BancoDados;
import bancodados.ComandaDAO;
import bancodados.MesaDAO;

import model.ClienteLogado;
import model.Mesa;

public class AbrirComandaActivity extends AppCompatActivity
{
    private ComandaDAO comanDAO = new ComandaDAO(new BancoDados(this));
    private MesaDAO mesaDAO = new MesaDAO(new BancoDados(this));
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_comanda);

        if(ClienteLogado.clienteLogado == null)
        {
            loginActivity();
        }
    }

    private void changeActivity()
    {
        if(intent != null)
        {
            finish();
            startActivity(intent);
        }
    }

    private void cardapioActivity()
    {
        intent = new Intent(this, CardapioActivity.class);
        intent.putExtra(CardapioActivity.intent1, 0);
        changeActivity();
    }

    private void loginActivity()
    {
        intent = new Intent(this, Login2Activity.class);
        intent.putExtra(Login2Activity.intent1, "lsantos");
        intent.putExtra(Login2Activity.intent1, "123");
        changeActivity();
    }

    private void errorMessage(String message)
    {
        Toast.makeText(AbrirComandaActivity.this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Método que verifica se um dados campo EditText foi preenchido pelo usuário
     * @param text: Campo a ser verificado
     * @return false, caso o campo foi preenchido. True, caso a campo não tenha sido preenchido
     */
    private boolean isEmpty(EditText text)
    {
        if (text.getText().toString().trim().length() > 0)
            return false;
        return true;
    }

    public void abrirComanda(View view)
    {

        EditText idMesa = (EditText) findViewById(R.id.idMesa);
        //Verifica se a mesa digitada existe
        if(!isEmpty(idMesa))
        {
            Mesa mesa = mesaDAO.pesquisarMesaId(Integer.parseInt(idMesa.getText().toString()));
            if(mesa != null)
            {
                System.out.println();
                ClienteLogado.clienteLogado.abrirComandaIndiv(mesa);
                int idComanda = comanDAO.inserirComanda(ClienteLogado.clienteLogado.getComandaAberta(),
                        ClienteLogado.clienteLogado.getId());
                ClienteLogado.clienteLogado.getComandaAberta().setId(idComanda);
                cardapioActivity();
            }
            else
            {
                errorMessage("Mesa inexistente");
            }
        }
    }
}
