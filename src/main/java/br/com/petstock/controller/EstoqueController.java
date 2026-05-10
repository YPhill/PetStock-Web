package br.com.petstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller responsável pelas rotas do módulo de estoque.
 */
@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    /*
     * Abre a página principal de estoque.
     */
    @RequestMapping
    public String abrirEstoque() {
        return "estoque";
    }
}