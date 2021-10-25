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

import br.com.mamute.cotacaoapi.model.Desconto;
import br.com.mamute.cotacaoapi.service.DescontoService;

@Controller
@RequestMapping("/dashboard-admin/desconto")
public class DescontoController {
	
	@Autowired
	private DescontoService descontoService;
	
    @GetMapping("/registrar")
	ModelAndView form(Desconto desconto) {
		return descontoService.form(desconto);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(Desconto desconto, BindingResult result,RedirectAttributes attributes) {
		return descontoService.salvar(desconto, result, attributes);				
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return descontoService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, RedirectAttributes attributes){
		return descontoService.editar(id, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return descontoService.deletar(id, attributes);
	}
}
