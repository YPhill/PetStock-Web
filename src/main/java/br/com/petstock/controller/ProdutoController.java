package br.com.petstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.petstock.model.Produto;
import br.com.petstock.service.FornecedorService;
import br.com.petstock.service.ProdutoService;

/*
 * Controller responsável pelas rotas do módulo de produtos.
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	/*
	 * Injeta automaticamente o ProdutoService.
	 */
	@Autowired
	private ProdutoService produtoService;

	/*
	 * Injeta o FornecedorService para carregar os fornecedores no formulário.
	 */
	@Autowired
	private FornecedorService fornecedorService;

	/*
	 * Abre a página principal de produtos.
	 */
	@RequestMapping
	public String listarProdutos(Model model) {

		// Envia a lista de produtos para o HTML
		model.addAttribute("produtos", produtoService.listarTodos());

		// Abre templates/produtos.html
		return "produtos";
	}

	/*
	 * Abre o formulário de cadastro de produto.
	 */
	@RequestMapping("/novo")
	public String novoProduto(Model model) {

		Produto produto = new Produto();

		// Cria fornecedor vazio para evitar erro no select do Thymeleaf
		produto.setFornecedor(new br.com.petstock.model.Fornecedor());

		model.addAttribute("produto", produto);

		// Envia fornecedores para o select do formulário
		model.addAttribute("fornecedores", fornecedorService.listarTodos());

		// Abre templates/formulario-produto.html
		return "formulario-produto";
	}

	/*
	 * Abre o formulário de edição.
	 */
	@RequestMapping("/editar")
	public String editarProduto(@RequestParam("id") int id, Model model) {

		// Busca produto selecionado no banco
		Produto produto = produtoService.buscarPorId(id);

		// Envia produto preenchido para o formulário
		model.addAttribute("produto", produto);

		// Envia fornecedores para manter o select preenchido na edição
		model.addAttribute("fornecedores", fornecedorService.listarTodos());

		// Abre o mesmo formulário usado para cadastro
		return "formulario-produto";
	}

	/*
	 * Salva produto novo ou editado.
	 */
	@RequestMapping("/salvar")
	public String salvarProduto(Produto produto) {

		// Salva produto no banco
		produtoService.salvar(produto);

		// Volta para a listagem
		return "redirect:/produtos";
	}

	/*
	 * Exclui vários produtos selecionados.
	 */
	@RequestMapping("/excluir")
	public String excluirProdutos(@RequestParam("idsSelecionados") List<Integer> idsSelecionados, Model model) {

		try {

			// Exclui produtos selecionados
			produtoService.excluirSelecionados(idsSelecionados);

		} catch (Exception e) {

			// Recarrega lista para a tabela continuar aparecendo
			model.addAttribute("produtos", produtoService.listarTodos());

			// Mensagem amigável
			model.addAttribute("erro", "Não é possível excluir produto vinculado ao estoque ou vendas.");

			return "produtos";
		}

		// Volta para listagem
		return "redirect:/produtos";
	}
}
