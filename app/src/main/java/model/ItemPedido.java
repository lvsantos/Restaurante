package model;

/**
 * Created by lucas on 29/11/17.
 * Armazena os dados de um item do pedido
 */

public class ItemPedido extends ItemCardapio
{
    private int idPedido;
    /**
     * status = 0: Em andamento, 1: Aprovação pagamento, 2: Pagamento aprovado, 3: Em preparo, 4: Encaminhado, 5: Finalizado
     */
    private int status;
    public static int ANDAMENTO = 0;
    public  static int AGUARDANDO_APROV = 1;
    public static int PGTO_APROV = 2;
    public static int PREPARO = 3;
    public static int ENCAMINHADO = 4;
    public static int FINALIZADO = 5;

    public ItemPedido (int idItemCardap, String nome, String desc, double valor, String ingred, int tipo, boolean isVisible,
                       int idCardapio, int idItemPedido, int status)
    {
        super(idItemCardap, nome, desc, valor, ingred, tipo, isVisible, idCardapio);
        setId(idItemPedido);
        setStatus(status);
    }

    public ItemPedido(int idItemCardap, String nome, String desc, double valor, String ingred, int tipo, boolean isVisible,
                      int idCardapio)
    {
        super(idItemCardap, nome, desc, valor, ingred, tipo, isVisible, idCardapio);
        setId(-1);
        setStatus(ANDAMENTO);
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int id) {
        this.idPedido = id;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusText()
    {
        String retorno = new String();
        if (status == AGUARDANDO_APROV)
        {
            retorno = "Aguardando aprovação do pagamento";
        }
        else if (status == PGTO_APROV)
        {
            retorno = "Pagamento aprovado";
        }
        else if (status == ANDAMENTO)
        {
            retorno = "Pedido em andamento";
        }
        else if (status == ENCAMINHADO)
        {
            retorno = "Pedido encaminhado";
        }
        else if (status == FINALIZADO)
        {
            retorno = "Pedido finalizado";
        }
        else if (status == PREPARO)
        {
            retorno = "Pedido em preparo";
        }
        return retorno;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
