package model;

/**
 * Created by lucas on 29/11/17.
 * Classe que armazena todos os dados de Endereço de um cliente
 */

public class Endereco
{
    private int id;
    private String cep;
    private String rua;
    private int num;
    private String complemento;
    private String cidade;
    private String estado;

    public Endereco(int id, String cep, String rua, int num, String complemento, String cidade, String estado)
    {
        setId(id);
        setCep(cep);
        setRua(rua);
        setNum(num);
        setComplemento(complemento);
        setCidade(cidade);
        setEstado(estado);
    }

    public Endereco(String cep, String rua, int num, String complemento, String cidade, String estado)
    {
        setId(-1);
        setCep(cep);
        setRua(rua);
        setNum(num);
        setComplemento(complemento);
        setCidade(cidade);
        setEstado(estado);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
