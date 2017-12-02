package bancodados;

import android.database.Cursor;

import Tools.StringParser;
import model.Endereco;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata as conexões da classe Endereco com o banco de dados
 */

public class EnderecoDAO
{
    private BancoDados bd;

    public EnderecoDAO(BancoDados bd)
    {
        setBd(bd);
        System.out.println("Construtor da classe EnderecoDAO");
    }

    public int inserirEndereco(Endereco end)
    {
        System.out.println("Método InserirEndereço() de ClienteDAO");
        String query = "INSERT INTO Endereco(cep, rua, numero, complemento, cidade, estado)"
                + "VALUES(" + StringParser.getAspas(end.getCep()) + ", " + StringParser.getAspas(end.getRua()) + ", "
                + end.getNum() + ", " + StringParser.getAspas(end.getComplemento()) + ", " + StringParser.getAspas(end.getCidade())
                + "," + StringParser.getAspas(end.getEstado()) + ")";
        return bd.insertQuery(query);
    }

    public Endereco pesquisarEnderecoId(int id)
    {
        String query = "SELECT cep, rua, numero, complemento, cidade, estado " +
                "FROM Endereco " +
                "WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor != null && cursor.moveToFirst())
        {
            return new Endereco(id, cursor.getString(cursor.getColumnIndex("cep")),
                    cursor.getString(cursor.getColumnIndex("rua")),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("numero"))),
                    cursor.getString(cursor.getColumnIndex("complemento")),
                    cursor.getString(cursor.getColumnIndex("cidade")),
                    cursor.getString(cursor.getColumnIndex("estado")));
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
