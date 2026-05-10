package br.com.petstock.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * Entidade ItensVenda
 * Representa a tabela "itens_venda" do banco de dados.
 */
@Entity
@Table(name = "itens_venda")
public class ItensVenda {

    /*
     * Chave primária da tabela itens_venda.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Itens_venda")
    private int idItensVenda;

    /*
     * Venda à qual este item pertence.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Venda")
    private Venda venda;

    /*
     * Produto vendido neste item.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Produto")
    private Produto produto;

    /*
     * Quantidade vendida do produto.
     */
    @Column(name = "Quantidade", nullable = false)
    private int quantidade;

    /*
     * Preço unitário do produto no momento da venda.
     */
    @Column(name = "Preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;

    /*
     * Construtor vazio exigido pelo JPA.
     */
    public ItensVenda() {
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getIdItensVenda() {
        return idItensVenda;
    }

    public void setIdItensVenda(int idItensVenda) {
        this.idItensVenda = idItensVenda;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}