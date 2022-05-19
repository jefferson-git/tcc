package br.com.mamute.cotacaoapi.ecommerceController;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.ecommerceService.UsuarioPerfilService;
import br.com.mamute.cotacaoapi.model.Cliente;

@Controller
@RequestMapping("/usuario/perfil")
public class UsuarioPerfilController {
	
	@Autowired
	private UsuarioPerfilService usuarioPerfilService;
	
    @GetMapping()
	ModelAndView perfil(Cliente cliente) {
		return usuarioPerfilService.perfil(cliente);
    }
    
    @Transactional
    @PostMapping("/editar")
	ModelAndView perfil(@ Valid Cliente cliente, @RequestParam("file") MultipartFile arquivo,BindingResult result, RedirectAttributes attributes) {    	
		return usuarioPerfilService.editar(cliente, arquivo, result, attributes);
    }
    
    @ResponseBody
	@GetMapping("/mostarImagem/{imagem}")
	byte[] mostarImagem(@PathVariable(name = "imagem") String imagem) throws IOException {
    	return usuarioPerfilService.imagem(imagem);
	}
}
