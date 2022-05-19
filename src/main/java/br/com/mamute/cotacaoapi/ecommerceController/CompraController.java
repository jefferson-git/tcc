package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("usuario/compra")
public class CompraController {

	@PostMapping
	public ModelAndView finalizaCompra() {
		return null;
	}
}
