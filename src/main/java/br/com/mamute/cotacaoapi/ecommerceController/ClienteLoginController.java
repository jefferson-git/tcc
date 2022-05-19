package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.ecommerceService.ClienteLoginService;
import br.com.mamute.cotacaoapi.model.Cliente;

@Controller
@RequestMapping("/usuario/login")
public class ClienteLoginController {
	
	@Autowired private ClienteLoginService clienteLoginService;
	
    @GetMapping()
    public ModelAndView login(Cliente cliente, BindingResult result, String mensagem) {
		return clienteLoginService.logar(cliente, result, mensagem);
    }
    
    @PostMapping()
    public ModelAndView logar(Cliente cliente, BindingResult result, String mensagem) {
		return clienteLoginService.logar(cliente, result, mensagem);
    }
    
    @PostMapping("/registrar")
   	public ModelAndView registrar(Cliente cliente, BindingResult result, RedirectAttributes attributes, String mensagem) {
   		return clienteLoginService.registrar(cliente, result,attributes, mensagem);
    }
}
