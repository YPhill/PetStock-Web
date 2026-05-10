package br.com.petstock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petstock.service.ClienteService;

/*
 * Controller responsável pelas rotas do módulo de clientes.
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    /*
     * Injeta automaticamente o ClienteService.
     * O Controller usa o Service, e não acessa o Repository diretamente.
     */
    @Autowired
    private ClienteService clienteService;

    /*
     * Abre a página principal de clientes.
     * Também envia a lista de clientes para o HTML.
     */
    @RequestMapping
    public String listarClientes(Model model) {

        // Busca todos os clientes cadastrados no banco
        model.addAttribute("clientes", clienteService.listarTodos());

        // Abre o arquivo templates/clientes.html
        return "clientes";
    }
}