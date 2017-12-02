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

    public ItemPedido (int idCardapio, String nome, String desc, double valor, String ingred, int tipo, boolean isVisible,
                       int idItemPedido, int status)
    {
        super(idCardapio, nome, desc, valor, ingred, tipo, isVisible);
        setId(idItemPedido);
        setStatus(status);
    }

    public ItemPedido(int idCardapio, String nome, String desc, double valor, String ingred, int tipo, boolean isVisible)
    {
        super(idCardapio, nome, desc, valor, ingred, tipo, isVisible);
        setId(-1);
        setStatus(0);
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

    public void setStatus(int status) {
        this.status = status;
    }
}
