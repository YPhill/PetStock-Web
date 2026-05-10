package br.com.petstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller responsável pelas rotas do módulo de fornecedores.
 */
@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    /*
     * Abre a página principal de fornecedores.
     */
    @RequestMapping
    public String listarFornecedores() {
        return "fornecedores";
    }
}