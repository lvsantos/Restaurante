package com.example.lucas.restaurante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import bancodados.BancoDados;
import bancodados.ItemPedidoDAO;
import bancodados.PedidoDAO;
import model.Cliente;
import model.ClienteLogado;
import model.ItemPedido;

public class ProcessarPgtoActivity extends AppCompatActivity
{
    private Cliente cliLogado = ClienteLogado.clienteLogado;
    private PedidoDAO pedDAO = new PedidoDAO(new BancoDados(this));
    private ItemPedidoDAO itemPedDAO = new ItemPedidoDAO(new BancoDados(this));

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processar_pgto);
        preencheTela();
    }

    private void preencheTela()
    {
        TextView textViewNomeCartao = (TextView) findViewById(R.id.nome_cartao);
        TextView textViewNumCartao = (TextView) findViewById(R.id.num_cartao);
        TextView textViewDatValid = (TextView) findViewById(R.id.data_validade);
        TextView textViewCodSegur = (TextView) findViewById(R.id.cod_seguranca);

        textViewNomeCartao.setText(cliLogado.getCartaoCred().getNomeTitular());
        textViewNumCartao.setText(cliLogado.getCartaoCred().getNumero());
        textViewDatValid.setText("07/2022");
        System.out.println(cliLogado.getCartaoCred().getCodSeguranca());
        textViewCodSegur.setText(String.valueOf(cliLogado.getCartaoCred().getCodSeguranca()));
    }

    public void concluirPagamento(View view)
    {
        cliLogado.getPedidoAberto().setStatus(ItemPedido.PGTO_APROV);
        int idPed = pedDAO.inserirPedido(cliLogado.getPedidoAberto(), cliLogado.getComandaAberta().getId());
        cliLogado.getPedidoAberto().setId(idPed);

        for(int i = 0; i < cliLogado.getPedidoAberto().getItems().size(); i++)
        {
            int idItem = itemPedDAO.inserirItemPedido(cliLogado.getPedidoAberto().getItems().get(i),
                    cliLogado.getPedidoAberto().getId());
            cliLogado.getPedidoAberto().getItems().get(i).setId(idItem);
        }

        cliLogado.getComandaAberta().addPedido(cliLogado.getPedidoAberto());
        cliLogado.setPedidoAberto(null);
        ClienteLogado.clienteLogado = cliLogado;

        Toast.makeText(ProcessarPgtoActivity.this,
                cliLogado.getNomeComp() + "\nSeu pagamento foi processado com sucesso",
                Toast.LENGTH_LONG).show();

    }
}
