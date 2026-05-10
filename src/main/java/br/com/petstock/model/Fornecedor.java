package br.com.petstock.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/*
 * Entidade Fornecedor
 * Representa a tabela "fornecedor" do banco de dados.
 */
@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    /*
     * Chave primária da tabela fornecedor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Fornecedor")
    private int idFornecedor;

    /*
     * Nome fantasia do fornecedor.
     */
    @Column(name = "Nome_Fantasia", nullable = false, length = 100)
    private String nomeFantasia;

    /*
     * CNPJ do fornecedor.
     */
    @Column(name = "CNPJ", nullable = false, length = 20)
    private String cnpj;

    /*
     * Contrato social do fornecedor.
     */
    @Column(name = "Contrato_Social", nullable = false, length = 100)
    private String contratoSocial;

    /*
     * Endereço vinculado ao fornecedor.
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_Endereco")
    private Endereco endereco;

    /*
     * Contato vinculado ao fornecedor.
     */
    @OneToOne(mappedBy = "fornecedor", cascade = CascadeType.ALL)
    private ContatoFornecedor contato;

    /*
     * Construtor vazio.
     * Inicializa os objetos para evitar erro de objeto nulo.
     */
    public Fornecedor() {
        this.endereco = new Endereco();
        this.contato = new ContatoFornecedor();
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContratoSocial() {
        return contratoSocial;
    }

    public void setContratoSocial(String contratoSocial) {
        this.contratoSocial = contratoSocial;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ContatoFornecedor getContato() {
        return contato;
    }

    public void setContato(ContatoFornecedor contato) {
        this.contato = contato;
    }
}