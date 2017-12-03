package bancodados;

import android.database.Cursor;

import java.util.Vector;

import Tools.StringParser;
import model.ItemCardapio;
import model.ItemPedido;
import model.Pedido;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata todas as conexões da classe ItemPedido com o banco de dados
 */

public class ItemPedidoDAO
{
    private BancoDados bd;
    private ItemCardapioDAO itemCardapDAO;

    public ItemPedidoDAO(BancoDados bd)
    {
        setBd(bd);
        setItemCardapDAO(new ItemCardapioDAO(bd));
    }

    public int inserirItemPedido(ItemPedido item, int idPedido)
    {
        String query = "INSERT INTO Item_Pedido(Pedido_id, status, Item_cardapio_id)" +
                "VALUES(" + idPedido + ", " + item.getStatus() + ", " + item.getId() + ")";
        return bd.insertQuery(query);
    }

    public ItemPedido pesquisarItemPedidoId(int id)
    {
        String query = "SELECT status, Item_cardapio_id " +
                "FROM Item_Pedido " +
                "WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor.moveToFirst())
        {
            int idCardapio = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Item_cardapio_id")));
            ItemCardapio iC = itemCardapDAO.pesquisarItemCardapioId(idCardapio);
            if(iC != null)
            {
                return new ItemPedido(iC.getId(), iC.getNome(), iC.getDesc(), iC.getValor(), iC.getIngred(), iC.getTipo(),
                        iC.isVisible()==1, id, Integer.parseInt(cursor.getString(cursor.getColumnIndex("status"))),
                        iC.getId_cardapio());
            }
        }
        return null;
    }

    public Vector<ItemPedido> pesquisarTodosItensPedido(int idPedido)
    {
        Vector<ItemPedido>itens = new Vector<ItemPedido>();

        String query = "SELECT id " +
                "FROM Item_Pedido " +
                "WHERE Pedido_id = " + idPedido;

        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                ItemPedido i = pesquisarItemPedidoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                if (i != null)
                {
                    itens.add(i);
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

    public ItemCardapioDAO getItemCardapDAO() {
        return itemCardapDAO;
    }

    public void setItemCardapDAO(ItemCardapioDAO itemCardapDAO) {
        this.itemCardapDAO = itemCardapDAO;
    }
}
