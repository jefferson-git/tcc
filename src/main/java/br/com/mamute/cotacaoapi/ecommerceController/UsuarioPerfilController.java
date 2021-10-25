package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.ClienteLoginService;
import br.com.mamute.cotacaoapi.ecommerceService.UsuarioPerfilService;

@Controller
@RequestMapping("/mamute/perfil")
public class UsuarioPerfilController {
	
	@Autowired
	private UsuarioPerfilService usuarioPerfilService;
	
    @GetMapping()
	ModelAndView perfil() {
		return usuarioPerfilService.perfil();
    }
}
