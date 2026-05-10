package br.com.petstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller responsável pelas rotas do módulo de relatórios.
 */
@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    /*
     * Abre a página principal de relatórios.
     */
    @RequestMapping
    public String abrirRelatorios() {
        return "relatorios";
    }
}