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

import br.com.mamute.cotacaoapi.model.UnidadeDeMedida;
import br.com.mamute.cotacaoapi.service.UnidadeMedidaService;

@Controller
@RequestMapping("/dashboard-admin/unidade-medida")
public class UnidadeDeMedidaController {
	
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;
	
    @GetMapping("/registrar")
	ModelAndView form(UnidadeDeMedida unidadeDeMedida) {
		return unidadeMedidaService.form(unidadeDeMedida);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(UnidadeDeMedida unidadeDeMedida, BindingResult result,RedirectAttributes attributes) {
		return unidadeMedidaService.salvar(unidadeDeMedida, result, attributes);				
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return unidadeMedidaService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, RedirectAttributes attributes){
		return unidadeMedidaService.editar(id, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return unidadeMedidaService.deletar(id, attributes);
	}
}
