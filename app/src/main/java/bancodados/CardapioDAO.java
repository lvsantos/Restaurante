package bancodados;

import android.database.Cursor;

import model.Cardapio;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata todas as conexões da classe Cardapio com o banco de dados
 */

public class CardapioDAO
{
    private BancoDados bd;
    private ItemCardapioDAO itemCardapioDAO;

    public CardapioDAO(BancoDados bd)
    {
        setBd(bd);
        setItemCardapioDAO(new ItemCardapioDAO(bd));
    }

    public int inserirCardapio(Cardapio cardapio, int idRestaurante)
    {
        String query = "INSERT INTO Cardapio(Restaurante_id) VALUES(" + idRestaurante + ")";
        return bd.insertQuery(query);
    }

    public Cardapio pesquisarCardapioId(int id)
    {
        return new Cardapio(id, itemCardapioDAO.pesquisarTodosItensCardapio(id));
    }

    public Cardapio pesquisarCardapioRestaurante(int idRestaurante)
    {
        String query = "SELECT id FROM Cardapio WHERE Restaurante_id = " + idRestaurante;
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            return new Cardapio(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))),
                    itemCardapioDAO.pesquisarTodosItensCardapio(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")))));
        }
        return null;
    }

    public BancoDados getBd() {
        return bd;
    }

    public void setBd(BancoDados bd) {
        this.bd = bd;
    }

    public ItemCardapioDAO getItemCardapioDAO() {
        return itemCardapioDAO;
    }

    public void setItemCardapioDAO(ItemCardapioDAO itemCardapioDAO) {
        this.itemCardapioDAO = itemCardapioDAO;
    }
}
