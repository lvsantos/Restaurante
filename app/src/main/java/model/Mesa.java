package model;

import java.util.Vector;

/**
 * Created by lucas on 29/11/17.
 * Armazena os dados da mesa
 */

public class Mesa
{
    private int id;
    private boolean isOpen;
    private Vector<Cliente>clientes;

    public Mesa(int id, boolean isOpen, Vector<Cliente>clientes)
    {
        setId(id);
        setOpen(isOpen);
        setClientes(clientes);
    }

    public Mesa()
    {
        setId(-1);
        setOpen(false);
    }

    public void associarCliente(Cliente cli)
    {
        clientes.add(cli);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Vector<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Vector<Cliente> clientes) {
        this.clientes = clientes;
    }
}
