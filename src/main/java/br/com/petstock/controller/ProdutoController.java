package br.com.petstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * Abre a página principal de produtos.
     */
    @RequestMapping
    public String listarProdutos(Model model) {

        // Envia a lista de produtos para o HTML
        model.addAttribute("produtos", produtoService.listarTodos());

        // Abre templates/produtos.html
        return "produtos";
    }
}