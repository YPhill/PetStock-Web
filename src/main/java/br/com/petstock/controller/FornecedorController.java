package br.com.petstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

        // Abre templates/fornecedores.html
        return "fornecedores";
    }
}