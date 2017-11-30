package bancodados;

import android.database.Cursor;

import Tools.StringParser;
import model.CartaoCredito;
import model.Cliente;
import model.Endereco;

/**
 * Created by lucas on 29/11/17.
 * Classe que trata as conexões com o banco de dados
 */

public class ClienteDAO
{
    private BancoDados bd;

    public ClienteDAO(BancoDados bd)
    {
        //setBd(/*new BancoDados(this)*/);
        this.bd = bd;
        System.out.println("Construtor da classe ClienteDAO");
    }

    private int inserirCartaoCredito(CartaoCredito cartao)
    {
        System.out.println("Método InserirCartaoCredito() de ClienteDAO");
        String query = "INSERT INTO Cartao_credito(numero, codSeguranca, bandeira, nomeTitular)"
                + "VALUES(" + cartao.getNumero() + ", " + cartao.getCodSeguranca() + ", " +
                cartao.getBandeira() + ", " + StringParser.getAspas(cartao.getNomeTitular()) + ")";
        return bd.insertQuery(query);
    }

    private int inserirEndereco(Endereco end)
    {
        System.out.println("Método InserirEndereço() de ClienteDAO");
        String query = "INSERT INTO Endereco(cep, rua, numero, complemento, cidade, estado)"
                + "VALUES(" + StringParser.getAspas(end.getCep()) + ", " + StringParser.getAspas(end.getRua()) + ", "
                + end.getNum() + ", " + StringParser.getAspas(end.getComplemento()) + ", " + StringParser.getAspas(end.getCidade())
                + "," + StringParser.getAspas(end.getEstado()) + ")";
        return bd.insertQuery(query);
    }

    public int inserirCliente(Cliente cli)
    {
        System.out.println("Método InserirCliente() de ClienteDAO");

        //Insere o endereço do cliente
        int endId = inserirEndereco(cli.getEndereco());

        //Insere o cartão de crédito do cliente
        int cartaoId = inserirCartaoCredito(cli.getCartaoCred());

        //Insere o cliente
        String query = "INSERT INTO Cliente(nomeComp, cpf, email, login, senha, dataNasc, " +
                "sexo, celular, Cartao_credito_id, Endereco_id)" +
                "VALUES(" + StringParser.getAspas(cli.getNomeComp()) + ", " + cli.getCpf() + ", " +
                StringParser.getAspas(cli.getEmail()) + ", " + StringParser.getAspas(cli.getLogin()) + ", " +
                StringParser.getAspas(cli.getSenha()) + ", " + StringParser.getAspas(cli.getDataNasc()) + ", " +
                StringParser.getAspas(String.valueOf(cli.getSexo())) + ", " + StringParser.getAspas(cli.getCelular()) + "," +
                cartaoId + ", " + endId + ")";
        System.out.println("Cliente inserido com sucesso");
        return bd.insertQuery(query);
    }

    /*
    public Cliente pesquisarClienteId(int id)
    {
        String query = "SELECT nomeComp, cpf, email, login, senha, dataNasc, sexo, celular," +
                "Endereco.id as idEnd, cep, rua, num, complemento, cidade, estado," +
                "Cartao_credito.id as idCartao, numero, codSeguranca, bandeira, nomeTitular" +
                "FROM Cliente" +
                "INNER JOIN Endereco" +
                "  ON Cliente.id = Endereco.Cliente.id" +
                "INNER JOIN Cartao_credito" +
                "  ON Cliente.id = Cartao_credito.Cliente_id" +
                "WHERE id = " + id;
        Cursor cursor = bd.selectQuery(query);

        if(cursor.moveToFirst())
        {
            Endereco end = new Endereco(Integer.parseInt(cursor.getString(cursor.getColumnIndex("idEnd"))), cursor.getString(cursor.getColumnIndex("cep")),
                                        cursor.getString(cursor.getColumnIndex("rua")), Integer.parseInt(cursor.getString(cursor.getColumnIndex("num"))),
                                        cursor.getString(cursor.getColumnIndex("complemento")), cursor.getString(cursor.getColumnIndex("cidade")),
                                        cursor.getString(cursor.getColumnIndex("estado"))
                                        );
            CartaoCredito cartao = new CartaoCredito(Integer.parseInt(cursor.getString(cursor.getColumnIndex("idCartao"))),
                                                     Integer.parseInt(cursor.getString(cursor.getColumnIndex("numero"))),
                                                     Integer.parseInt(cursor.getString(cursor.getColumnIndex("codSeguranca"))),
                                                     Integer.parseInt(cursor.getString(cursor.getColumnIndex("bandeira"))),
                                                     cursor.getString(cursor.getColumnIndex("nomeTitular")));
            return new Cliente(id,
                    cursor.getString(cursor.getColumnIndex("nomeComp")),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("cpf"))),
                    cursor.getString(cursor.getColumnIndex("login")),
                    cursor.getString(cursor.getColumnIndex("senha")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    end,
                    cursor.getString(cursor.getColumnIndex("dataNasc")),
                    cursor.getString(cursor.getColumnIndex("sexo")).charAt(0),
                    cursor.getString(cursor.getColumnIndex("celular")),
                    cartao);
        }
        return  null;
    }*/

    public Cliente pesquisarClienteLogin(String login)
    {
        String query = "SELECT cli.id as idCli, nomeComp, cpf, email, senha, dataNasc, sexo, celular, " +
                "end.id as idEnd, cep, rua, end.numero as numEnd, complemento, cidade," +
                " " + "estado, " +
                "car.id as idCar, car.numero as numCar, codSeguranca, " + "bandeira, nomeTitular"
                + " FROM Cliente cli" +
                " INNER JOIN Endereco end" +
                " ON cli.Endereco_id = end.id" +
                " INNER JOIN Cartao_credito car" +
                " ON cli.Cartao_credito_id = car.id" +
                " WHERE login = \"" + login + "\"";
        Cursor cursor = bd.selectQuery(query);

        if(cursor.moveToFirst())
        {
            System.out.println("Aqui foi 0");
            Endereco end = new Endereco(
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("idEnd"))),
                    cursor.getString(cursor.getColumnIndex("cep")),
                    cursor.getString(cursor.getColumnIndex("rua")),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("numEnd"))),
                    cursor.getString(cursor.getColumnIndex("complemento")),
                    cursor.getString(cursor.getColumnIndex("cidade")),
                    cursor.getString(cursor.getColumnIndex("estado"))
            );
            System.out.println("Aqui foi 1");
            System.out.printf("Endereco: %s", end.getRua());
            System.out.printf("Posicao: %d", cursor.getPosition());
            System.out.printf("Posicao: %d", cursor.getPosition());
            System.out.println("Aqui foi 2");
            int i =  Integer.parseInt(cursor.getString(cursor.getColumnIndex("idCar")));
            int q = Integer.parseInt(cursor.getString(cursor.getColumnIndex("numCar")));
            int t = Integer.parseInt(cursor.getString(cursor.getColumnIndex("codSeguranca")));
            /*Integer.parseInt(cursor.getString(cursor.getColumnIndex("bandeira")));
            cursor.getString(cursor.getColumnIndex("nomeTitular"));*/
            /*CartaoCredito cartao = new CartaoCredito(
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("idCar"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("numCar"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("codSeguranca"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("bandeira"))),
                    cursor.getString(cursor.getColumnIndex("nomeTitular")));
            System.out.printf("Cartao: %s", cartao.getNomeTitular());*/
            /*return new Cliente(
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("idCli"))),
                    cursor.getString(cursor.getColumnIndex("nomeComp")),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("cpf"))),
                    cursor.getString(cursor.getColumnIndex(login)),
                    cursor.getString(cursor.getColumnIndex("senha")),
                    cursor.getString(cursor.getColumnIndex("email")),
                    end,
                    cursor.getString(cursor.getColumnIndex("dataNasc")),
                    cursor.getString(cursor.getColumnIndex("sexo")).charAt(0),
                    cursor.getString(cursor.getColumnIndex("celular")),
                    cartao);*/return null;
        }
        return  null;
    }

    public BancoDados getBd() {
        return bd;
    }

    public void setBd() {
        this.bd = bd;
    }
}
