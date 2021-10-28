package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.UsuarioListaDesejosService;
import br.com.mamute.cotacaoapi.ecommerceService.UsuarioPedidoService;

@Controller
@RequestMapping("/mamute/desejos")
public class UsuarioListaDesejosController {
	
	@Autowired
	private UsuarioListaDesejosService usuarioListaDesejosService;
	
    @GetMapping()
	ModelAndView pedido() {
		return usuarioListaDesejosService.desejos();
    }
}
