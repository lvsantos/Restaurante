package model;

/**
 * Created by lucas on 29/11/17.
 * Armazena os dados de um item do pedido
 */

public class ItemPedido
{
    private int id;
    private int status;

    public ItemPedido(int id, int status)
    {
        setId(id);
        setStatus(status);
    }

    public ItemPedido()
    {
        setId(-1);
        setStatus(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
