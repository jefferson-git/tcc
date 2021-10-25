package br.com.mamute.cotacaoapi.ecommerceController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.PedidoFinalizadoService;
import br.com.mamute.cotacaoapi.model.Departamento;

@Controller
@RequestMapping("/mamute")
public class PedidoFinalizadoController {
	
	@Autowired
	private PedidoFinalizadoService pedidoFinalizadoService;
	
    @GetMapping("/pedido")
	ModelAndView Rastreamento(Departamento departamento) {
		return pedidoFinalizadoService.page(departamento);
	}		
	
}
