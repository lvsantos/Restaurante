package com.example.lucas.restaurante;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bancodados.BancoDados;
import bancodados.CardapioDAO;
import bancodados.ItemCardapioDAO;
import bancodados.MesaDAO;
import bancodados.RestauranteDAO;
import model.Cardapio;
import model.Endereco;
import model.ItemCardapio;
import model.Mesa;
import model.Restaurante;

public class MainActivity extends AppCompatActivity
{
    private RestauranteDAO restDAO = new RestauranteDAO(new BancoDados(this));
    private MesaDAO mesaDAO = new MesaDAO(new BancoDados(this));
    private CardapioDAO cardapioDAO = new CardapioDAO(new BancoDados(this));
    private ItemCardapioDAO itemCardapioDAO = new ItemCardapioDAO(new BancoDados(this));
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gerarBaseDados();
    }

    private void changeActivity()
    {
        if(intent != null)
        {
            startActivity(intent);
        }
    }

    public void redirecionaLogin(View view)
    {
        intent = new Intent(this, Login2Activity.class);
        changeActivity();
    }

    public void redirecionaAbrirComanda(View view)
    {
        intent = new Intent(this, AbrirComandaActivity.class);
        changeActivity();
    }

    public void redirecionaCadastroCliente(View view)
    {
        intent = new Intent(this, CadastroActivity.class);
        changeActivity();
    }

    /*public void telaPrincipalActivity()
    {
        intent = new Intent(this, TelaPrincipalActivity.class);
        startActivity(intent);
    }*/

    //Método para gerar base de dados nas tabelas para teste. (Excluir depois)
    public void gerarBaseDados()
    {
        if(restDAO.pesquisarRestauranteId(1) == null)
        {
            Restaurante rest = new Restaurante("Restaurante Lab II",
                                               "Laboratório de projetos II",
                                               "164644646",
                                               "rockbar",
                                               "123",
                                               "rock@gmail.com",
                                               "56454445",
                                               new Endereco("646464646",
                                                            "Rua tal",
                                                            25,
                                                            "",
                                                            "BH",
                                                            "MG"
                                                           )
                                              );
            int id = restDAO.inserirRestaurante(rest);
            rest.setId(id);
            Cardapio cardapio = new Cardapio();
            int idCard = cardapioDAO.inserirCardapio(cardapio, id);
            cardapio.setId(idCard);
            rest.setCardapio(cardapio);

            cardapio.addItemCardapio(new ItemCardapio("Suco de abacaxi",
                                                      "Suco de abacaxi",
                                                      5.50,
                                                      "Suco natural de abacaxi",
                                                      ItemCardapio.BEBIDA,
                                                      true,
                                                      cardapio.getId()));
            cardapio.addItemCardapio(new ItemCardapio("Suco de laranja",
                    "Suco de laranja",
                    5.50,
                    "Suco natual de laranja",
                    ItemCardapio.BEBIDA,
                    true,
                    cardapio.getId()));

            cardapio.addItemCardapio(new ItemCardapio("Brahma",
                    "Brahma 600 ml",
                    9.90,
                    "Brahma 600 ml",
                    ItemCardapio.BEBIDA,
                    true,
                    cardapio.getId()));

            cardapio.addItemCardapio(new ItemCardapio("Caipirinha",
                    "Caipirinha",
                    12,
                    "Caipirinha 300 ml",
                    ItemCardapio.BEBIDA,
                    true,
                    cardapio.getId()));

            cardapio.addItemCardapio(new ItemCardapio("Fritas 750g",
                    "Fritas 750g",
                    20,
                    "Batata fritas com bacon",
                    ItemCardapio.COMIDA,
                    true,
                    cardapio.getId()));

            cardapio.addItemCardapio(new ItemCardapio("X-egg",
                    "X-egg",
                    11,
                    "Pão, ovo, hamburger, queijo, presunto, tomate e alface",
                    ItemCardapio.COMIDA,
                    true,
                    cardapio.getId()));

            cardapio.addItemCardapio(new ItemCardapio("Coca-Cola",
                    "Coca-Cola",
                    4.50,
                    "Coca-Cola 350 ml",
                    ItemCardapio.BEBIDA,
                    true,
                    cardapio.getId()));

            cardapio.addItemCardapio(new ItemCardapio("Espaguete à Bolonhesa",
                    "Espaguete à Bolonhesa",
                    7.80,
                    "Espaguete à Bolonhesa com arroz",
                    ItemCardapio.COMIDA,
                    true,
                    cardapio.getId()));

            cardapio.addItemCardapio(new ItemCardapio("Canelone",
                    "",
                    5.50,
                    "Cannelone Presunto e Mussarela",
                    ItemCardapio.COMIDA,
                    true,
                    cardapio.getId()));

            cardapio.addItemCardapio(new ItemCardapio("Lombo Grelhado",
                    "",
                    15.90,
                    "Lombo, Arroz, Farofa, Alface, Tomate e Palmito",
                    ItemCardapio.COMIDA,
                    true,
                    cardapio.getId()));

            for(int i = 0; i < cardapio.getItensCardap().size(); i++)
            {
                itemCardapioDAO.inserirItemCardapio(cardapio.getItensCardap().get(i));
            }

            rest.addMesa(new Mesa(rest.getId()));
            rest.addMesa(new Mesa(rest.getId()));
            rest.addMesa(new Mesa(rest.getId()));
            rest.addMesa(new Mesa(rest.getId()));
            rest.addMesa(new Mesa(rest.getId()));
            rest.addMesa(new Mesa(rest.getId()));

            for(int i = 0; i < rest.getMesas().size(); i++)
            {
                mesaDAO.inserirMesa(rest.getMesas().get(i));
            }

        }

    }
}
