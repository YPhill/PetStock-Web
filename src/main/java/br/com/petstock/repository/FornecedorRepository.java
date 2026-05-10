package br.com.petstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petstock.model.Fornecedor;

/*
 * Repository responsável pelo acesso aos dados de Fornecedor.
 * 
 * O JpaRepository fornece métodos prontos para:
 * salvar, listar, buscar e excluir fornecedores.
 */
@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

}