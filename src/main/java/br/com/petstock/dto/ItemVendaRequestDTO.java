package br.com.petstock.dto;

/*
 * DTO responsável por receber cada item do carrinho enviado pelo JavaScript.
 * 
 * Ele não representa diretamente uma tabela do banco.
 * Serve apenas para transportar dados da tela de venda para o backend.
 */
public class ItemVendaRequestDTO {

    /*
     * ID do produto selecionado no carrinho.
     */
    private int produtoId;

    /*
     * Quantidade escolhida para venda.
     */
    private int quantidade;

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}