package br.com.petstock.dto;

import java.util.List;

/*
 * DTO responsável por receber os dados da venda enviados pelo JavaScript.
 * 
 * Contém o cliente selecionado e a lista de itens do carrinho.
 */
public class VendaRequestDTO {

    /*
     * ID do cliente selecionado.
     * Pode ser null quando a venda for feita sem cliente.
     */
    private Integer clienteId;

    /*
     * Lista de itens adicionados ao carrinho.
     */
    private List<ItemVendaRequestDTO> itens;

    // =========================
    // GETTERS E SETTERS
    // =========================

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public List<ItemVendaRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaRequestDTO> itens) {
        this.itens = itens;
    }
}