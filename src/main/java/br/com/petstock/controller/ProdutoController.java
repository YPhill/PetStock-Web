package br.com.petstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller responsável pelas rotas do módulo de produtos.
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    /*
     * Abre a página principal de produtos.
     */
    @RequestMapping
    public String listarProdutos() {
        return "produtos";
    }
}