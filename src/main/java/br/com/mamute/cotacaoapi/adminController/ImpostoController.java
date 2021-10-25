package br.com.mamute.cotacaoapi.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Imposto;
import br.com.mamute.cotacaoapi.service.ImpostoService;

@Controller
@RequestMapping("/dashboard-admin/imposto")
public class ImpostoController {
	
	@Autowired
	private ImpostoService impostoService;
	
    @GetMapping("/registrar")
	ModelAndView form(Imposto imposto) {
		return impostoService.form(imposto);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(Imposto imposto, BindingResult result,RedirectAttributes attributes) {
		return impostoService.salvar(imposto, result, attributes);				
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return impostoService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, RedirectAttributes attributes){
		return impostoService.editar(id, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return impostoService.deletar(id, attributes);
	}
}
