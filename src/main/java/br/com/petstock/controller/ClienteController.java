package br.com.petstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.petstock.model.Cliente;
import br.com.petstock.service.ClienteService;

import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller responsável pelas rotas do módulo de clientes.
 */
@Controller
public class ClienteController {

	/*
	 * Injeta automaticamente o ClienteService.
	 */
	@Autowired
	private ClienteService clienteService;

	/*
	 * Lista todos os clientes.
	 */
	@GetMapping("/clientes")
	public String listarClientes(Model model) {

		model.addAttribute("clientes", clienteService.listarTodos());
		

		return "clientes";
	}

	/*
	 * Abre o formulário de cadastro.
	 */
	@GetMapping("/clientes/novo")
	public String novoCliente(@RequestParam(value = "origem", required = false) String origem, Model model) {

		model.addAttribute("cliente", new Cliente());

		// Guarda de onde o usuário veio
		model.addAttribute("origem", origem);

		return "formulario-cliente";
	}

	/*
	 * Abre o formulário de edição. Recebe o ID selecionado na tabela.
	 */
	@GetMapping("/clientes/editar")
	public String editarCliente(@RequestParam("id") int id, Model model) {

		Cliente cliente = clienteService.buscarPorId(id);

		model.addAttribute("cliente", cliente);

		return "formulario-cliente";
	}

	/*
	 * Salva cliente novo ou editado.
	 */
	@PostMapping("/clientes/salvar")
	public String salvarCliente(Cliente cliente,
			@RequestParam(value = "origem", required = false) String origem) {

		clienteService.salvar(cliente);

		// Se veio da tela de vendas, volta para vendas
		if ("vendas".equals(origem)) {
			return "redirect:/vendas";
		}

		return "redirect:/clientes";
	}

	/*
	 * Exclui vários clientes selecionados.
	 */
	@PostMapping("/clientes/excluir")
	public String excluirClientes(
	        @RequestParam("idsSelecionados") List<Integer> idsSelecionados,
	        Model model) {

	    try {
	        clienteService.excluirSelecionados(idsSelecionados);

	    } catch (Exception e) {

	        model.addAttribute("clientes", clienteService.listarTodos());
	        model.addAttribute("erro", "Não é possível excluir cliente vinculado a uma venda.");

	        return "clientes";
	    }

	    return "redirect:/clientes";
	}
	
	
	}
