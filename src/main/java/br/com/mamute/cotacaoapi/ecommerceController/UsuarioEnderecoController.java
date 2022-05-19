package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.UsuarioEnderecoService;
import br.com.mamute.cotacaoapi.model.Cliente;

@Controller
@RequestMapping("/usuario/endereco")
public class UsuarioEnderecoController {
	
	@Autowired private UsuarioEnderecoService usuarioEndereco;
	
    @GetMapping()
	public ModelAndView endereco() {
		return usuarioEndereco.endereco();
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(Cliente cliente,BindingResult result) {
    	return usuarioEndereco.salvar(cliente, result);    	
    }
}
