package br.com.petstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petstock.model.Estoque;

/*
 * Repository responsável pelo acesso aos dados de Estoque.
 * 
 * O ID do estoque é o mesmo ID do produto,
 * por isso usamos Integer como tipo da chave.
 */
@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

}