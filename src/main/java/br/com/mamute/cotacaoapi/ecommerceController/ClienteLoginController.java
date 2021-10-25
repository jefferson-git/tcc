package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.ClienteLoginService;
import br.com.mamute.cotacaoapi.model.Usuario;

@Controller
@RequestMapping("/mamute/login")
public class ClienteLoginController {
	
	@Autowired
	private ClienteLoginService clienteLoginService;
	
    @GetMapping()
	ModelAndView login() {
		return clienteLoginService.logar();
    }
    
    @PostMapping()
	ModelAndView login(Usuario usuario) {
		return clienteLoginService.logar();
    }
}
