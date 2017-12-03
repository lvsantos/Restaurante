package com.example.lucas.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import bancodados.BancoDados;
import bancodados.ClienteDAO;
import bancodados.ItemCardapioDAO;
import model.Cliente;
import model.ClienteLogado;
import model.ItemCardapio;
import model.ItemPedido;

public class ItemCardapioActivity extends AppCompatActivity
{
    //ClienteDAO cliDAO = new ClienteDAO(new BancoDados(this));
    ItemCardapioDAO itemCarDAO = new ItemCardapioDAO(new BancoDados(this));
    Cliente cliLogado = ClienteLogado.clienteLogado;
    ItemCardapio itemCardapio;

    //Cria intents
    Intent intent;
    public static String intent1 = "idItemCardapio";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_cardapio);
        //cliLogado = cliDAO.pesquisarClienteId(getIntent().getIntExtra("idCli", 0));
        itemCardapio = itemCarDAO.pesquisarItemCardapioId(getIntent().getIntExtra(intent1, 0));
        /*Toast.makeText(ItemCardapioActivity.this, "Olá " + cliLogado.getNomeComp() + "\n" +
                        "Você clicou no item " + itemCardapio.getNome() + " - " + itemCardapio.getId(), Toast.LENGTH_LONG).show();*/
        gerarDadosItemCardapio(itemCardapio);
    }

    private void changeTextView(TextView textView, String value)
    {
        textView.setText(value);
    }

    private void displayItemNome(String nome)
    {
        TextView textViewNome = (TextView) findViewById(R.id.item_nome);
        changeTextView(textViewNome, nome);
    }

    private void displayItemIngred(String ingred)
    {
        TextView textViewIngred = (TextView) findViewById(R.id.item_ingred);
        changeTextView(textViewIngred, ingred);
    }

    private void displayItemValor(double valor)
    {
        TextView textViewValor = (TextView) findViewById(R.id.item_valor);
        changeTextView(textViewValor, NumberFormat.getInstance().format(valor));
    }

    private void displayValTot(double valorTot)
    {
        TextView textViewValTot = (TextView) findViewById(R.id.ped_valor);
        changeTextView(textViewValTot, NumberFormat.getInstance().format(valorTot));
    }

    private void displayQtdItens(int qtd)
    {
        TextView textViewQtd = (TextView) findViewById(R.id.item_qtd);
        changeTextView(textViewQtd, String.valueOf(qtd));
    }

    public void incrementaQtdItens(View view)
    {
        TextView textViewQtd = (TextView) findViewById(R.id.item_qtd);
        TextView textViewValUnit = (TextView) findViewById(R.id.item_valor);

        int qtdItens = Integer.parseInt(textViewQtd.getText().toString()) + 1;
        System.out.println(qtdItens);
        double valUnit = Double.parseDouble(textViewValUnit.getText().toString().replace(",","."));
        System.out.println(valUnit);
        double valorTotal = valUnit * qtdItens;

        displayQtdItens(qtdItens);
        displayValTot(valorTotal);
    }

    public void decrementaQtdItens(View view)
    {
        TextView textViewQtd = (TextView) findViewById(R.id.item_qtd);
        TextView textViewValUnit = (TextView) findViewById(R.id.item_valor);

        int qtdItens = Integer.parseInt(textViewQtd.getText().toString()) - 1;
        System.out.println(qtdItens);
        double valUnit = Double.parseDouble(textViewValUnit.getText().toString().replace(",","."));
        System.out.println(valUnit);
        double valorTotal = valUnit * qtdItens;

        displayQtdItens(qtdItens);
        displayValTot(valorTotal);
    }

    private void gerarDadosItemCardapio(ItemCardapio item)
    {
        displayItemNome(item.getNome());

        displayItemIngred(item.getIngred());

        displayItemValor(item.getValor());

        TextView textViewQtd = (TextView) findViewById(R.id.item_qtd);
        displayValTot(item.getValor() * Double.parseDouble(textViewQtd.getText().toString()));
    }

    public void adicionarItemPedido(View view)
    {
        boolean isVisible = itemCardapio.isVisible() == 1?true:false;
        ItemPedido itemPedido = new ItemPedido(itemCardapio.getId(), itemCardapio.getNome(), itemCardapio.getDesc(),
        itemCardapio.getValor(), itemCardapio.getIngred(), itemCardapio.getTipo(), isVisible, itemCardapio.getId_cardapio());

        //Adiciona a quantidade de itens pedidos
        TextView textViewQtd = (TextView) findViewById(R.id.item_qtd);
        int qtd = Integer.parseInt(textViewQtd.getText().toString());
        for(int i = 0; i < qtd; i++)
        {
            cliLogado.addItemPedido(itemPedido);
        }

        ClienteLogado.clienteLogado = cliLogado;
        cardapioActivity();
    }

    public void cardapioActivity()
    {
        intent = new Intent(this, CardapioActivity.class);
        startActivity(intent);
    }
}
