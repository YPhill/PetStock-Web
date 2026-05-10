package br.com.petstock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
 * Entidade Estoque
 * Representa a tabela "estoque" do banco de dados.
 */
@Entity
@Table(name = "estoque")
public class Estoque {

    /*
     * Chave primária da tabela estoque.
     * Também é chave estrangeira para produto.
     */
    @Id
    @Column(name = "ID_Produto")
    private int idProduto;

    /*
     * Quantidade atual disponível em estoque.
     */
    @Column(name = "Quantidade_Atual", nullable = false)
    private int quantidadeAtual;

    /*
     * Produto vinculado ao estoque.
     * @MapsId indica que o ID do estoque é o mesmo ID do produto.
     */
    @OneToOne
    @MapsId
    @JoinColumn(name = "ID_Produto")
    private Produto produto;

    /*
     * Construtor vazio exigido pelo JPA.
     */
    public Estoque() {
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}