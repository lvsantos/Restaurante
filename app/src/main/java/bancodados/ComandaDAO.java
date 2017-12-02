package bancodados;

import android.database.Cursor;

import java.util.Vector;

import model.Comanda;
import model.Mesa;
import model.Pedido;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata as conexões da classe Comanda com o banco de dados
 */

public class ComandaDAO
{
    private BancoDados bd;
    private PedidoDAO pedidoDAO;

    public ComandaDAO(BancoDados bd)
    {
        System.out.println("Construtor da classe ComandaDAO");
        setBd(bd);
        setPedidoDAO(new PedidoDAO(bd));
    }

    public int inserirComanda(Comanda comanda, int idCli)
    {
        String query = "INSERT INTO Comanda(Cliente_id, status, Mesa_id) VALUES(" + idCli + ", " + comanda.getStatus() + ", " +
                    comanda.getMesa().getId() + ")";
        return bd.insertQuery(query);
    }

    public Comanda pesquisarComandaId(int id)
    {
        String query = "SELECT status, Mesa_id" +
                "FROM Comanda" +
                "WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            return new Comanda(id, Integer.parseInt(cursor.getString(cursor.getColumnIndex("status"))),
                    pedidoDAO.pesquisarTodosPedidosComanda(id),
                    new Mesa(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Mesa_id")))));
        }
        return null;
    }

    public Comanda pesquisarComandaAbertaCliente(int idCliente)
    {
        String query = "SELECT id, Mesa_id " +
                "FROM Comanda " +
                "WHERE Cliente_id = " + idCliente +
                " AND status = 1";
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            return new Comanda(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))),
                    1,
                    pedidoDAO.pesquisarTodosPedidosComanda(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")))),
                    new Mesa(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Mesa_id")))));
        }
        return null;
    }

    public Vector<Comanda> pesquisarTodasComandasCliente(int idCliente)
    {
        Vector<Comanda>itens = new Vector<Comanda>();

        String query = "SELECT id" +
                "FROM Comanda" +
                "WHERE Cliente_id = " + idCliente;

        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                Comanda c = pesquisarComandaId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                if (c != null)
                {
                    itens.add(c);
                }
            }while (cursor.moveToNext());
        }
        return itens;
    }

    public Vector<Comanda> pesquisarTodasComandasAbertasMesa(int idMesa)
    {
        Vector<Comanda>itens = new Vector<Comanda>();

        String query = "SELECT id" +
                "FROM Comanda" +
                "WHERE Mesa_id = " + idMesa +
                " AND status <> 2";

        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            do
            {
                Comanda c = pesquisarComandaId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                if (c != null)
                {
                    itens.add(c);
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

    public PedidoDAO getPedidoDAO() {
        return pedidoDAO;
    }

    public void setPedidoDAO(PedidoDAO pedidoDAO) {
        this.pedidoDAO = pedidoDAO;
    }
}
