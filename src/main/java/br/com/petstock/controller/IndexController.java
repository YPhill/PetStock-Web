package br.com.petstock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String inicio(Model model) {

		model.addAttribute("paginaAtual", "inicio");

		return "index";
	}
}