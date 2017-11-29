package model;

import java.util.Vector;

/**
 * Created by lucas on 29/11/17.
 * Armazena os dados das comandas abertas
 */

public class Comanda
{
    private int id;
    private Vector<Pedido>pedidos;

    public Comanda(int id)
    {
        setId(id);
    }

    public Comanda()
    {
        setId(-1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Vector<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
