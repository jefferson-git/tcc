package br.com.mamute.cotacaoapi.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard-admin")
public class IndexController {	
	
	@GetMapping()
	String principal() {
		return"dashboard-admin/analise/resultados";
	}
	
	@GetMapping("/resultados")
	String resultados() {
		return"dashboard-admin/analise/resultados";
	}
	
	@GetMapping("/ecommerce")
	String ecommerce() {
		return"dashboard-admin/analise/ecommerce";
	}
	
	@GetMapping("/analise")
	String analytics() {
		return"dashboard-admin/analise/analise";
	}
	
	@GetMapping("/modelo")
	String estado() {
		return"dashboard-admin/model-page";
	}
	
	

}
