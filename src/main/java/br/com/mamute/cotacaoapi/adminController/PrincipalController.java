package br.com.mamute.cotacaoapi.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PrincipalController {	
	
	@GetMapping()
	String principal() {
		return"dashboard-admin/analise/resultados";
	}
	
	
	
	
	

}
