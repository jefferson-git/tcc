package br.com.mamute.cotacaoapi.adminController;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.DescricaoTelefone;
import br.com.mamute.cotacaoapi.service.DescricaoTelefoneService;

@Controller
@RequestMapping("/dashboard-admin/descricao-telefone")
public class DescricaoTelefoneController {
	
	@Autowired
	private DescricaoTelefoneService descricaoTelefoneService;
	
	@GetMapping("registrar")
	ModelAndView form(DescricaoTelefone descricao) {
	    return descricaoTelefoneService.form(descricao);		 
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(@Valid DescricaoTelefone tipo, BindingResult result, RedirectAttributes attributes) {		
		return descricaoTelefoneService.salvar(tipo, result, attributes);	
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return descricaoTelefoneService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Integer id, RedirectAttributes attributes) {	
		return descricaoTelefoneService.editar(id, attributes);		
	}
	
	@GetMapping("/deletar/{id}")
	public ModelAndView deletar(@PathVariable Integer id, RedirectAttributes attributes) {		
		return descricaoTelefoneService.deletar(id, attributes);
	}
}
	
