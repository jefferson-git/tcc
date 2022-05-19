package br.com.mamute.cotacaoapi.ecommerceController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.ecommerceService.UsuarioListaDesejosService;

@Controller
@RequestMapping("/usuario/desejos")
public class UsuarioListaDesejosController {
	
	@Autowired
	private UsuarioListaDesejosService usuarioListaDesejosService;
	
    @GetMapping()
	ModelAndView desejos() {
		return usuarioListaDesejosService.desejos();
    }
    
    @GetMapping("/adicionar/{id}")
	String adicionar(@PathVariable Long id){
		return usuarioListaDesejosService.adicionar(id);
	}
    
    @GetMapping("/remover/{id}")
	String remover(@PathVariable Long id){
		return usuarioListaDesejosService.remover(id);
	}
	
	@ResponseBody
	@GetMapping("/produto/visualizar/{imagem}")
	byte[] imagemProduto(@PathVariable(name = "imagem") String imagem) throws IOException{
		return usuarioListaDesejosService.imagemProduto(imagem);
	}
    
}
