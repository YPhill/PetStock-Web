package br.com.petstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petstock.model.Produto;

/*
 * Repository responsável pelo acesso aos dados de Produto.
 * 
 * O JpaRepository fornece métodos automáticos para:
 * salvar, listar, buscar e excluir produtos.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}