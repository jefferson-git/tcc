package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.UsuarioMedotoPagamentoService;
import br.com.mamute.cotacaoapi.model.CartaoDeCredito;
import br.com.mamute.cotacaoapi.model.Cliente;

@Controller
@RequestMapping("/usuario/metodo-pagamento")
public class UsuarioMetodoPagamentoController {
	
	@Autowired
	private UsuarioMedotoPagamentoService usuarioMedotoPagamento;
	
    @GetMapping()
	ModelAndView medotoPagamento (CartaoDeCredito cartaoDeCredito) {
		return usuarioMedotoPagamento.MedotoPagamento(cartaoDeCredito);
    }
    
    @PostMapping("/registrar")
	String pedido(Cliente cliente) {
		return usuarioMedotoPagamento.registrar(cliente);
    }
}
