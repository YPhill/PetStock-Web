package br.com.petstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petstock.service.ProdutoService;

/*
 * Controller responsável pelas rotas do módulo de estoque.
 */
@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    /*
     * Usamos ProdutoService porque o estoque está vinculado ao produto.
     */
    @Autowired
    private ProdutoService produtoService;

    /*
     * Abre a página principal de estoque.
     */
    @RequestMapping
    public String abrirEstoque(Model model) {

        // Envia os produtos com seus respectivos estoques para o HTML
        model.addAttribute("produtos", produtoService.listarTodos());

        // Abre templates/estoque.html
        return "estoque";
    }
}