package model;

import java.util.Vector;

import Tools.StringParser;

/**
 * Created by lucas on 01/12/17.
 * Armazena os dados relativos aos restaurantes
 */

public class Restaurante
{
    private int id;
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String login;
    private String senha;
    private String email;
    private String telefone;
    private Vector<Mesa>mesas;
    private Cardapio cardapio;
    private Endereco end;

    public Restaurante(int id, String nome, String razaoSocial, String cnpj, String login, String senha, String email,
                       String telefone, Vector<Mesa>mesas, Cardapio cardapio, Endereco end)
    {
        setId(id);
        setNome(nome);
        setRazaoSocial(razaoSocial);
        setCnpj(cnpj);
        setLogin(login);
        setSenha(senha);
        setEmail(email);
        setTelefone(telefone);
        setMesas(mesas);
        setCardapio(cardapio);
        setEnd(end);
    }

    public Restaurante(String nome, String razaoSocial, String cnpj, String login, String senha, String email,
                       String telefone, Vector<Mesa>mesas, Cardapio cardapio, Endereco end)
    {
        setId(-1);
        setNome(nome);
        setRazaoSocial(razaoSocial);
        setCnpj(cnpj);
        setLogin(login);
        setSenha(senha);
        setEmail(email);
        setTelefone(telefone);
        setMesas(mesas);
        setCardapio(cardapio);
        setEnd(end);
    }

    public Restaurante(String nome, String razaoSocial, String cnpj, String login, String senha, String email,
                       String telefone, Endereco end)
    {
        setId(-1);
        setNome(nome);
        setRazaoSocial(razaoSocial);
        setCnpj(cnpj);
        setLogin(login);
        setSenha(senha);
        setEmail(email);
        setTelefone(telefone);
        setMesas(new Vector<Mesa>());
        setCardapio(new Cardapio());
        setEnd(end);
    }

    public void addMesa(Mesa mesa)
    {
        mesas.add(mesa);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Vector<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(Vector<Mesa> mesas) {
        this.mesas = mesas;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public Endereco getEnd() {
        return end;
    }

    public void setEnd(Endereco end) {
        this.end = end;
    }
}
