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

import br.com.mamute.cotacaoapi.model.Lucro;
import br.com.mamute.cotacaoapi.service.LucroService;

@Controller
@RequestMapping("/dashboard-admin/lucro")
public class LucroController {
	
	@Autowired
	private LucroService lucroService;
	
    @GetMapping("/registrar")
	ModelAndView form(Lucro lucro) {
		return lucroService.form(lucro);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(Lucro lucro, BindingResult result,RedirectAttributes attributes) {
		return lucroService.salvar(lucro, result, attributes);				
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return lucroService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, RedirectAttributes attributes){
		return lucroService.editar(id, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return lucroService.deletar(id, attributes);
	}
}
