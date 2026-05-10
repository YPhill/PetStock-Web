package br.com.petstock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
 * Entidade ContatoFornecedor
 * Representa a tabela "contato_fornecedor" do banco de dados.
 */
@Entity
@Table(name = "contato_fornecedor")
public class ContatoFornecedor {

    /*
     * Chave primária da tabela contato_fornecedor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Contato")
    private int idContato;

    /*
     * Telefone fixo do fornecedor.
     */
    @Column(name = "Telefone", length = 15)
    private String telefone;

    /*
     * Celular do fornecedor.
     */
    @Column(name = "Celular", length = 15)
    private String celular;

    /*
     * E-mail do fornecedor.
     */
    @Column(name = "Email", length = 100)
    private String email;

    /*
     * Fornecedor dono deste contato.
     * A chave estrangeira está na coluna ID_Fornecedor.
     */
    @OneToOne
    @JoinColumn(name = "ID_Fornecedor")
    private Fornecedor fornecedor;

    /*
     * Construtor vazio exigido pelo JPA.
     */
    public ContatoFornecedor() {
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}