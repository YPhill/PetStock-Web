package br.com.petstock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petstock.model.Cliente;

/*
 * Repository responsável pelo acesso aos dados de Cliente.
 * 
 * Substitui boa parte do antigo ClienteDAO.
 * O JpaRepository já fornece métodos prontos como:
 * save(), findAll(), findById() e deleteById().
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}