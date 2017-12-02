package bancodados;

import android.database.Cursor;

import Tools.StringParser;
import model.CartaoCredito;
import model.Cliente;
import model.Endereco;

/**
 * Created by lucas on 29/11/17.
 * Classe que trata as conexões da classe Cliente com o banco de dados
 */

public class ClienteDAO
{
    private BancoDados bd;
    private EnderecoDAO endDAO;
    private CartaoCreditoDAO cartaoDAO;
    private ComandaDAO comandaDAO;

    public ClienteDAO(BancoDados bd)
    {
        System.out.println("Construtor da classe ClienteDAO");
        setBd(bd);
        setEndDAO(new EnderecoDAO(bd));
        setCartaoDAO(new CartaoCreditoDAO(bd));
        setComandaDAO(new ComandaDAO(bd));
    }

    public int inserirCliente(Cliente cli)
    {
        System.out.println("Método InserirCliente() de ClienteDAO");

        //Insere o endereço do cliente
        int endId = endDAO.inserirEndereco(cli.getEndereco());

        //Insere o cartão de crédito do cliente
        int cartaoId = cartaoDAO.inserirCartaoCredito(cli.getCartaoCred());

        //Insere o cliente
        String query = "INSERT INTO Cliente(nomeComp, cpf, email, login, senha, dataNasc, " +
                "sexo, celular, Cartao_credito_id, Endereco_id)" +
                "VALUES(" + StringParser.getAspas(cli.getNomeComp()) + ", " + StringParser.getAspas(cli.getCpf()) + ", " +
                StringParser.getAspas(cli.getEmail()) + ", " + StringParser.getAspas(cli.getLogin()) + ", " +
                StringParser.getAspas(cli.getSenha()) + ", " + StringParser.getAspas(cli.getDataNasc()) + ", " +
                StringParser.getAspas(String.valueOf(cli.getSexo())) + ", " + StringParser.getAspas(cli.getCelular()) + "," +
                cartaoId + ", " + endId + ")";
        System.out.println("Cliente inserido com sucesso");
        return bd.insertQuery(query);
    }

    public Cliente pesquisarClienteId(int id)
    {
        String query = "SELECT nomeComp, cpf, email, login, senha, dataNasc, sexo, celular, Cartao_credito_id, Endereco_id " +
                "FROM Cliente " +
                "WHERE id = " + id;

        Cursor cursor = bd.selectQuery(query);

        if(cursor.moveToFirst())
        {
            return new Cliente(
                    id,
                    cursor.getString(cursor.getColumnIndex("nomeComp")),
                    cursor.getString(cursor.getColumnIndex("cpf")),
                    cursor.getString(cursor.getColumnIndex("login")),
                    cursor.getString(cursor.getColumnIndex("senha")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    endDAO.pesquisarEnderecoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Endereco_id")))),
                    cursor.getString(cursor.getColumnIndex("dataNasc")),
                    cursor.getString(cursor.getColumnIndex("sexo")).charAt(0),
                    cursor.getString(cursor.getColumnIndex("celular")),
                    cartaoDAO.pesquisarCartaoCreditoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Cartao_credito_id")))),
                    comandaDAO.pesquisarComandaAbertaCliente(id));
        }
        return  null;
    }

    public Cliente pesquisarClienteLogin(String login)
    {
        String query = "SELECT id, nomeComp, cpf, email, senha, dataNasc, sexo, celular, Cartao_credito_id, Endereco_id " +
                "FROM Cliente " +
                "WHERE login = " + StringParser.getAspas(login);

        Cursor cursor = bd.selectQuery(query);

        if(cursor.moveToFirst())
        {
            return new Cliente(
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))),
                    cursor.getString(cursor.getColumnIndex("nomeComp")),
                    cursor.getString(cursor.getColumnIndex("cpf")),
                    login,
                    cursor.getString(cursor.getColumnIndex("senha")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    endDAO.pesquisarEnderecoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Endereco_id")))),
                    cursor.getString(cursor.getColumnIndex("dataNasc")),
                    cursor.getString(cursor.getColumnIndex("sexo")).charAt(0),
                    cursor.getString(cursor.getColumnIndex("celular")),
                    cartaoDAO.pesquisarCartaoCreditoId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("Cartao_credito_id")))),
                    comandaDAO.pesquisarComandaAbertaCliente(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")))));
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

    public CartaoCreditoDAO getCartaoDAO() {
        return cartaoDAO;
    }

    public void setCartaoDAO(CartaoCreditoDAO cartaoDAO) {
        this.cartaoDAO = cartaoDAO;
    }

    public ComandaDAO getComandaDAO() {
        return comandaDAO;
    }

    public void setComandaDAO(ComandaDAO comandaDAO) {
        this.comandaDAO = comandaDAO;
    }
}
