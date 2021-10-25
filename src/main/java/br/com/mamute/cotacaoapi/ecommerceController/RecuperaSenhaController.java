package br.com.mamute.cotacaoapi.ecommerceController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.ecommerceService.DetalhesService;
import br.com.mamute.cotacaoapi.ecommerceService.RecuperaSenhaService;

@Controller
@RequestMapping("/mamute/recupera")
public class RecuperaSenhaController {
	
	@Autowired
	private RecuperaSenhaService recuperaSenhaService;
	
    @GetMapping()
	ModelAndView recupera() {
		return recuperaSenhaService.recupera();
    }
    	
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] imagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return recuperaSenhaService.imagemProduto(imagem);
	}
}
