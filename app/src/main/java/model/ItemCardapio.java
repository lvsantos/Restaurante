package model;

import java.security.PublicKey;

import Tools.StringParser;

/**
 * Created by lucas on 01/12/17.
 * Classe que armazena os dados relativos aos itens do cardápio
 */

public class ItemCardapio
{
    private int id;
    private String nome;
    private String desc; //Descrição
    private double valor;
    private String ingred; // Ingredientes
    private int tipo; // 1: Comida, 2: Bebida, 3: Sobremesa
    private boolean isVisible; //Visivel no cardápio
    private int id_cardapio;

    public static int COMIDA = 1;
    public static int BEBIDA = 2;
    public static int SOBREMESA = 3;

    public ItemCardapio(int id, String nome, String desc, double valor, String ingred, int tipo, boolean isVisible,
                        int id_cardapio)
    {
        setId(id);
        setNome(nome);
        setDesc(desc);
        setValor(valor);
        setIngred(ingred);
        setTipo(tipo);
        setVisible(isVisible);
        setId_cardapio(id_cardapio);
    }

    public ItemCardapio(String nome, String desc, double valor, String ingred, int tipo, boolean isVisible, int id_cardapio)
    {
        setId(-1);
        setNome(nome);
        setDesc(desc);
        setValor(valor);
        setIngred(ingred);
        setTipo(tipo);
        setVisible(isVisible);
        setId_cardapio(id_cardapio);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getIngred() {
        return ingred;
    }

    public void setIngred(String ingred) {
        this.ingred = ingred;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int isVisible() {
        return isVisible?1:0;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(int id_cardapio) {
        this.id_cardapio = id_cardapio;
    }
}
