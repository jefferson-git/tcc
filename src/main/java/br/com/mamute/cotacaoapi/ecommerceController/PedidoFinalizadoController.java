package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.PedidoFinalizadoService;

@Controller
@RequestMapping("/usuario")
public class PedidoFinalizadoController {
	
	@Autowired
	private PedidoFinalizadoService pedidoFinalizadoService;
	
    @GetMapping("/pedido")
	ModelAndView pedido() {
		return pedidoFinalizadoService.page();
	}	
    
    @GetMapping("/pedido-finalizado")
   	ModelAndView pedidoFinalizado() {
   		return pedidoFinalizadoService.pedido();
   	}	
	
}
