package br.com.petstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petstock.model.Venda;

/*
 * Repository responsável pelo acesso aos dados de Venda.
 * 
 * O JpaRepository fornece métodos automáticos para:
 * salvar, listar, buscar e excluir vendas.
 */
@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

}