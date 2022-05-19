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

import br.com.mamute.cotacaoapi.model.TaxaEntrega;
import br.com.mamute.cotacaoapi.service.TaxaEntregaService;

@Controller
@RequestMapping("/dashboard-admin/taxa-entrega")
public class TaxaEntregaController {
	
	@Autowired
	private TaxaEntregaService taxaEntregaService;
	
    @GetMapping("/registrar")
	ModelAndView form(TaxaEntrega taxa) {
		return taxaEntregaService.form(taxa);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(@Valid TaxaEntrega taxa, BindingResult result, RedirectAttributes attributes) {
		return taxaEntregaService.salvar(taxa, result, attributes);				
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return taxaEntregaService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, RedirectAttributes attributes){
		return taxaEntregaService.editar(id, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return taxaEntregaService.deletar(id, attributes);
	}
}

