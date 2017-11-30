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

        script.add("CREATE TABLE Cartao_credito(id NOT NULL INTEGER PRIMARY KEY, numero NOT NULL INTEGER, " +
                "codSeguranca NOT NULL INTEGER, bandeira INTEGER, nomeTitular TEXT)");
        script.add("CREATE TABLE Endereco(id NOT NULL INTEGER PRIMARY KEY, cep NOT NULL TEXT, rua NOT NULL TEXT, " +
                "numero NOT NULL INTEGER, " + "complemento TEXT, cidade NOT NULL TEXT, estado NOT NULL TEXT)");
        script.add("CREATE TABLE Cliente(id NOT NULL INTEGER PRIMARY KEY, nomeComp NOT NULL TEXT, cpf NOT NULL TEXT, " +
                "email NOT NULL TEXT, " + "login NOT NULL TEXT, senha NOT NULL TEXT, dataNasc NOT NULL TEXT, " +
                "sexo NOT NULL TEXT, celular NOT NULL TEXT, Cartao_credito_id NOT NULL INTEGER, Endereco_id NOT NULL INTEGER," +
                "FOREIGN KEY(Cartao_credito_id) REFERENCES Cartao_credito(id) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY(Endereco_id) REFERENCES Endereco(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Comanda(id NOT NULL INTEGER PRIMARY KEY, Cliente_id NOT NULL INTEGER, " +
                "status NOT NULL INTEGER," +
                "FOREIGN KEY(Cliente_id) REFERENCES Cliente(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Pedido(id NOT NULL INTEGER PRIMARY KEY, status NOT NULL INTEGER, nota INTEGER, " +
                "reclamacao TEXT," + "Comanda_id NOT NULL INTEGER," +
                "FOREIGN KEY(Comanda_id) REFERENCES Comanda(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Cardapio(id NOT NULL INTEGER PRIMARY KEY)");
        script.add("CREATE TABLE Item_cardapio(id NOT NULL INTEGER PRIMARY KEY, nome NOT NULL TEXT, descricao NOT NULL TEXT, " +
                "valor NOT NULL DOUBLE," + "ingredientes NOT NULL TEXT, tipo NOT NULL INTEGER, isVisible NOT NULL INTEGER, " +
                "Cardapio_id NOT NULL INTEGER," +
                "FOREIGN KEY(Cardapio_id) REFERENCES Cardapio(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Item_Pedido(id NOT NULL INTEGER PRIMARY KEY, Pedido_id NOT NULL INTEGER, " +
                "status NOT NULL INTEGER, Item_cardapio_id NOT NULL INTEGER," +
                "FOREIGN KEY(Pedido_id) REFERENCES Pedido(id) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY(Item_cardapio_id) REFERENCES Item_cardapio(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Restaurante(id NOT NULL INTEGER PRIMARY KEY, nome NOT NULL TEXT, razao_social NOT NULL TEXT, " +
                "cnpj NOT NULL INTEGER," + "login NOT NULL TEXT, senha NOT NULL TEXT, email NOT NULL TEXT, " +
                "Endereco_id NOT NULL INTEGER, Cardapio_id NOT NULL INTEGER," +
                "FOREIGN KEY(Endereco_id) REFERENCES Endereco(id) ON DELETE NO ACTION ON UPDATE NO ACTION," +
                "FOREIGN KEY(Cardapio_id) REFERENCES Cardapio(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
        script.add("CREATE TABLE Mesa(id NOT NULL INTEGER PRIMARY KEY, isOpen NOT NULL INTEGER, Restaurante_id NOT NULL INTEGER," +
                "FOREIGN KEY(Restaurante_id) REFERENCES Restaurante(id) ON DELETE NO ACTION ON UPDATE NO ACTION)");
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
