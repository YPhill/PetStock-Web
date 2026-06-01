
package br.com.petstock.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petstock.model.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

	List<Venda> findAllByOrderByIdVendaDesc();

}