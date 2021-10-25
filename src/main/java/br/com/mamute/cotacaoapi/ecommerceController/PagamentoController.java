package br.com.mamute.cotacaoapi.ecommerceController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.PagamentoService;
@Controller
@RequestMapping("/mamute/pagamento")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
    @GetMapping()
	ModelAndView pagamento() {
		return pagamentoService.pagamento();
    }
    
    @PostMapping("/cartao")
	String dadosCartao() {
		return pagamentoService.dadosCartao();
    }
    	
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] imagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return pagamentoService.imagemProduto(imagem);
	}
}
