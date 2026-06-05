package br.com.petstock.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petstock.model.Estoque;
import br.com.petstock.model.Produto;
import br.com.petstock.repository.ProdutoRepository;
import java.time.LocalDate;

/*
 * Service responsável pelas regras de negócio de Produto.
 */
@Service
public class ProdutoService {

	/*
	 * Injeta automaticamente o ProdutoRepository.
	 */
	@Autowired
	private ProdutoRepository produtoRepository;

	/*
	 * Lista todos os produtos cadastrados.
	 */
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	/*
	 * Salva um produto no banco.
	 */
	public Produto salvar(Produto produto) {

		/*
		 * Valida o nome do produto.
		 */
		if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
			throw new RuntimeException("O nome do produto é obrigatório.");
		}

		/*
		 * Valida se o preço foi preenchido.
		 */
		if (produto.getPreco() == null) {
			throw new RuntimeException("O preço do produto é obrigatório.");
		}

		/*
		 * Valida se o preço é maior que zero.
		 */
		if (produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
			throw new RuntimeException("O preço do produto deve ser maior que zero.");
		}
		
		/*
		 * Valida se a validade não é uma data anterior à data atual.
		 */
		if (produto.getValidade() != null
				&& produto.getValidade().isBefore(LocalDate.now())) {

			throw new RuntimeException("A validade do produto não pode ser anterior à data atual.");
		}

		/*
		 * Se o usuário escolher "Sem fornecedor", o ID chegará como 0. Nesse caso,
		 * removemos o fornecedor do produto.
		 */
		if (produto.getFornecedor() != null && produto.getFornecedor().getIdFornecedor() == 0) {
			produto.setFornecedor(null);
		}

		/*
		 * Se for um produto novo, cria um estoque inicial zerado.
		 * 
		 * Isso garante que todo produto cadastrado já apareça automaticamente na tela
		 * de estoque.
		 */
		if (produto.getIdProduto() == 0 && produto.getEstoque() == null) {

			Estoque estoque = new Estoque();

			estoque.setQuantidadeAtual(0);
			estoque.setProduto(produto);

			produto.setEstoque(estoque);
		}
		/*
		 * Salva o produto no banco.
		 */
		return produtoRepository.save(produto);
	}

	/*
	 * Busca um produto pelo ID. Usado para carregar os dados na tela de edição.
	 */
	public Produto buscarPorId(int id) {

		return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
	}

	/*
	 * Exclui vários produtos selecionados. Usado quando o usuário marca várias
	 * linhas na tabela.
	 */
	public void excluirSelecionados(List<Integer> ids) {

		produtoRepository.deleteAllById(ids);
	}

	/*
	 * Exclui produto pelo ID.
	 */
	public void excluir(int id) {
		produtoRepository.deleteById(id);
	}
}