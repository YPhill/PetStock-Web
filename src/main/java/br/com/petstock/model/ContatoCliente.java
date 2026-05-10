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
 * Entidade ContatoCliente
 * Representa a tabela "contato_cliente" do banco de dados.
 */
@Entity
@Table(name = "contato_cliente")
public class ContatoCliente {

    /*
     * Chave primária da tabela contato_cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Contato")
    private int idContato;

    /*
     * Telefone fixo do cliente.
     * Campo opcional no banco.
     */
    @Column(name = "Telefone", length = 15)
    private String telefone;

    /*
     * Celular do cliente.
     * Campo opcional no banco.
     */
    @Column(name = "Celular", length = 15)
    private String celular;

    /*
     * E-mail do cliente.
     * Campo opcional no banco.
     */
    @Column(name = "Email", length = 100)
    private String email;

    /*
     * Relacionamento com Cliente.
     * Esta classe possui a chave estrangeira ID_Cliente no banco.
     */
    @OneToOne
    @JoinColumn(name = "ID_Cliente")
    private Cliente cliente;

    /*
     * Construtor vazio.
     * O JPA precisa dele para criar objetos automaticamente.
     */
    public ContatoCliente() {
    }

    /*
     * Construtor completo sem cliente.
     * Útil quando quisermos criar um contato antes de associar ao cliente.
     */
    public ContatoCliente(int idContato, String telefone, String celular, String email) {
        this.idContato = idContato;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}