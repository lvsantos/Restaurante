package bancodados;

import android.database.Cursor;

import java.util.Vector;

import Tools.StringParser;
import model.ItemCardapio;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata as conexões da classe ItemCardapio com o banco de dados
 */

public class ItemCardapioDAO
{
    private BancoDados bd;

    public ItemCardapioDAO(BancoDados bd)
    {
        setBd(bd);
    }

    public int inserirItemCardapio(ItemCardapio item, int idCardapio)
    {
        String query = "INSERT INTO Item_cardapio(nome, descricao, valor, ingredientes, tipo, isVisible, Cardapio_id)" +
                "VALUES(" + StringParser.getAspas(item.getNome()) + ", " + StringParser.getAspas(item.getDesc()) + ", " +
                item.getValor() + ", " + StringParser.getAspas(item.getIngred()) + ", " + item.getTipo() + ", " +
                item.isVisible() + ", " + idCardapio + ")";
        return bd.insertQuery(query);
    }

    public ItemCardapio pesquisarItemCardapioId(int id)
    {
        String query = "SELECT nome, descricao, valor, ingredientes, tipo, isVisible " +
                "FROM Item_cardapio " +
                "WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            return new ItemCardapio(id, cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("descricao")),
                    Double.parseDouble(cursor.getString(cursor.getColumnIndex("valor"))),
                    cursor.getString(cursor.getColumnIndex("ingredientes")),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("tipo"))),
                    Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("isVisible")))
            );
        }
        return null;
    }

    public Vector<ItemCardapio> pesquisarTodosItensCardapio(int idCardapio)
    {
        Vector<ItemCardapio>itens = new Vector<ItemCardapio>();

        String query = "SELECT id " +
                "FROM Item_cardapio " +
                "WHERE Cardapio_id = " + idCardapio;
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                ItemCardapio i = pesquisarItemCardapioId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
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
}
