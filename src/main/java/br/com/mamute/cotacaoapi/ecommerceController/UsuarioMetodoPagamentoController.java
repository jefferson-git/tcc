package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.UsuarioMedotoPagamentoService;

@Controller
@RequestMapping("/mamute/metodo-pagamento")
public class UsuarioMetodoPagamentoController {
	
	@Autowired
	private UsuarioMedotoPagamentoService usuarioMedotoPagamento;
	
    @GetMapping()
	ModelAndView pedido() {
		return usuarioMedotoPagamento.MedotoPagamento();
    }
}
