package com.debugon.cobranca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.debugon.cobranca.model.Titulo;
import com.debugon.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(Titulo titulo) {
		// TODO: Salvar no banco de dados - jdbc:h2:mem:testdb
		titulos.save(titulo);
		System.out.println(">>>" + titulo.getDescricao());
		
		return "CadastroTitulo";
	}
}
