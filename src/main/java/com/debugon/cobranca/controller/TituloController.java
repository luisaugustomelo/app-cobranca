package com.debugon.cobranca.controller;

import java.util.Arrays;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.debugon.cobranca.model.StatusTitulo;
import com.debugon.cobranca.model.Titulo;
import com.debugon.cobranca.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private final String CADASTRO_VIEW = "CadastroTitulo";
	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		// mv.addObject("todosStatusTitulo", StatusTitulo.values());
		// return "CadastroTitulo";
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		// TODO: Salvar no banco de dados - jdbc:h2:mem:testdb
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso!!");
		// mv.addObject("todosStatusTitulo", StatusTitulo.values());
		// return "CadastroTitulo";
		return "redirect:/titulos/novo";
	}

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		// return "PesquisaTitulos";
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	//public ModelAndView edicao(@PathVariable Long codigo) {
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		//Optional<Titulo> titulo = titulos.findById(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		//mv.addObject(titulo.get());
		mv.addObject(titulo);
		return mv;
	}

	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		titulos.deleteById(codigo);
		attributes.addFlashAttribute("mensagem", "Titulo excluído com sucesso!!");
		return "redirect:/titulos";
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		// Poderia ser utilizado pelo thymeleaf como StatusTituloList (tipo do retorno
		// do método)
		return Arrays.asList(StatusTitulo.values());
	}
}
