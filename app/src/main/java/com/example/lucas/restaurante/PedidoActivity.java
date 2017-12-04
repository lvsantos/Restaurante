package com.example.lucas.restaurante;

import android.content.Context;
import android.content.Intent;
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
import model.ItemPedido;
import model.Restaurante;

public class PedidoActivity extends AppCompatActivity
{
    private RestauranteDAO restDAO = new RestauranteDAO(new BancoDados(this));
    private Intent intent;
    public static String intent1 = "idPedido";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        Restaurante rest = restDAO.pesquisarRestauranteId(ClienteLogado.clienteLogado.getComandaAberta().getMesa().getRest_id());
        criarCabecalho(rest);
        if(ClienteLogado.clienteLogado.getPedidoAberto() != null)
        {
            gerarListaDoPedido(ClienteLogado.clienteLogado.getPedidoAberto().getItems());
        }
        else
        {
            int id = getIntent().getIntExtra(intent1, -1);
            if(id != -1)
            {
                System.out.println(id);
                System.out.println(ClienteLogado.clienteLogado.getComandaAberta().getPedidos().size());
                gerarListaDoPedido(ClienteLogado.clienteLogado.getComandaAberta().getPedidos().get(id).getItems());
            }
        }
    }

    private void changeActivity()
    {
        if(intent != null)
        {
            startActivity(intent);
        }
    }

    private void pagamentoActivity()
    {
        intent = new Intent(this, ProcessarPgtoActivity.class);
        changeActivity();
    }

    public void finalizarPedido(View view)
    {
        ClienteLogado.clienteLogado.getPedidoAberto().setStatus(ItemPedido.AGUARDANDO_APROV);
        pagamentoActivity();
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
        comanda_id.setText(String.valueOf("Comanda: " +ClienteLogado.clienteLogado.getComandaAberta().getId()));
        TextView cliente_nome = (TextView) findViewById(R.id.nome_cliente);
        cliente_nome.setText("Cliente: " + ClienteLogado.clienteLogado.getNomeComp());
    }

    private void gerarListaDoPedido(Vector<ItemPedido> itensPed)
    {
        double valTot = 0;
        //Gera todos os itens do cardápio

        LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayPrincip = (LinearLayout) findViewById(R.id.layoutPrincipal);

        for (int i = 0; i < itensPed.size(); i++) {
            LinearLayout customView = (LinearLayout) inflater.inflate(R.layout.activity_custom_item, null);

            //Modifica a view com os dados dos itens do pedido
            //ImageView imageView = (ImageView) customView.getChildAt(0);
            LinearLayout linearLayoutTextos = (LinearLayout) customView.getChildAt(1);
            TextView textViewNome = (TextView) linearLayoutTextos.getChildAt(0);
            TextView textViewIngred = (TextView) linearLayoutTextos.getChildAt(1);
            TextView textViewPreco = (TextView) customView.getChildAt(2);

            textViewNome.setText(itensPed.get(i).getNome());
            if(ClienteLogado.clienteLogado.getPedidoAberto() != null)
            {
                textViewIngred.setText(itensPed.get(i).getIngred());
            }
            else
            {
                textViewIngred.setText(itensPed.get(i).getStatusText());
                TextView buttonFinalizarPed = (TextView) findViewById(R.id.finalizarPedido);
                buttonFinalizarPed.setVisibility(View.INVISIBLE);
            }

            textViewPreco.setText("R$" + itensPed.get(i).getValor());

            linearLayPrincip.addView(customView);

            valTot += itensPed.get(i).getValor();
        }

        TextView textViewValTot = (TextView) findViewById(R.id.valTot);
        textViewValTot.setText("VALOR TOTAL: R$" + valTot);

        //Gambiarra para não sobrescrever com o menu inferior
        TextView gam = new TextView(getApplicationContext());
        gam.setTextSize(150);
        linearLayPrincip.addView(gam);
        //Fim gambiarra*/
    }
}
