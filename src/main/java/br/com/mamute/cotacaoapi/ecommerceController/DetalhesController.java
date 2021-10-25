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

@Controller
@RequestMapping("/usuario/detalhes")
public class DetalhesController {
	
	@Autowired
	private DetalhesService detalhesService;
	
    @GetMapping()
	ModelAndView detalhes() {
		return detalhesService.detalhes();
    }
    	
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] imagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return detalhesService.imagemProduto(imagem);
	}
}
