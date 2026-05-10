package br.com.petstock.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * Entidade Produto
 * Representa a tabela "produto" do banco de dados.
 */
@Entity
@Table(name = "produto")
public class Produto {

    /*
     * Chave primária da tabela produto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Produto")
    private int idProduto;

    /*
     * Nome do produto.
     */
    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    /*
     * Categoria do produto.
     */
    @Column(name = "Categoria", length = 50)
    private String categoria;

    /*
     * Preço do produto.
     * BigDecimal é melhor para valores monetários.
     */
    @Column(name = "Preco", nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    /*
     * Marca do produto.
     */
    @Column(name = "Marca", length = 50)
    private String marca;

    /*
     * Validade do produto.
     */
    @Column(name = "Validade")
    private LocalDate validade;

    /*
     * Fornecedor vinculado ao produto.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Fornecedor")
    private Fornecedor fornecedor;

    /*
     * Estoque vinculado ao produto.
     * Relação 1 para 1.
     */
    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
    private Estoque estoque;

    /*
     * Construtor vazio exigido pelo JPA.
     */
    public Produto() {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }


    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }


    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}