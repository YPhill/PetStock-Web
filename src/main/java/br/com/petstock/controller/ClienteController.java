package br.com.petstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller responsável pelas rotas do módulo de clientes.
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    /*
     * Abre a página principal de clientes.
     */
    @RequestMapping
    public String listarClientes() {
        return "clientes";
    }
}