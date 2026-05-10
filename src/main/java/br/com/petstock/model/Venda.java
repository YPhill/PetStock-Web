package br.com.petstock.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
 * Entidade Venda
 * Representa a tabela "venda" do banco de dados.
 */
@Entity
@Table(name = "venda")
public class Venda {

    /*
     * Chave primária da tabela venda.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Venda")
    private int idVenda;

    /*
     * Data em que a venda foi realizada.
     */
    @Column(name = "Data_venda", nullable = false)
    private LocalDate dataVenda;

    /*
     * Valor total da venda.
     */
    @Column(name = "Valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    /*
     * Cliente vinculado à venda.
     * Pode ser nulo, pois algumas vendas podem ser feitas sem cliente cadastrado.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Cliente")
    private Cliente cliente;

    /*
     * Lista de itens pertencentes a esta venda.
     */
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItensVenda> itens = new ArrayList<>();

    /*
     * Construtor vazio exigido pelo JPA.
     */
    public Venda() {
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItensVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItensVenda> itens) {
        this.itens = itens;
    }
}