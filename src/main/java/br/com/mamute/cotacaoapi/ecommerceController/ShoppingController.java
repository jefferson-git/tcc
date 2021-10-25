package br.com.mamute.cotacaoapi.ecommerceController;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.ecommerceService.ShoppingService;

@Controller
@RequestMapping("/mamute/shopping")
public class ShoppingController {
	
	@Autowired
	private ShoppingService shoppingService;
	
    @GetMapping()
	ModelAndView shopping() {
		return shoppingService.shopping();
	}	
		
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] ImagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return shoppingService.imagemProduto(imagem);
	}
}
