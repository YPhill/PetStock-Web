package br.com.petstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller responsável pelas rotas do módulo de vendas.
 */
@Controller
@RequestMapping("/vendas")
public class VendaController {

    /*
     * Abre a página principal de vendas.
     */
    @RequestMapping
    public String abrirVendas() {
        return "vendas";
    }
}