package com.example.lucas.restaurante;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

import bancodados.BancoDados;
import bancodados.CardapioDAO;
import bancodados.ClienteDAO;

import bancodados.ItemCardapioDAO;
import bancodados.MesaDAO;
import bancodados.RestauranteDAO;
import model.Cardapio;
import model.CartaoCredito;
import model.Cliente;
import model.ClienteLogado;
import model.Endereco;
import model.ItemCardapio;
import model.Mesa;
import model.Restaurante;

public class MainActivity extends AppCompatActivity
{
    ClienteDAO cliDao = new ClienteDAO(new BancoDados(this));
    private RestauranteDAO restDAO = new RestauranteDAO(new BancoDados(this));
    private MesaDAO mesaDAO = new MesaDAO(new BancoDados(this));
    private CardapioDAO cardapioDAO = new CardapioDAO(new BancoDados(this));
    private ItemCardapioDAO itemCardapioDAO = new ItemCardapioDAO(new BancoDados(this));
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("Método onCreate da MainActivity()");
        intent = new Intent(this, TelaPrincipalActivity.class);
        //gerarBaseDados();
    }

    public void login(View view)
    {
        System.out.println("Método login() da MainActivity()");
        Cliente cli = cliDao.pesquisarClienteLogin("lvsantos");
        ClienteLogado.clienteLogado = cli;
        telaPrincipalActivity(cli.getId());
    }

    public void telaPrincipalActivity(int idCliente)
    {
        intent.putExtra("idCli", idCliente);
        startActivity(intent);
    }

    //Método para gerar base de dados nas tabelas para teste. (Excluir depois)
    public void gerarBaseDados()
    {
        cliDao.inserirCliente(new Cliente("Lucas Valtudes Basílio dos Santos",
                "119.888.266-24", "lvsantos", "123456", "lucasvbsantos@gmail.com",
                new Endereco("30640095", "Caetano de azeredo", 94, "Casa", "BH", "MG"),
                "26/12/1996", 'M', "64648-9810",
                new CartaoCredito("455445504", 485, 2, "Lucas V B Santos")));
        cliDao.inserirCliente(new Cliente("Vitor Valtudes Basílio dos Santos",
                "265.658.256-22", "viturino", "12345678", "vitor_valtudes64@gmail.com",
                new Endereco("30640095", "Caetano de azeredo", 94, "Casa", "BH", "MG"),
                "26/12/1996", 'M', "64648-9810",
                new CartaoCredito("455445504", 485, 2, "Vitinho Santos")));

        Restaurante rest = new Restaurante("Rock Bar", "Rock Bar LTDA", "164644646", "rockbar",
                "123", "rock@gmail.com", "56454445",
                new Endereco("646464646", "Rua tal", 25, "", "BH",
                        "MG"));

        Cardapio cardapio = new Cardapio();

        rest.setCardapio(cardapio);
        int id = restDAO.inserirRestaurante(rest);
        rest.addMesa(new Mesa(1, id));
        mesaDAO.inserirMesa(new Mesa(1, id));
        rest.addMesa(new Mesa(2, id));
        mesaDAO.inserirMesa(new Mesa(2, id));
        rest.addMesa(new Mesa(3, id));
        mesaDAO.inserirMesa(new Mesa(3, id));
        rest.addMesa(new Mesa(4, id));
        mesaDAO.inserirMesa(new Mesa(4, id));
        rest.addMesa(new Mesa(5, id));
        mesaDAO.inserirMesa(new Mesa(5, id));
        rest.addMesa(new Mesa(6, id));
        mesaDAO.inserirMesa(new Mesa(6, id));

        int idCard = cardapioDAO.inserirCardapio(cardapio, id);
        itemCardapioDAO.inserirItemCardapio(new ItemCardapio("Coca Cola", "Coca gelada", 2.36, "Não tem ingredientes",
                2, true, idCard));
        itemCardapioDAO.inserirItemCardapio(new ItemCardapio("Suco de uva", "Suco geladinho", 1.30,
                "Suco natural", 2, true, idCard));
        itemCardapioDAO.inserirItemCardapio(new ItemCardapio("Picanha", "Picanha suculenta", 10.36, "",
                1, true, idCard));
        itemCardapioDAO.inserirItemCardapio(new ItemCardapio("Arroz", "Arroz fino", 8.6, "Arroz fino grão",
                1, true, idCard));
        itemCardapioDAO.inserirItemCardapio(new ItemCardapio("Feijão", "Feijão tropeiro", 10, "Feijão e tropeiro",
                1, true, idCard));
        itemCardapioDAO.inserirItemCardapio(new ItemCardapio("Petit Gatot", "Sobremesa francesa", 26.6, "Chocolate",
                3, true, idCard));

    }
}
