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

import br.com.mamute.cotacaoapi.model.Categoria;
import br.com.mamute.cotacaoapi.service.CategoriaService;

@Controller
@RequestMapping("/dashboard-admin/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
	
    @GetMapping("/registrar")
	ModelAndView form(Categoria categoria) {
		return categoriaService.form(categoria);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(Categoria categoria, BindingResult result,RedirectAttributes attributes) {
		return categoriaService.salvar(categoria, result, attributes);				
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return categoriaService.listar(attributes);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, RedirectAttributes attributes){
		return categoriaService.editar(id, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return categoriaService.deletar(id, attributes);
	}
}
