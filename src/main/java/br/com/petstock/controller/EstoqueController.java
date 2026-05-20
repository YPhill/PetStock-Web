package br.com.petstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.petstock.service.EstoqueService;

/*
 * Controller responsável pelas rotas do módulo de estoque.
 */
@Controller
@RequestMapping("/estoque")
public class EstoqueController {

	/*
	 * Injeta automaticamente o EstoqueService. Ele controla as regras de entrada,
	 * saída e ajuste de estoque.
	 */
	@Autowired
	private EstoqueService estoqueService;

	/*
	 * Abre a página principal de estoque.
	 */
	@RequestMapping
	public String abrirEstoque(Model model) {

		// Envia todos os registros de estoque para o HTML
		model.addAttribute("estoques", estoqueService.listarTodos());
		

		// Abre templates/estoque.html
		return "estoque";
	}

	/*
	 * Realiza entrada de estoque. Soma a quantidade informada ao estoque atual.
	 */
	@RequestMapping("/entrada")
	public String entradaEstoque(@RequestParam("idProduto") int idProduto, @RequestParam("quantidade") int quantidade,
			Model model) {

		try {
			// Chama o service para somar a quantidade no estoque
			estoqueService.entrada(idProduto, quantidade);

		} catch (Exception e) {

			// Recarrega a lista para manter a tela preenchida
			model.addAttribute("estoques", estoqueService.listarTodos());

			// Envia mensagem de erro amigável
			model.addAttribute("erro", e.getMessage());

			return "estoque";
		}

		// Volta para a tela de estoque atualizada
		return "redirect:/estoque";
	}

	/*
	 * Realiza saída de estoque. Subtrai a quantidade informada do estoque atual.
	 */
	@RequestMapping("/saida")
	public String saidaEstoque(@RequestParam("idProduto") int idProduto, @RequestParam("quantidade") int quantidade,
			Model model) {

		try {
			// Chama o service para subtrair a quantidade do estoque
			estoqueService.saida(idProduto, quantidade);

		} catch (Exception e) {

			// Recarrega a lista para manter a tela preenchida
			model.addAttribute("estoques", estoqueService.listarTodos());

			// Envia mensagem de erro amigável
			model.addAttribute("erro", e.getMessage());

			return "estoque";
		}

		// Volta para a tela de estoque atualizada
		return "redirect:/estoque";
	}

	/*
	 * Ajusta diretamente a quantidade do estoque. Usado em inventário ou correção
	 * manual.
	 */
	@RequestMapping("/ajustar")
	public String ajustarEstoque(@RequestParam("idProduto") int idProduto, @RequestParam("quantidade") int quantidade,
			Model model) {

		try {
			// Chama o service para definir a nova quantidade
			estoqueService.ajustar(idProduto, quantidade);

		} catch (Exception e) {

			// Recarrega a lista para manter a tela preenchida
			model.addAttribute("estoques", estoqueService.listarTodos());

			// Envia mensagem de erro amigável
			model.addAttribute("erro", e.getMessage());

			return "estoque";
		}

		// Volta para a tela de estoque atualizada
		return "redirect:/estoque";
	}

}