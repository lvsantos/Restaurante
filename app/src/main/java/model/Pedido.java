package model;

import java.util.Vector;

/**
 * Created by lucas on 29/11/17.
 * Armazena os dados dos pedidos de um cliente
 */

public class Pedido
{
    int id;
    /**
     * status = 0: Em andamento, 1: Aprovação pagamento, 2: Pagamento aprovado, 3: Em preparo, 4: Encaminhado, 5: Finalizado
     */
    private int status;
    private int nota;
    private String reclamacao;
    private Vector<ItemPedido>items;

    public Pedido(int id, int status, int nota, String reclamacao, Vector<ItemPedido>items)
    {
        setId(id);
        setStatus(status);
        setNota(nota);
        setReclamacao(reclamacao);
        setItems(items);
    }

    public Pedido()
    {
        setId(-1);
        setStatus(ItemPedido.ANDAMENTO);
        setNota(-1);
        setReclamacao("");
        items = new Vector<ItemPedido>();
    }

    public void addItemPedido(ItemPedido item)
    {
        items.add(item);
    }

    public void removerItemPedido(ItemPedido item)
    {
        items.remove(item);
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

    public String getStatusText()
    {
        String retorno = new String();
        if (status == ItemPedido.AGUARDANDO_APROV)
        {
            retorno = "Aguardando aprovação do pagamento";
        }
        else if (status == ItemPedido.PGTO_APROV)
        {
            retorno = "Pagamento aprovado";
        }
        else if (status == ItemPedido.ANDAMENTO)
        {
            retorno = "Pedido em andamento";
        }
        else if (status == ItemPedido.ENCAMINHADO)
        {
            retorno = "Pedido encaminhado";
        }
        else if (status == ItemPedido.FINALIZADO)
        {
            retorno = "Pedido finalizado";
        }
        else if (status == ItemPedido.PREPARO)
        {
            retorno = "Pedido em preparo";
        }
        return retorno;
    }

    public void setStatus(int status)
    {
        this.status = status;

        for(int i = 0; items != null && i < items.size(); i++)
        {
            items.get(i).setStatus(status);
        }
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getReclamacao() {
        return reclamacao;
    }

    public void setReclamacao(String reclamacao) {
        this.reclamacao = reclamacao;
    }

    public Vector<ItemPedido> getItems() {
        return items;
    }

    public void setItems(Vector<ItemPedido> items) {
        this.items = items;
    }
}
