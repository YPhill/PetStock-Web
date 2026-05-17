package br.com.petstock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petstock.dto.ItemVendaRequestDTO;
import br.com.petstock.dto.VendaRequestDTO;
import br.com.petstock.model.Cliente;
import br.com.petstock.model.ItensVenda;
import br.com.petstock.model.Produto;
import br.com.petstock.model.Venda;
import br.com.petstock.service.ClienteService;
import br.com.petstock.service.ProdutoService;
import br.com.petstock.service.VendaService;
/*
 * Controller responsável pelas rotas do módulo de vendas.
 */
@Controller
@RequestMapping("/vendas")
public class VendaController {

	/*
	 * Injeta automaticamente o VendaService.
	 */
	@Autowired
	private VendaService vendaService;

	/*
	 * Injeta o ClienteService para carregar os clientes na tela de venda.
	 */
	@Autowired
	private ClienteService clienteService;

	/*
	 * Injeta o ProdutoService para carregar os produtos na tela de venda.
	 */
	@Autowired
	private ProdutoService produtoService;

	/*
	 * Abre a página principal de vendas.
	 */
	@RequestMapping
	public String listarVendas(Model model) {

		// Envia clientes para o campo de seleção/pesquisa da venda
		model.addAttribute("clientes", clienteService.listarTodos());

		// Envia produtos para o campo de seleção/pesquisa da venda
		model.addAttribute("produtos", produtoService.listarTodos());

		// Abre templates/vendas.html
		return "vendas";
	}
	
	/*
	 * Finaliza a venda enviada pela tela.
	 * 
	 * Recebe os dados do carrinho em formato JSON,
	 * transforma em entidades Java e chama o VendaService.
	 */
	@PostMapping("/finalizar")
	public ResponseEntity<String> finalizarVenda(@RequestBody VendaRequestDTO vendaDTO) {

		try {
			/*
			 * Cria a entidade Venda que será salva no banco.
			 */
			Venda venda = new Venda();

			/*
			 * Se a venda tiver cliente, associa somente o ID.
			 * O JPA entende que esse cliente já existe no banco.
			 */
			if (vendaDTO.getClienteId() != null && vendaDTO.getClienteId() > 0) {

				Cliente cliente = new Cliente();
				cliente.setIdCliente(vendaDTO.getClienteId());

				venda.setCliente(cliente);
			}

			/*
			 * Lista que receberá os itens da venda.
			 */
			List<ItensVenda> itens = new ArrayList<>();

			/*
			 * Converte cada item recebido do JavaScript
			 * em um objeto ItensVenda.
			 */
			for (ItemVendaRequestDTO itemDTO : vendaDTO.getItens()) {

				ItensVenda item = new ItensVenda();

				Produto produto = new Produto();
				produto.setIdProduto(itemDTO.getProdutoId());

				item.setProduto(produto);
				item.setQuantidade(itemDTO.getQuantidade());

				itens.add(item);
			}

			/*
			 * Associa os itens à venda.
			 */
			venda.setItens(itens);

			/*
			 * Chama o service para validar, calcular total,
			 * baixar estoque e salvar a venda.
			 */
			vendaService.salvar(venda);

			return ResponseEntity.ok("Venda finalizada com sucesso!");

		} catch (Exception e) {

			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}