package bancodados;

import android.database.Cursor;

import Tools.StringParser;
import model.Cardapio;
import model.ItemCardapio;
import model.Mesa;
import model.Restaurante;

/**
 * Created by lucas on 01/12/17.
 * Classe que trata as conexões da classe Restaurante com o banco de dados
 */

public class RestauranteDAO
{
    private BancoDados bd;
    private EnderecoDAO endDAO;
    private MesaDAO mesaDAO;
    private CardapioDAO cardapioDAO;

    public RestauranteDAO(BancoDados bd)
    {
        System.out.println("Construtor da classe RestauranteDAO");
        this.bd = bd;
        setEndDAO(new EnderecoDAO(bd));
        setMesaDAO(new MesaDAO(bd));
        setCardapioDAO(new CardapioDAO(bd));
    }

    public int inserirRestaurante(Restaurante rest)
    {
        //Insere o endereço do restaurante
        int endId = endDAO.inserirEndereco(rest.getEnd());

        String query = "INSERT INTO Restaurante(nome, razao_social, cnpj, login, senha, email, telefone, Endereco_id)" +
                "VALUES(" + StringParser.getAspas(rest.getNome()) + ", " + StringParser.getAspas(rest.getRazaoSocial()) + ", " +
                StringParser.getAspas(rest.getCnpj()) + ", " + StringParser.getAspas(rest.getLogin()) + ", " +
                StringParser.getAspas(rest.getSenha()) + ", " + StringParser.getAspas(rest.getEmail()) + ", " +
                StringParser.getAspas(rest.getTelefone()) + ", " + endId + ")";
        return bd.insertQuery(query);
    }

    public Restaurante pesquisarRestauranteId(int id)
    {
        String query = "SELECT id, nome, razao_social, cnpj, login, senha, email, telefone, Endereco_id " +
                "FROM Restaurante " +
                "WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor.moveToFirst())
        {
            return new Restaurante(id, cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("razao_social")),
                    cursor.getString(cursor.getColumnIndex("cnpj")),
                    cursor.getString(cursor.getColumnIndex("login")),
                    cursor.getString(cursor.getColumnIndex("senha")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    cursor.getString(cursor.getColumnIndex("telefone")),
                    mesaDAO.pesquisarTodasMesasRestaurante(id),
                    cardapioDAO.pesquisarCardapioRestaurante(id),
                    endDAO.pesquisarEnderecoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Endereco_id")))));
        }
        return  null;
    }

    public BancoDados getBd() {
        return bd;
    }

    public void setBd(BancoDados bd) {
        this.bd = bd;
    }

    public EnderecoDAO getEndDAO() {
        return endDAO;
    }

    public void setEndDAO(EnderecoDAO endDAO) {
        this.endDAO = endDAO;
    }

    public MesaDAO getMesaDAO() {
        return mesaDAO;
    }

    public void setMesaDAO(MesaDAO mesaDAO) {
        this.mesaDAO = mesaDAO;
    }

    public CardapioDAO getCardapioDAO() {
        return cardapioDAO;
    }

    public void setCardapioDAO(CardapioDAO cardapioDAO) {
        this.cardapioDAO = cardapioDAO;
    }
}
