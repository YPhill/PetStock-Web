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
	public String novoCliente(Model model) {

		model.addAttribute("cliente", new Cliente());

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
	public String salvarCliente(Cliente cliente) {

		clienteService.salvar(cliente);

		return "redirect:/clientes";
	}

	/*
	 * Exclui vários clientes selecionados.
	 */
	@PostMapping("/clientes/excluir")
	public String excluirClientes(@RequestParam("idsSelecionados") List<Integer> idsSelecionados) {

		clienteService.excluirSelecionados(idsSelecionados);

		return "redirect:/clientes";
	}
}