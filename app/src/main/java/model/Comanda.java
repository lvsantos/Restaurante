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
    private Mesa mesa;
    private int status; //0: Nem aberta, 1: Aberta, 2: Fechada

    public Comanda(int id, int status, Vector<Pedido>pedidos, Mesa mesa)
    {
        setId(id);
        setStatus(status);
        setPedidos(pedidos);
        setMesa(mesa);
    }

    public Comanda(Mesa mesa)
    {
        setId(-1);
        setStatus(1);
        pedidos = new Vector<>();
        setMesa(mesa);
    }

    public void addPedido(Pedido pedido)
    {
        pedidos.add(pedido);
    }

    //Métodos getters e setters

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

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
