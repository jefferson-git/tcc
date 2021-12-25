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

import br.com.mamute.cotacaoapi.model.Permissao;
import br.com.mamute.cotacaoapi.service.PermissaoService;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Controller
@RequestMapping("/dashboard-admin/permissao")
public class PermissaoController {
	
	@Autowired PermissaoService permissaoService;
	
	@GetMapping("/registrar/{id}")
	ModelAndView form(@PathVariable Long id, RedirectAttributes attributes) {		
		return permissaoService.form(id, attributes);		
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(Permissao permissao,Long id, BindingResult result, RedirectAttributes attributes) {	
		return permissaoService.salvar(id, permissao, result, attributes );
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {				 
		 return permissaoService.listar(attributes);
	} 
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Integer id, RedirectAttributes attributes) {
		return permissaoService.deletar(id, attributes);	
	}
	
}
