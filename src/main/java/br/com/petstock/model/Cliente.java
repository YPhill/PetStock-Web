package br.com.petstock.model;

// Importações do JPA
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/*
 * Entidade Cliente
 * Representa a tabela "cliente" do banco de dados
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    /*
     * Chave primária da tabela cliente
     * AUTO_INCREMENT no MySQL
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente")
    private int idCliente;

    /*
     * Nome do cliente
     */
    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    /*
     * Relacionamento com Endereco
     * Cliente possui um endereço
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Endereco")
    private Endereco endereco;

    /*
     * Relacionamento com ContatoCliente
     * Cliente possui um contato
     */
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private ContatoCliente contato;

    /*
     * Construtor padrão
     * Inicializa objetos para evitar NullPointerException
     */
    public Cliente() {
        this.endereco = new Endereco();
        this.contato = new ContatoCliente();
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ContatoCliente getContato() {
        return contato;
    }

    public void setContato(ContatoCliente contato) {
        this.contato = contato;
    }
}