package com.example.lucas.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import bancodados.BancoDados;
import bancodados.ItemPedidoDAO;
import bancodados.PedidoDAO;
import model.ClienteLogado;
import model.ItemPedido;

public class ProcessarPgtoActivity extends AppCompatActivity
{
    private PedidoDAO pedDAO = new PedidoDAO(new BancoDados(this));
    private ItemPedidoDAO itemPedDAO = new ItemPedidoDAO(new BancoDados(this));
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processar_pgto);
        preencheTela();
    }

    private void changeActivity()
    {
        if(intent != null)
        {
            startActivity(intent);
        }
    }

    private void comandaActivity()
    {
        intent = new Intent(this, ComandaActivity.class);
        changeActivity();
    }

    private void preencheTela()
    {
        TextView textViewNomeCartao = (TextView) findViewById(R.id.nome_cartao);
        TextView textViewNumCartao = (TextView) findViewById(R.id.num_cartao);
        TextView textViewDatValid = (TextView) findViewById(R.id.data_validade);
        TextView textViewCodSegur = (TextView) findViewById(R.id.cod_seguranca);

        textViewNomeCartao.setText(ClienteLogado.clienteLogado.getCartaoCred().getNomeTitular());
        textViewNumCartao.setText(ClienteLogado.clienteLogado.getCartaoCred().getNumero());
        textViewDatValid.setText("07/2022");
        System.out.println(ClienteLogado.clienteLogado.getCartaoCred().getCodSeguranca());
        textViewCodSegur.setText(String.valueOf(ClienteLogado.clienteLogado.getCartaoCred().getCodSeguranca()));
    }

    public void concluirPagamento(View view)
    {
        ClienteLogado.clienteLogado.getPedidoAberto().setStatus(ItemPedido.PGTO_APROV);
        int idPed = pedDAO.inserirPedido(ClienteLogado.clienteLogado.getPedidoAberto(),
                                         ClienteLogado.clienteLogado.getComandaAberta().getId());
        ClienteLogado.clienteLogado.getPedidoAberto().setId(idPed);

        for(int i = 0; i < ClienteLogado.clienteLogado.getPedidoAberto().getItems().size(); i++)
        {
            int idItem = itemPedDAO.inserirItemPedido(ClienteLogado.clienteLogado.getPedidoAberto().getItems().get(i),
                                                      ClienteLogado.clienteLogado.getPedidoAberto().getId());
            ClienteLogado.clienteLogado.getPedidoAberto().getItems().get(i).setId(idItem);
        }

        ClienteLogado.clienteLogado.getComandaAberta().addPedido(ClienteLogado.clienteLogado.getPedidoAberto());
        ClienteLogado.clienteLogado.setPedidoAberto(null);

        Toast.makeText(ProcessarPgtoActivity.this,
                ClienteLogado.clienteLogado.getNomeComp() + "\nSeu pagamento foi processado com sucesso",
                Toast.LENGTH_LONG).show();
        comandaActivity();
    }
}
