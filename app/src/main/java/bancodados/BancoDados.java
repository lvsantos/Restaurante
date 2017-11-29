package bancodados;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Script;
import android.util.Log;

import java.util.Vector;

import model.CartaoCredito;

/**
 * Created by lucas on 29/11/17.
 * Classe responsável por criar o banco de dados da aplicação
 */

public class BancoDados extends SQLiteOpenHelper
{
    private static final int VERSAO_BANCO = 1;
    private static final String DATABASE = "localhost_restaurante";

    public BancoDados(Context context)
    {
        super(context, DATABASE, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Vector<String>script = new Vector<>();

        script.add("CREATE TABLE Cartao_credito(id INTEGER PRIMARY KEY, numero INTEGER, codSeguranca INTEGER, " +
                "bandeira INTEGER, nomeTitular TEXT)");
        script.add("CREATE TABLE Endereco(id INTEGER PRIMARY KEY, cep TEXT, endereco TEXT, numero INTEGER, " +
                "complemento TEXT, cidade TEXT, estado TEXT)");
        script.add("CREATE TABLE Cliente(id INTEGER PRIMARY KEY, nomeComp TEXT, cpf TEXT, email TEXT, " +
                "login TEXT, senha TEXT, dataNasc TEXT, sexo TEXT, celular TEXT, Cartao_credito_id INTEGER, " +
                "Endereco_id INTEGER, " +
                "FOREIGN KEY (Cartao_credito_id) REFERENCES Cartao_credito(id) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY (Endereco_id) REFERENCES Endereco(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Comanda(id INTEGER, Cliente_id INTEGER, status INTEGER," +
                "PRIMARY KEY(id, Cliente_id)," +
                "FOREIGN KEY (Cliente_id) REFERENCES Cliente(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Pedido(id INTEGER, status INTEGER, nota INTEGER, reclamacao TEXT," +
                "Comanda_id INTEGER, Comanda_Cliente_id INTEGER," +
                "PRIMARY KEY(id, Comanda_id, Comanda_Cliente_id)," +
                "FOREIGN KEY(Comanda_id, Comanda_Cliente_id) REFERENCES Comanda(id, Cliente_id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Cardapio(id INTEGER PRIMARY KEY)");
        script.add("CREATE TABLE Item_Cardapio(id INTEGER, nome TEXT, descricao TEXT, valor DOUBLE," +
                "ingredientes TEXT, tipo INTEGER, isVisible INTEGER, Cardapio_id INTEGER," +
                "PRIMARY KEY(id, Cardapio_id)," +
                "FOREIGN KEY(Cardapio_id) REFERENCES Cardapio(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Item_Pedido(Pedido_id INTEGER, Pedido_Comanda_id INTEGER, " +
                "Pedido_Comanda_Cliente_id INTEGER, status INTEGER, Item_Cardapio_id INTEGER," +
                "PRIMARY KEY(Pedido_id, Pedido_Comanda_id, Pedido_Comanda_Cliente_id, Item_Cardapio_id)," +
                "FOREIGN KEY(Pedido_id, Pedido_Comanda_id, Pedido_Comanda_Cliente_id) REFERENCES Pedido(id, Comanda_id, Comanda_Cliente_id) " +
                "ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY(Item_Cardapio_id) REFERENCES Item_Cardapio(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Restaurante(id INTEGER PRIMARY KEY, nome TEXT, razao_social TEXT, cnpj INTEGER," +
                "login TEXT, senha TEXT, email TEXT, Endereco_id INTEGER, Cardapio_id INTEGER," +
                "FOREIGN KEY(Endereco_id) REFERENCES Endereco(id) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY(Cardapio_id) REFERENCES Cardapio(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Mesa(id INTEGER, isOpen INTEGER, Restaurante_id INTEGER," +
                "PRIMARY KEY(id, Restaurante_id)," +
                "FOREIGN KEY(Restaurante_id) REFERENCES Restaurante(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        int i;
        for(i=0; i<script.size(); i++)
        {
            System.out.println(script.get(i));
            db.execSQL(script.get(i));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {

    }

    public void addCartaoCredito(CartaoCredito cartao)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();

        System.out.println("Mensagem de teste 1");

        value.put("numero", cartao.getNumero());
        value.put("codSeguranca", cartao.getCodSeguranca());
        value.put("bandeira", cartao.getBandeira());
        value.put("nomeTitular", cartao.getNomeTitular());

        db.insert("Cartao_Credito", null, value);
        db.close();
    }
}
