package com.example.lucas.restaurante;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import bancodados.BancoDados;
import bancodados.ItemCardapioDAO;
import bancodados.RestauranteDAO;
import model.Cliente;
import model.ClienteLogado;
import model.ItemCardapio;
import model.Restaurante;

public class CardapioActivity extends AppCompatActivity
{
    //private ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));
    private RestauranteDAO restDAO = new RestauranteDAO((new BancoDados(this)));
    private ItemCardapioDAO itemCardDAO = new ItemCardapioDAO(new BancoDados(this));
    private Cliente cliLogado = ClienteLogado.clienteLogado;
    private Vector<ItemCardapio> itensCardap;
    private int index = 0;
    private int tipo; // Tipo de comidas que serão exibidas no cardápio

    //Cria intents
    private Intent intent;
    public static String intent1 = "tipo";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        //cliLogado = cliDao.pesquisarClienteId(getIntent().getIntExtra("idCli", 0));
        tipo = getIntent().getIntExtra(intent1, 0);

        if(cliLogado.getPedidoAberto() != null)
        {
            Toast.makeText(CardapioActivity.this, "Olá " + cliLogado.getNomeComp() + "\n" + "Você acabou de pedir " +
                            cliLogado.getPedidoAberto().getItems().get(cliLogado.getPedidoAberto().getItems().size()-1).getNome() +
                            "Qtd: " + cliLogado.getPedidoAberto().getItems().size()
                    , Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(CardapioActivity.this, "Tipo = " + tipo
                    , Toast.LENGTH_LONG).show();
        }
        Restaurante rest = restDAO.pesquisarRestauranteId(cliLogado.getComandaAberta().getMesa().getRest_id());
        criarCabecalho(rest);
        if(tipo == 0)
        {
            itensCardap = itemCardDAO.pesquisarTodosItensCardapio(rest.getCardapio().getId());
        }
        else
        {
            itensCardap = itemCardDAO.pesquisarTodosItensCardapioTipo(rest.getCardapio().getId(), tipo);
        }

        gerarCardapio(itensCardap);
        gerarMenuInferior();
    }

    public void criarCabecalho(Restaurante rest)
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

    private void gerarCardapio(final Vector<ItemCardapio>itensCardap)
    {
        //Gera todos os itens do cardápio
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll1_id);
        for(int i = 0; i < itensCardap.size(); i++)
        {
            index = i;
            //Layout linear horizontal
            final LinearLayout linearLayout2 = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams linearLayParam = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            linearLayout2.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout2.setLayoutParams(linearLayParam);
            linearLayout2.setId(itensCardap.get(i).getId());

            //Imagem a esquerda
            ImageView imageView = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams imageLay = new LinearLayout.LayoutParams(150,150);
            imageView.setImageResource(R.drawable.comida_web);
            imageView.setLayoutParams(imageLay);

            //Linear Layout vertical
            LinearLayout linearLayout3 = new LinearLayout(getApplicationContext());
            linearLayParam = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT);
            linearLayout3.setOrientation(LinearLayout.VERTICAL);
            linearLayout3.setLayoutParams(linearLayParam);

            //Nome do item do cardápio
            TextView textView = new TextView(getApplicationContext());
            LinearLayout.LayoutParams textLay = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            textLay.setMargins(15, 30, 10, 40);
            textView.setText(itensCardap.get(i).getNome());
            textView.setTextSize(20);
            textView.setLayoutParams(textLay);

            //Ingredientes do item do cardápio
            TextView textView2 = new TextView(getApplicationContext());
            textLay.setMargins(15, 10, 0 , 0);
            textView2.setText(itensCardap.get(i).getIngred());
            textView2.setLayoutParams(textLay);

            linearLayout3.addView(textView);
            linearLayout3.addView(textView2);

            linearLayout2.addView(imageView);
            linearLayout2.addView(linearLayout3);
            linearLayout2.setClickable(true);
            linearLayout2.setOnClickListener(new View.OnClickListener(){
                LinearLayout l = linearLayout2;
                @Override
                public void onClick(View v) {
                    itemCardapioActivity(l.getId());
                }
            });

            linearLayout.addView(linearLayout2);
        }

        /*Gambiarra para não sobrescrever com o menu inferior */
        TextView gam = new TextView(getApplicationContext());
        gam.setTextSize(55);
        linearLayout.addView(gam);
        /*Fim gambiarra*/
    }

    private void gerarMenuInferior()
    {
        //Recupera o relative layout de toda a tela
        RelativeLayout relativeLayout = findViewById(R.id.rl1);

        //Layout linear horizontal do menu fixo inferior
        LinearLayout linearLayMenuInf = new LinearLayout(getApplicationContext());
        RelativeLayout.LayoutParams linearLayParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        linearLayMenuInf.setOrientation(LinearLayout.HORIZONTAL);
        linearLayParam.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        linearLayParam.setMargins(0, 500, 0, 0);

        //Botão comida do menu inferior
        final Button buttonComida = new Button(getApplicationContext());
        LinearLayout.LayoutParams buttonLayParam =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,65, 1);
        buttonComida.setText("Comida");
        buttonComida.setTextSize(12);
        buttonComida.setBackgroundColor(Color.RED);
        buttonComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardapioComidaActivity();
            }
        });
        linearLayMenuInf.addView(buttonComida, buttonLayParam);

        //Botão bebida do menu inferior
        Button buttonBebida = new Button(getApplicationContext());
        buttonLayParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,65, 1);
        buttonBebida.setText("Bebida");
        buttonBebida.setTextSize(12);
        buttonBebida.setBackgroundColor(Color.RED);
        buttonBebida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardapioBebidaActivity();
            }
        });
        linearLayMenuInf.addView(buttonBebida, buttonLayParam);

        //Botão sobremesa do menu inferior
        Button buttonSobre = new Button(getApplicationContext());
        buttonLayParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,65, 1);
        buttonSobre .setText("Sobremesa");
        buttonSobre .setTextSize(12);
        buttonSobre.setBackgroundColor(Color.RED);
        buttonSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardapioSobremesaActivity();
            }
        });
        linearLayMenuInf.addView(buttonSobre , buttonLayParam);

        //Botão Pedidos do menu inferior
        Button buttonPed = new Button(getApplicationContext());
        buttonLayParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,65, 1);
        buttonPed.setText("Pedidos");
        buttonPed.setTextSize(12);
        buttonPed.setBackgroundColor(Color.RED);
        buttonPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedidoEmAbertoActivity();
            }
        });
        linearLayMenuInf.addView(buttonPed, buttonLayParam);

        relativeLayout.addView(linearLayMenuInf, linearLayParam);
    }

    private void changeActivity()
    {
        //intent.putExtra("idCli", cliLogado.getId());
        startActivity(intent);
    }

    private void cardapioComidaActivity()
    {
        //intent = new Intent(this, CardapioComidaActivity.class);
        intent = getIntent();
        intent.putExtra(CardapioActivity.intent1, ItemCardapio.COMIDA);
        finish();
        changeActivity();
    }

    private void cardapioBebidaActivity()
    {
        intent = getIntent();
        intent.putExtra(CardapioActivity.intent1, ItemCardapio.BEBIDA);
        finish();
        changeActivity();
    }

    private void cardapioSobremesaActivity()
    {
        intent = getIntent();
        intent.putExtra(CardapioActivity.intent1, ItemCardapio.SOBREMESA);
        finish();
        changeActivity();
    }

    private void pedidoEmAbertoActivity()
    {
        intent = new Intent(this, PedidoActivity.class);
        changeActivity();
    }

    private void itemCardapioActivity(int iditem)
    {
        intent = new Intent(this, ItemCardapioActivity.class);
        intent.putExtra(ItemCardapioActivity.intent1, iditem);
        changeActivity();
    }
}
