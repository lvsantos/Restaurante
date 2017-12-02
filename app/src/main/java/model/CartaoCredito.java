package model;

/**
 * Created by lucas on 29/11/17.
 * Armazena dados relacionados aos cartões de crédito
 */

public class CartaoCredito
{
    private int id;
    private String numero;
    private int codSeguranca;
    private int bandeira;
    private String nomeTitular;

    public CartaoCredito(int id, String numero, int codSeguranca, int bandeira, String nomeTitular)
    {
        System.out.println("\nConstrutor da classe CartaoCredito");
        setId(id);
        setNumero(numero);
        setCodSeguranca(codSeguranca);
        setBandeira(bandeira);
        setNomeTitular(nomeTitular);
    }

    public CartaoCredito(String numero, int codSeguranca, int bandeira, String nomeTitular)
    {
        setId(-1);
        setNumero(numero);
        setCodSeguranca(codSeguranca);
        setBandeira(bandeira);
        setNomeTitular(nomeTitular);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getCodSeguranca() {
        return codSeguranca;
    }

    public void setCodSeguranca(int codSeguranca) {
        this.codSeguranca = codSeguranca;
    }

    public int getBandeira() {
        return bandeira;
    }

    public void setBandeira(int bandeira) {
        this.bandeira = bandeira;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }
}
