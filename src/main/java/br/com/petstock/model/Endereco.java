package br.com.petstock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * Entidade Endereco
 * Representa a tabela "endereco" do banco de dados.
 */
@Entity
@Table(name = "endereco")
public class Endereco {

    /*
     * Chave primária da tabela endereco.
     * No banco está como AUTO_INCREMENT.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Endereco")
    private int idEndereco;

    /*
     * Rua do endereço.
     */
    @Column(name = "Rua", nullable = false, length = 100)
    private String rua;

    /*
     * Cidade do endereço.
     */
    @Column(name = "Cidade", nullable = false, length = 50)
    private String cidade;

    /*
     * Estado do endereço.
     */
    @Column(name = "Estado", nullable = false, length = 50)
    private String estado;

    /*
     * CEP do endereço.
     */
    @Column(name = "CEP", nullable = false, length = 10)
    private String cep;

    /*
     * Construtor vazio.
     * O JPA precisa dele para criar objetos automaticamente.
     */
    public Endereco() {
    }

    /*
     * Construtor completo.
     * Útil quando quisermos criar um endereço já com todos os dados.
     */
    public Endereco(int idEndereco, String rua, String cidade, String estado, String cep) {
        this.idEndereco = idEndereco;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}