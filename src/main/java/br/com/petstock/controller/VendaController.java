package br.com.petstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * Abre a página principal de vendas.
     */
    @RequestMapping
    public String listarVendas(Model model) {

        // Envia a lista de vendas para o HTML
        model.addAttribute("vendas", vendaService.listarTodas());

        // Abre templates/vendas.html
        return "vendas";
    }
}