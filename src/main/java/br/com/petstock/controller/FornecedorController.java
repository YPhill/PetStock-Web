package br.com.petstock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.petstock.model.Fornecedor;
import br.com.petstock.service.FornecedorService;

/*
 * Controller responsável pelas rotas do módulo de fornecedores.
 */
@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    /*
     * Injeta automaticamente o FornecedorService.
     */
    @Autowired
    private FornecedorService fornecedorService;

    /*
     * Abre a página principal de fornecedores.
     */
    @RequestMapping
    public String listarFornecedores(Model model) {

        // Envia lista de fornecedores para o HTML
        model.addAttribute("fornecedores", fornecedorService.listarTodos());

        model.addAttribute("paginaAtual", "fornecedores");
        
        // Abre templates/fornecedores.html
        return "fornecedores";
    }

    /*
     * Abre o formulário de cadastro de fornecedor.
     */
    @RequestMapping("/novo")
    public String novoFornecedor(Model model) {

        // Cria objeto vazio para preencher no formulário
        model.addAttribute("fornecedor", new Fornecedor());
        
        model.addAttribute("paginaAtual", "fornecedores");

        // Abre templates/formulario-fornecedor.html
        return "formulario-fornecedor";
    }

    /*
     * Abre o formulário de edição.
     */
    @RequestMapping("/editar")
    public String editarFornecedor(@RequestParam("id") int id, Model model) {

        // Busca fornecedor selecionado no banco
        Fornecedor fornecedor = fornecedorService.buscarPorId(id);

        // Envia fornecedor preenchido para o formulário
        model.addAttribute("fornecedor", fornecedor);
        
        model.addAttribute("paginaAtual", "fornecedores");

        // Abre o mesmo formulário usado para cadastro
        return "formulario-fornecedor";
    }

    /*
     * Salva fornecedor novo ou editado.
     */
    @RequestMapping("/salvar")
    public String salvarFornecedor(Fornecedor fornecedor, Model model) {

    	try {

    		fornecedorService.salvar(fornecedor);

    		return "redirect:/fornecedores";

    	} catch (RuntimeException e) {

    		model.addAttribute("fornecedor", fornecedor);

    		model.addAttribute("erro", e.getMessage());
    		
    		model.addAttribute("paginaAtual", "fornecedores");

    		return "formulario-fornecedor";
    	}
    }

    /*
     * Exclui vários fornecedores selecionados.
     */


    @RequestMapping("/excluir")
    public String excluirFornecedores(
            @RequestParam("idsSelecionados") List<Integer> idsSelecionados,
            Model model) {

        try {
            // Tenta excluir os fornecedores selecionados
            fornecedorService.excluirSelecionados(idsSelecionados);

        } catch (Exception e) {

            // Recarrega a lista para a tabela continuar aparecendo
            model.addAttribute("fornecedores", fornecedorService.listarTodos());

            // Envia mensagem amigável para o HTML
            model.addAttribute("erro", "Não é possível excluir fornecedor vinculado a produtos.");

            model.addAttribute("paginaAtual", "fornecedores");
            
            
            return "fornecedores";
        }

        // Volta para a listagem após excluir com sucesso
        return "redirect:/fornecedores";
    }
    
    
}