package com.example.lucas.restaurante;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Vector;

import bancodados.BancoDados;
import bancodados.RestauranteDAO;

import model.ClienteLogado;
import model.Pedido;
import model.Restaurante;

public class ComandaActivity extends AppCompatActivity
{
    private RestauranteDAO restDAO = new RestauranteDAO(new BancoDados(this));
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comanda);

        Restaurante rest = restDAO.pesquisarRestauranteId(ClienteLogado.clienteLogado.getComandaAberta().getMesa().getRest_id());
        criarCabecalho(rest);
        gerarListaDaComanda(ClienteLogado.clienteLogado.getComandaAberta().getPedidos());
    }

    private void criarCabecalho(Restaurante rest)
    {
        TextView rest_nome = (TextView) findViewById(R.id.nome_rest);

        //Altera os campos restaurante, mesa, comanda e cliente
        if(rest != null)
        {
            rest_nome.setText("Restaurante: " + rest.getNome());
        }
        TextView mesa_id = (TextView) findViewById(R.id.mesa_id);
        mesa_id.setText(String.valueOf("Mesa: " + ClienteLogado.clienteLogado.getComandaAberta().getMesa().getId()));
        TextView comanda_id = (TextView) findViewById(R.id.comanda_id);
        comanda_id.setText(String.valueOf("Comanda: " + ClienteLogado.clienteLogado.getComandaAberta().getId()));
        TextView cliente_nome = (TextView) findViewById(R.id.nome_cliente);
        cliente_nome.setText("Cliente: " + ClienteLogado.clienteLogado.getNomeComp());
    }

    private void gerarListaDaComanda(Vector<Pedido> pedidos)
    {
        //Gera todos os pedidos da comanda

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayPrincip = (LinearLayout) findViewById(R.id.layoutPrincipal);

        for (int i = 0; pedidos != null && i < pedidos.size(); i++)
        {
            final int indexPed = i; //Utilizada caso seja clicado em um pedido
            LinearLayout customView = (LinearLayout) inflater.inflate(R.layout.activity_custom_pedido, null);
            customView.setClickable(true);
            customView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    pedidoActivity(indexPed);
                }
            });

            //Modifica a view com os dados dos pedidos
            TextView textViewNumPed = (TextView) customView.getChildAt(0);
            TextView textViewQtdItens = (TextView) customView.getChildAt(1);
            TextView textViewValTot = (TextView) customView.getChildAt(2);
            TextView textViewStatusPed = (TextView) customView.getChildAt(3);

            textViewNumPed.setText("Número do pedido :" + pedidos.get(i).getId());
            textViewQtdItens.setText("Quantidade de itens: " + pedidos.get(i).getItems().size());
            double valTot = 0;
            for(int j = 0; j < pedidos.get(i).getItems().size(); j++)
            {
                valTot += pedidos.get(i).getItems().get(j).getValor();
            }
            textViewValTot.setText("Valor: R$" + valTot);
            textViewStatusPed.setText("Status: " + pedidos.get(i).getStatusText());

            //Verifica a cor de fundo do pedido
            if(i % 2 == 0)
            {
                customView.setBackgroundColor(Color.RED);
            }
            else
            {
                customView.setBackgroundColor(Color.BLUE);
            }

            linearLayPrincip.addView(customView);
        }

        /*
        //Gambiarra para não sobrescrever com o menu inferior
        TextView gam = new TextView(getApplicationContext());
        gam.setTextSize(100);
        linearLayPrincip.addView(gam);
        //Fim gambiarra*/
    }

    private void changeActivity()
    {

        if(intent != null)
        {
            startActivity(intent);
        }
    }

    private void pedidoActivity(int idPed)
    {
        intent = new Intent(this, PedidoActivity.class);
        intent.putExtra(PedidoActivity.intent1, idPed);
        changeActivity();
    }
}
