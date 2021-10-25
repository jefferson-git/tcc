package br.com.mamute.cotacaoapi.ecommerceController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.ecommerceService.HomeService;

@Controller
@RequestMapping("/mamute")
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
    @GetMapping()
	ModelAndView index() {
		return homeService.index();
	}	
	
	@ResponseBody
	@GetMapping("/marca/visualizar/{imagem}")
	byte[] ImagemMarca(@PathVariable(name = "imagem") String imagem) throws IOException{
		return homeService.imagemMarca(imagem);
	}
	
	@ResponseBody
	@GetMapping("/departamento/visualizar/{imagem}")
	byte[] ImagemDepartamento(@PathVariable(name = "imagem") String imagem) throws IOException{
		return homeService.ImagemDepartamento(imagem);
	}
	
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] ImagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return homeService.imagemProduto(imagem);
	}
}
