package br.com.petstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petstock.service.ClienteService;
import br.com.petstock.service.VendaService;

import org.springframework.web.bind.annotation.RequestParam;

/*
 * Controller responsável pelas rotas do módulo de relatórios.
 */
@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

	/*
	 * Injeta automaticamente o VendaService.
	 */
	@Autowired
	private VendaService vendaService;

	/*
	 * Injeta automaticamente o ClienteService.
	 */
	@Autowired
	private ClienteService clienteService;

	/*
	 * Abre a tela de relatórios.
	 */
	@RequestMapping
	public String abrirRelatorios(Model model) {

		// Envia todas as vendas para a tabela principal
		model.addAttribute("vendas", vendaService.listarTodas());

		// Envia clientes para o filtro pesquisável
		model.addAttribute("clientes", clienteService.listarTodos());
		
		model.addAttribute("paginaAtual", "relatorios");

		// Abre templates/relatorios.html
		return "relatorios";
	}

	/*
	 * Exclui uma venda pelo ID.
	 * 
	 * Neste momento, a exclusão não devolve itens ao estoque. Serve apenas para
	 * corrigir uma venda cadastrada incorretamente.
	 */
	@RequestMapping("/excluir")
	public String excluirVenda(@RequestParam("id") int id) {

		vendaService.excluir(id);

		return "redirect:/relatorios";
	}
	
	
}
