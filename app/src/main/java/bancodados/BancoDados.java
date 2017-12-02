package bancodados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

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
        System.out.println("Construtor da classe BancoDados");
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Vector<String>script = new Vector<>();

        System.out.println("Método OnCreate() de BancoDados");

        script.add("CREATE TABLE Cartao_credito(id INTEGER NOT NULL PRIMARY KEY, numero TEXT NOT NULL, " +
                "codSeguranca INTEGER NOT NULL, bandeira INTEGER NOT NULL, nomeTitular TEXT NOT NULL)");

        script.add("CREATE TABLE Endereco(id INTEGER  NOT NULL PRIMARY KEY, cep TEXT NOT NULL, rua TEXT NOT NULL, " +
                "numero INTEGER NOT NULL, " + "complemento TEXT NOT NULL , cidade TEXT NOT NULL, estado TEXT NOT NULL)");

        script.add("CREATE TABLE Cliente(id INTEGER  NOT NULL PRIMARY KEY, nomeComp TEXT NOT NULL, cpf TEXT NOT NULL, " +
                "email TEXT NOT NULL, " + "login TEXT NOT NULL, senha TEXT NOT NULL, dataNasc TEXT NOT NULL, " +
                "sexo TEXT NOT NULL, celular TEXT NOT NULL, Cartao_credito_id INTEGER NOT NULL, Endereco_id INTEGER NOT NULL," +
                "FOREIGN KEY(Cartao_credito_id) REFERENCES Cartao_credito(id) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY(Endereco_id) REFERENCES Endereco(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");

        script.add("CREATE TABLE Comanda(id INTEGER NOT NULL PRIMARY KEY, Cliente_id INTEGER NOT NULL, " +
                "status INTEGER NOT NULL, Mesa_id INTEGER NOT NULL," +
                "FOREIGN KEY(Cliente_id) REFERENCES Cliente(id) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY(Mesa_id) REFERENCES Mesa(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");

        script.add("CREATE TABLE Pedido(id INTEGER NOT NULL PRIMARY KEY, status INTEGER NOT NULL, nota INTEGER, " +
                "reclamacao TEXT," + "Comanda_id INTEGER NOT NULL," +
                "FOREIGN KEY(Comanda_id) REFERENCES Comanda(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");

        script.add("CREATE TABLE Restaurante(id INTEGER NOT NULL PRIMARY KEY, nome TEXT NOT NULL, razao_social TEXT NOT NULL, " +
                "cnpj TEXT NOT NULL," + "login TEXT NOT NULL, senha TEXT NOT NULL, email TEXT NOT NULL, telefone TEXT NOT NULL, " +
                "Endereco_id INTEGER NOT NULL," +
                "FOREIGN KEY(Endereco_id) REFERENCES Endereco(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");

        script.add("CREATE TABLE Cardapio(id INTEGER NOT NULL PRIMARY KEY, Restaurante_id INTEGER NOT NULL," +
                "FOREIGN KEY(RESTAURANTE_ID) REFERENCES RESTAURANTE(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");

        script.add("CREATE TABLE Item_cardapio(id INTEGER NOT NULL PRIMARY KEY, nome TEXT NOT NULL, descricao TEXT NOT NULL, " +
                "valor DOUBLE NOT NULL," + "ingredientes TEXT NOT NULL, tipo INTEGER NOT NULL, isVisible INTEGER NOT NULL, " +
                "Cardapio_id INTEGER NOT NULL," +
                "FOREIGN KEY(Cardapio_id) REFERENCES Cardapio(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");

        script.add("CREATE TABLE Item_Pedido(id INTEGER NOT NULL PRIMARY KEY, Pedido_id INTEGER NOT NULL, " +
                "status INTEGER NOT NULL, Item_cardapio_id INTEGER NOT NULL," +
                "FOREIGN KEY(Pedido_id) REFERENCES Pedido(id) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY(Item_cardapio_id) REFERENCES Item_cardapio(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");

        script.add("CREATE TABLE Mesa(id INTEGER NOT NULL PRIMARY KEY, Restaurante_id INTEGER NOT NULL,"
                + "FOREIGN KEY(Restaurante_id) REFERENCES Restaurante(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        int i;
        for(i=0; i<script.size(); i++)
        {
            System.out.println(i);
            System.out.println(script.get(i));
            db.execSQL(script.get(i));
        }
        System.out.println("Banco de dados criado com sucesso");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {

    }

    /**
     * Executa uma query que vai inserir um dado na tabela
     * @param query: Comando para inserir um dado na tabela
     */
    public int insertQuery(String query)
    {
        System.out.println("Método insertQuery() de BancoDados");
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(query);
        db.execSQL(query);
        int lastId = lastInsertId();
        db.close();
        return lastId;
    }

    private int lastInsertId()
    {
        String query = "SELECT last_insert_rowid() as lastId";
        Cursor cursor = selectQuery(query);

        if(cursor.moveToFirst())
        {
            return Integer.parseInt(cursor.getString(0));
            //return Integer.parseInt(cursor.getString(cursor.getColumnIndex("lastId")));
        }
        cursor.close();
        return -1;
    }

    /**
     * Executa uma query que vai retornar um conjunto de dados da tabela
     * @param query: Comando para pesquisar na tabela
     */
    public Cursor selectQuery(String query)
    {
        System.out.println("Método selectQuery do BancoDados");
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println(query);
        Cursor cursor = db.rawQuery(query, null);
        //db.close();
        return cursor;
    }
}
