package br.com.petstock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petstock.model.Fornecedor;
import br.com.petstock.repository.FornecedorRepository;

/*
 * Service responsável pelas regras de negócio de Fornecedor.
 */
@Service
public class FornecedorService {

	/*
	 * Injeta automaticamente o FornecedorRepository.
	 */
	@Autowired
	private FornecedorRepository fornecedorRepository;

	/*
	 * Lista todos os fornecedores cadastrados.
	 */
	public List<Fornecedor> listarTodos() {
		return fornecedorRepository.findAll();
	}

	/*
	 * Salva um fornecedor no banco.
	 */
	public Fornecedor salvar(Fornecedor fornecedor) {

		/*
		 * Valida o nome fantasia.
		 */
		if (fornecedor.getNomeFantasia() == null || fornecedor.getNomeFantasia().trim().isEmpty()) {
			throw new RuntimeException("O nome fantasia do fornecedor é obrigatório.");
		}

		/*
		 * Valida o CNPJ.
		 */
		if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().isEmpty()) {
			throw new RuntimeException("O CNPJ do fornecedor é obrigatório.");
		}

		/*
		 * Valida o contrato social.
		 */
		if (fornecedor.getContratoSocial() == null || fornecedor.getContratoSocial().trim().isEmpty()) {
			throw new RuntimeException("O contrato social é obrigatório.");
		}

		/*
		 * Valida se existe endereço.
		 */
		if (fornecedor.getEndereco() == null) {
			throw new RuntimeException("O endereço do fornecedor é obrigatório.");
		}

		/*
		 * Valida rua.
		 */
		if (fornecedor.getEndereco().getRua() == null || fornecedor.getEndereco().getRua().trim().isEmpty()) {
			throw new RuntimeException("A rua é obrigatória.");
		}

		/*
		 * Valida cidade.
		 */
		if (fornecedor.getEndereco().getCidade() == null || fornecedor.getEndereco().getCidade().trim().isEmpty()) {
			throw new RuntimeException("A cidade é obrigatória.");
		}

		/*
		 * Valida estado.
		 */
		if (fornecedor.getEndereco().getEstado() == null || fornecedor.getEndereco().getEstado().trim().isEmpty()) {
			throw new RuntimeException("O estado é obrigatório.");
		}

		/*
		 * Valida CEP.
		 */
		if (fornecedor.getEndereco().getCep() == null || fornecedor.getEndereco().getCep().trim().isEmpty()) {
			throw new RuntimeException("O CEP é obrigatório.");
		}

		/*
		 * Relaciona contato ao fornecedor antes de salvar.
		 */
		if (fornecedor.getContato() != null) {
			fornecedor.getContato().setFornecedor(fornecedor);
		}

		/*
		 * Salva no banco.
		 */
		return fornecedorRepository.save(fornecedor);
	}

	/*
	 * Exclui fornecedor pelo ID.
	 */
	public void excluir(int id) {
		fornecedorRepository.deleteById(id);
	}

	/*
	 * Busca um fornecedor pelo ID. Usado para carregar os dados na tela de edição.
	 */
	public Fornecedor buscarPorId(int id) {

		return fornecedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado."));
	}

	/*
	 * Exclui vários fornecedores selecionados. Usado quando o usuário marca várias
	 * linhas na tabela.
	 */
	public void excluirSelecionados(List<Integer> ids) {

		fornecedorRepository.deleteAllById(ids);
	}
}