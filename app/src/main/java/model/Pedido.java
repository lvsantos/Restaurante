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
        setStatus(0);
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

    public void setStatus(int status) {
        this.status = status;
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
