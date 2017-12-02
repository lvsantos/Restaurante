package bancodados;

import android.database.Cursor;

import Tools.StringParser;
import model.CartaoCredito;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata todas as conexões da classe CartaoCredito com o banco de dados
 */

public class CartaoCreditoDAO
{
    private BancoDados bd;

    public CartaoCreditoDAO(BancoDados bd)
    {
        setBd(bd);
    }

    public int inserirCartaoCredito(CartaoCredito cartao)
    {
        System.out.println("Método InserirCartaoCredito() de ClienteDAO");
        String query = "INSERT INTO Cartao_credito(numero, codSeguranca, bandeira, nomeTitular)"
                + "VALUES(" + StringParser.getAspas(cartao.getNumero()) + ", " + cartao.getCodSeguranca() + ", " +
                cartao.getBandeira() + ", " + StringParser.getAspas(cartao.getNomeTitular()) + ")";
        return bd.insertQuery(query);
    }

    public CartaoCredito pesquisarCartaoCreditoId(int id)
    {
        String query = "SELECT numero, codSeguranca, bandeira, nomeTitular " +
                "FROM Cartao_credito " +
                "WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            return new CartaoCredito(id, cursor.getString(cursor.getColumnIndex("numero")),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("codSeguranca"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("bandeira"))),
                    cursor.getString(cursor.getColumnIndex("nomeTitular")));
        }
        return null;
    }

    public BancoDados getBd() {
        return bd;
    }

    public void setBd(BancoDados bd) {
        this.bd = bd;
    }
}
