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

import br.com.mamute.cotacaoapi.model.FormaPagamento;
import br.com.mamute.cotacaoapi.service.FormaPagamentoService;

@Controller
@RequestMapping("/dashboard-admin/forma-pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoService formaPagmentoService;
	
    @GetMapping("/registrar")
	ModelAndView form(FormaPagamento formaPagmento) {
		return formaPagmentoService.form(formaPagmento);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(@Valid FormaPagamento formaPagmento, BindingResult result,RedirectAttributes attributes) {
		return formaPagmentoService.salvar(formaPagmento , result, attributes);				
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return formaPagmentoService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, RedirectAttributes attributes){
		return formaPagmentoService.editar(id, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return formaPagmentoService.deletar(id, attributes);
	}
}
