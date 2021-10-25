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

import br.com.mamute.cotacaoapi.ecommerceService.CarrinhoService;
@Controller
@RequestMapping("/mamute/carrinho")
public class CarrinhoController {
	
	@Autowired
	private CarrinhoService carrinhoService;
	
    @GetMapping()
	ModelAndView carrinho() {
		return carrinhoService.carrinho();
    }
    
    @GetMapping("/adicionar/{id}")
	String adicionar(@PathVariable Long id, RedirectAttributes attributes){
		return carrinhoService.adicionar(id, attributes);
	}
    
    @GetMapping("/quantidade/{id}/{acao}")
	String quantidade(@PathVariable Long id, @PathVariable Integer acao, RedirectAttributes attributes){
		return carrinhoService.quantidade(id, acao, attributes);
	}
    
    @GetMapping("/remover/{id}")
	String remover(@PathVariable Long id, RedirectAttributes attributes){
		return carrinhoService.remover(id, attributes);
	}
	
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] imagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return carrinhoService.imagemProduto(imagem);
	}
}
