package model;

import java.util.Vector;

/**
 * Created by lucas on 01/12/17.
 * Armazena os dados relativos ao cardápio
 */

public class Cardapio
{
    private int id;
    private Vector<ItemCardapio>itensCardap; //Itens do cardápio

    public Cardapio(int id, Vector<ItemCardapio>itensCardapio)
    {
        setId(id);
        setItensCardap(itensCardapio);
    }

    public Cardapio()
    {
        setId(-1);
        setItensCardap(new Vector<ItemCardapio>());
    }

    public Cardapio(Vector<ItemCardapio>itensCardap)
    {
        setId(-1);
        setItensCardap(itensCardap);
    }

    public void     addItemCardapio(ItemCardapio item)
    {
        itensCardap.add(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector<ItemCardapio> getItensCardap() {
        return itensCardap;
    }

    public void setItensCardap(Vector<ItemCardapio> itensCardap) {
        this.itensCardap = itensCardap;
    }
}
