package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.ecommerceService.RastreamentoService;
import br.com.mamute.cotacaoapi.model.Departamento;

@Controller
@RequestMapping("/mamute")
public class RastreamentoController {
	
	@Autowired
	private RastreamentoService rastreamentoService;
	
    @GetMapping("/rastreamento")
	ModelAndView Rastreamento(Departamento departamento) {
		return rastreamentoService.page(departamento);
	}		
	
}
