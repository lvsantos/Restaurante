package com.example.lucas.restaurante;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Vector;

import bancodados.BancoDados;
import bancodados.RestauranteDAO;
import model.Cliente;
import model.ClienteLogado;
import model.ItemPedido;
import model.Restaurante;

public class PedidoActivity extends AppCompatActivity
{
    private Cliente cliLogado = ClienteLogado.clienteLogado;
    private RestauranteDAO restDAO = new RestauranteDAO(new BancoDados(this));
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        Restaurante rest = restDAO.pesquisarRestauranteId(cliLogado.getComandaAberta().getMesa().getRest_id());
        criarCabecalho(rest);
        if(cliLogado.getPedidoAberto() != null)
        {
            gerarListaDoPedido(cliLogado.getPedidoAberto().getItems());
        }
    }

    public void finalizarPedido(View view)
    {
        cliLogado.getPedidoAberto().setStatus(ItemPedido.AGUARDANDO_APROV);
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
        mesa_id.setText(String.valueOf("Mesa: " + cliLogado.getComandaAberta().getMesa().getId()));
        TextView comanda_id = (TextView) findViewById(R.id.comanda_id);
        comanda_id.setText(String.valueOf("Comanda: " + cliLogado.getComandaAberta().getId()));
        TextView cliente_nome = (TextView) findViewById(R.id.nome_cliente);
        cliente_nome.setText("Cliente: " + cliLogado.getNomeComp());
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
            textViewIngred.setText(itensPed.get(i).getIngred());
            textViewPreco.setText("R$" + itensPed.get(i).getValor());

            linearLayPrincip.addView(customView);

            valTot += itensPed.get(i).getValor();
        }

        TextView textViewValTot = (TextView) findViewById(R.id.valTot);
        textViewValTot.setText("VALOR TOTAL: R$" + valTot);

        //Gambiarra para não sobrescrever com o menu inferior
        TextView gam = new TextView(getApplicationContext());
        gam.setTextSize(100);
        linearLayPrincip.addView(gam);
        //Fim gambiarra*/
    }

    private void changeActivity()
    {
        startActivity(intent);
    }

    private void pagamentoActivity()
    {
        //intent = new Intent(this, ProcessarPgtoActivity.class);
        changeActivity();
    }
}
