package model;

import java.util.Vector;

/**
 * Created by lucas on 29/11/17.
 * Armazena os dados da mesa
 */

public class Mesa
{
    private int id;
    private int rest_id; // id do restaurante
    //private boolean isOpen;
    //private Vector<Comanda>comandas;

    public Mesa(int id, int idRest/*, boolean isOpen, Vector<Comanda>comandas*/)
    {
        System.out.println("No construtor da classe Mesa");
        setId(id);
        setRest_id(idRest);
        /*setOpen(isOpen);
        setComandas(comandas);*/
    }

    /*public Mesa(int id)
    {
        setId(id);
        setOpen(false);
        setComandas(new Vector<Comanda>());
    }*/

    public Mesa(int idRest)
    {
        setId(-1);
        setRest_id(idRest);
        /*setOpen(false);
        setComandas(new Vector<Comanda>());*/
    }

    /*public void addComanda(Comanda comanda)
    {
        if(comandas.size() == 0)
        {
            setOpen(true);
        }
        comandas.add(comanda);
    }*/

    /*public void removeComanda(Comanda comanda)
    {
        if(comandas.remove(comanda) && comandas.size() == 0)
        {
            setOpen(false);
        }
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public Vector<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(Vector<Comanda> comandas) {
        this.comandas = comandas;
    }*/

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }
}
