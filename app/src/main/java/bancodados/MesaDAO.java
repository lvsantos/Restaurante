package bancodados;

import android.database.Cursor;

import java.util.Vector;

import Tools.StringParser;
import model.Mesa;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata as conexões da classe Mesa com o banco de dados
 */

public class MesaDAO
{
    private BancoDados bd;

    public MesaDAO(BancoDados bd)
    {
        setBd(bd);
    }

    public int inserirMesa(Mesa mesa, int idRest)
    {
        String query = "INSERT INTO Mesa(Restaurante_id)" +
                "VALUES(" + idRest + ")";
        return bd.insertQuery(query);
    }

    public Mesa pesquisarMesaId(int id)
    {
        String query = "SELECT id FROM Mesa WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            return new Mesa(id);
        }
        return null;
    }

    public Vector<Mesa> pesquisarTodasMesasRestaurante(int idRest)
    {
        Vector<Mesa>mesas = new Vector<>();

        String query = "SELECT id " +
                "FROM Mesa " +
                "WHERE Restaurante_id = " + idRest;

        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                mesas.add(new Mesa(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")))));
            }while (cursor.moveToNext());
        }
        return mesas;
    }

    public BancoDados getBd() {
        return bd;
    }

    public void setBd(BancoDados bd) {
        this.bd = bd;
    }
}
