package bancodados;

import android.database.Cursor;

import java.util.Vector;

import Tools.StringParser;
import model.ItemPedido;
import model.Pedido;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata todas as conexões da classe Pedido com o banco de dados
 */

public class PedidoDAO
{
    private BancoDados bd;
    private ItemPedidoDAO itemPedidoDAO;

    public  PedidoDAO(BancoDados bd)
    {
        setBd(bd);
        setItemPedidoDAO(new ItemPedidoDAO(bd));
    }

    public int inserirPedido(Pedido pedido, int idComanda)
    {
        String query = "INSERT INTO Pedido(status, nota, reclamacao, Comanda_id)" +
                "VALUES(" + pedido.getStatus() + ", " + pedido.getNota() + ", " + StringParser.getAspas(pedido.getReclamacao())
                + ", " + idComanda + ")";
        return bd.insertQuery(query);
    }

    public Pedido pesquisarPedidoId(int id)
    {
        String query = "SELECT status, nota, reclamacao " +
                "FROM Pedido " +
                "WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            return new Pedido(id, Integer.parseInt(cursor.getString(cursor.getColumnIndex("status"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("nota"))),
                    cursor.getString(cursor.getColumnIndex("reclamacao")),
                    itemPedidoDAO.pesquisarTodosItensPedido(id));
        }
        return null;
    }

    public Vector<Pedido> pesquisarTodosPedidosComanda(int idComanda)
    {
        Vector<Pedido>itens = new Vector<Pedido>();

        String query = "SELECT id " +
                "FROM Pedido " +
                "WHERE Comanda_id = " + idComanda;

        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                Pedido p = pesquisarPedidoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                if (p != null)
                {
                    itens.add(p);
                }
            }while (cursor.moveToNext());
        }
        return itens;
    }

    public BancoDados getBd() {
        return bd;
    }

    public void setBd(BancoDados bd) {
        this.bd = bd;
    }

    public ItemPedidoDAO getItemPedidoDAO() {
        return itemPedidoDAO;
    }

    public void setItemPedidoDAO(ItemPedidoDAO itemPedidoDAO) {
        this.itemPedidoDAO = itemPedidoDAO;
    }
}
