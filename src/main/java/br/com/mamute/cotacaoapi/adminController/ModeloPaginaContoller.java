package br.com.mamute.cotacaoapi.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class ModeloPaginaContoller {
	
	@GetMapping("model-page")
	String modeloPage() {
		return"model-page";
	}

}
