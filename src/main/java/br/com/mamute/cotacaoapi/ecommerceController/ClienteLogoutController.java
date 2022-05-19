package br.com.mamute.cotacaoapi.ecommerceController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.ClienteLogoutService;

@Controller
@RequestMapping("/usuario/logout")
public class ClienteLogoutController {
	
	@Autowired private ClienteLogoutService clienteLogoutService;
	
	@GetMapping
    public ModelAndView sai(HttpServletRequest request) {       
		return clienteLogoutService.sair(request);
    } 
	
	@PostMapping
    public ModelAndView sair(HttpServletRequest request) {       
		return clienteLogoutService.sair(request);
    }
}
