package model;

import java.util.Vector;

/**
 * Created by lucas on 29/11/17.
 * Armazena os dados dos pedidos de um cliente
 */

public class Pedido
{
    int id;
    private int status;
    private int nota;
    private String reclamacao;
    private Vector<ItemPedido>items;

    public Pedido(int id, int status, int nota, String reclamacao)
    {
        setId(id);
        setStatus(status);
        setNota(nota);
        setReclamacao(reclamacao);
    }

    public Pedido(int status, int nota, String reclamacao)
    {
        setId(-1);
        setStatus(status);
        setNota(nota);
        setReclamacao(reclamacao);
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
}
