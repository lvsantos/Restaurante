package model;

import java.io.Serializable;

/**
 * Created by lucas on 29/11/17.
 * Classe que trata as interações do cliente
 */

public class Cliente //implements Serializable
{
    private int id;
    private String nomeComp;
    private String cpf;
    private String login;
    private String senha;
    private String email;
    private Endereco endereco;
    private String dataNasc;
    private char sexo;
    private String celular;
    private CartaoCredito cartaoCred;
    private Comanda comandaAberta;
    private Pedido pedidoAberto;

    public Cliente(int id, String nomeComp, String cpf, String login, String senha, String email,
                   Endereco endereco, String dataNasc, char sexo, String celular,
                   CartaoCredito cartaoCred, Comanda comandaAberta)
    {
        setId(id);
        setNomeComp(nomeComp);
        setCpf(cpf);
        setLogin(login);
        setSenha(senha);
        setEmail(email);
        setEndereco(endereco);
        setDataNasc(dataNasc);
        setSexo(sexo);
        setCelular(celular);
        setCartaoCred(cartaoCred);
        setComandaAberta(comandaAberta);
        setPedidoAberto(null);
    }

    public Cliente(String nomeComp, String cpf, String login, String senha, String email,
                   Endereco endereco, String dataNasc, char sexo, String celular,
                   CartaoCredito cartaoCred)
    {
        setId(-1);
        setNomeComp(nomeComp);
        setCpf(cpf);
        setLogin(login);
        setSenha(senha);
        setEmail(email);
        setEndereco(endereco);
        setDataNasc(dataNasc);
        setSexo(sexo);
        setCelular(celular);
        setCartaoCred(cartaoCred);
        setComandaAberta(null);
        setPedidoAberto(null);
    }

    public void abrirComandaIndiv(Mesa mesa)
    {
        comandaAberta = new Comanda(mesa);
    }

    public void addItemPedido(ItemPedido item)
    {
        if(pedidoAberto == null)
        {
            pedidoAberto = new Pedido();
        }
        pedidoAberto.addItemPedido(item);
    }

    //Métodos getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeComp() {
        return nomeComp;
    }

    public void setNomeComp(String nomeComp) {
        this.nomeComp = nomeComp;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Comanda getComandaAberta() {
        return comandaAberta;
    }

    public void setComandaAberta(Comanda comandaAberta) {
        this.comandaAberta = comandaAberta;
    }

    public Pedido getPedidoAberto() {
        return pedidoAberto;
    }

    public void setPedidoAberto(Pedido pedidoAberto) {
        this.pedidoAberto = pedidoAberto;
    }

    public CartaoCredito getCartaoCred() {
        return cartaoCred;
    }

    public void setCartaoCred(CartaoCredito cartaoCred) {
        this.cartaoCred = cartaoCred;
    }
}
