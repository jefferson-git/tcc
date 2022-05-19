package br.com.mamute.cotacaoapi.ecommerceController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.ecommerceService.PagamentoService;
@Controller
@RequestMapping("/usuario/pagamento")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService;
	
    @GetMapping()
	ModelAndView pagamento() {
		return pagamentoService.pagamento();
    }
    
    @GetMapping("/cartao/{id}")
	String dadosCartao(@PathVariable Long id, RedirectAttributes attributes) {
		return pagamentoService.pagamentoCartao(id, attributes);
    }
    	
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] imagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return pagamentoService.imagemProduto(imagem);
	}
}
