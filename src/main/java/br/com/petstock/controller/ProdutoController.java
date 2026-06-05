package br.com.petstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.petstock.model.Fornecedor;
import br.com.petstock.model.Produto;
import br.com.petstock.service.FornecedorService;
import br.com.petstock.service.ProdutoService;

/*
 * Controller responsável pelas rotas do módulo de produtos.
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private FornecedorService fornecedorService;

	/*
	 * Lista todos os produtos.
	 */
	@RequestMapping
	public String listarProdutos(Model model) {

		model.addAttribute("produtos", produtoService.listarTodos());
		
		model.addAttribute("paginaAtual", "produtos");

		return "produtos";
	}

	/*
	 * Abre o formulário de cadastro.
	 */
	@RequestMapping("/novo")
	public String novoProduto(Model model) {

		Produto produto = new Produto();

		// Evita erro no select do Thymeleaf
		produto.setFornecedor(new Fornecedor());

		model.addAttribute("produto", produto);
		model.addAttribute("fornecedores", fornecedorService.listarTodos());
		
		model.addAttribute("paginaAtual", "produtos");

		return "formulario-produto";
	}

	/*
	 * Abre o formulário de edição.
	 */
	@RequestMapping("/editar")
	public String editarProduto(@RequestParam("id") int id, Model model) {

		Produto produto = produtoService.buscarPorId(id);

		if (produto.getFornecedor() == null) {
			produto.setFornecedor(new Fornecedor());
		}

		model.addAttribute("produto", produto);
		model.addAttribute("fornecedores", fornecedorService.listarTodos());
		
		model.addAttribute("paginaAtual", "produtos");

		return "formulario-produto";
	}

	/*
	 * Salva produto novo ou editado.
	 */
	@RequestMapping("/salvar")
	public String salvarProduto(Produto produto, Model model) {

		try {

			produtoService.salvar(produto);

			return "redirect:/produtos";

		} catch (RuntimeException e) {

			if (produto.getFornecedor() == null) {
				produto.setFornecedor(new Fornecedor());
			}

			model.addAttribute("produto", produto);

			model.addAttribute("fornecedores", fornecedorService.listarTodos());

			model.addAttribute("erro", e.getMessage());
			
			model.addAttribute("paginaAtual", "produtos");

			return "formulario-produto";
		}
	}

	/*
	 * Exclui vários produtos selecionados.
	 */
	@RequestMapping("/excluir")
	public String excluirProdutos(@RequestParam("idsSelecionados") List<Integer> idsSelecionados, Model model) {

		try {
			produtoService.excluirSelecionados(idsSelecionados);

		} catch (Exception e) {

			model.addAttribute("produtos", produtoService.listarTodos());
			model.addAttribute("erro", "Não é possível excluir produto vinculado ao estoque ou vendas.");
			
			model.addAttribute("paginaAtual", "produtos");

			return "produtos";
		}

		return "redirect:/produtos";
	}
}