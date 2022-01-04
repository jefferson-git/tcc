package br.com.mamute.cotacaoapi.ecommerceController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.ProdutosService;
@Controller
@RequestMapping("/mamute/produto")
public class ProdutosController {
	
	@Autowired
	private ProdutosService produtosService;
    
    @GetMapping("/descricao/{id}")
	ModelAndView adicionar(@PathVariable Long id){
		return produtosService.descricao(id);
	}
    
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] imagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return produtosService.imagemProduto(imagem);
	}
}
