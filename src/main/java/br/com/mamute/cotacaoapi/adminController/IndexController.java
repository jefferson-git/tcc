package br.com.mamute.cotacaoapi.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Controller
@RequestMapping("/dashboard-admin")
public class IndexController {	
	
	@Autowired private UsuarioService usuarioService;
	
	@GetMapping()
	public ModelAndView principal() {
		ModelAndView mvPrincipal = new ModelAndView("dashboard-admin/analise/resultados");
		mvPrincipal.addObject("colaboradorLogado",usuarioService.usuarioLogado());
		
		return mvPrincipal;
	}
	
	@GetMapping("/resultados")
	public ModelAndView resultados() {
		ModelAndView mvResultados = new ModelAndView("dashboard-admin/analise/resultados");		
		return mvResultados.addObject("colaboradorLogado",usuarioService.usuarioLogado());
	}
	
	@GetMapping("/ecommerce")
	public ModelAndView ecommerce() {
		ModelAndView mvEcommerce = new ModelAndView("dashboard-admin/analise/ecommerce");		
		return mvEcommerce.addObject("colaboradorLogado",usuarioService.usuarioLogado());
	}
	
	@GetMapping("/analise")
	public ModelAndView analytics() {
		ModelAndView mvAnalytics = new ModelAndView("dashboard-admin/analise/analise");		
		return mvAnalytics.addObject("colaboradorLogado",usuarioService.usuarioLogado());
	}
	
	@GetMapping("/modelo")
	public ModelAndView modelo() {
		ModelAndView mvModelo = new ModelAndView("dashboard-admin/model-page");		
		return mvModelo.addObject("colaboradorLogado",usuarioService.usuarioLogado());
	}
	
	

}
