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

import br.com.mamute.cotacaoapi.model.Papel;
import br.com.mamute.cotacaoapi.service.PapelService;

@Controller
@RequestMapping("/dashboard-admin/papel")
public class PapelController {
	
	@Autowired
	private PapelService papelService;
	
	@GetMapping("/registrar")
	ModelAndView form(Papel papel) {
		return papelService.form(papel);
	}

	@PostMapping("/salvar")
	ModelAndView salvar(Papel papel, BindingResult result, RedirectAttributes attributes) {		
		return papelService.salvar(papel, result, attributes);		
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {				
		return papelService.listar(attributes);
	}
	
	@GetMapping("editar/{id}")
	ModelAndView editar(@PathVariable Integer id, RedirectAttributes attributes) {
		return papelService.editar(id, attributes);
	}
	
	@GetMapping("deletar/{id}")
	ModelAndView deletar(@PathVariable Integer id, RedirectAttributes attributes) {
		return papelService.deletar(id, attributes);
	}
}
