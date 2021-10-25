package br.com.mamute.cotacaoapi.adminController;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

import br.com.mamute.cotacaoapi.model.Categoria;
import br.com.mamute.cotacaoapi.model.Desconto;
import br.com.mamute.cotacaoapi.model.Imposto;
import br.com.mamute.cotacaoapi.model.Lucro;
import br.com.mamute.cotacaoapi.model.Marca;
import br.com.mamute.cotacaoapi.model.Produto;
import br.com.mamute.cotacaoapi.model.UnidadeDeMedida;
import br.com.mamute.cotacaoapi.service.ProdutoService;

@Controller
@RequestMapping("/dashboard-admin/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
    @GetMapping("/registrar")
	ModelAndView form( Produto produto, MultipartFile arquivo, Imposto imposto, Categoria categoria,
		Marca marca, Lucro lucro, Desconto desconto, UnidadeDeMedida unidadeDeMedida) {
		return produtoService.form(produto, arquivo, imposto, categoria, marca, lucro, desconto, unidadeDeMedida);
	}
	
	@PostMapping("/salvar")
	ModelAndView salvar(@Valid Produto produto, @RequestParam("file") MultipartFile arquivo, Imposto imposto,
		Categoria categoria, Marca marca, Lucro lucro, Desconto desconto, UnidadeDeMedida unidadeDeMedida,
		BindingResult result, RedirectAttributes attributes) {
		return produtoService.salvar(produto, arquivo, imposto, categoria, marca, lucro, desconto, unidadeDeMedida, result, attributes);			
	}
	
	@GetMapping("/listar")
	ModelAndView listar(RedirectAttributes attributes) {		
		return produtoService.listar(attributes);
	}
	
	@ResponseBody
	@GetMapping("/visualizar/{imagem}")
	byte[] visualizar(@PathVariable(name = "imagem") String imagem) throws IOException{
		return produtoService.visualizar(imagem);
	}
	
	@GetMapping("/editar/{id}")
	ModelAndView editar(@PathVariable Long id, String imagem, Imposto imposto, Categoria categoria,
			Marca marca,Lucro margem, Desconto desconto, UnidadeDeMedida unidadeDeMedida,
			BindingResult result, RedirectAttributes attributes){
		return produtoService.editar(id, imagem, imposto, categoria, marca, margem, desconto, unidadeDeMedida, result, attributes);
	}
	
	@GetMapping("/deletar/{id}")
	ModelAndView deletar(@PathVariable Long id, RedirectAttributes attributes) {		
		return produtoService.deletar(id, attributes);
	}
	
	@GetMapping("/descricao/{id}")
	ModelAndView descricao(@PathVariable Long id, RedirectAttributes attributes) {		
		return produtoService.descricao(id, attributes);
	}
	
	@PostMapping("/editado")
	ModelAndView editado(Produto produto, Imposto imposto, Categoria categoria,
			Marca marca,Lucro margem, Desconto desconto, UnidadeDeMedida unidadeDeMedida,
			BindingResult result, RedirectAttributes attributes){
		return produtoService.editado(produto, imposto, categoria, marca, margem, desconto, unidadeDeMedida, result, attributes);
	}
}
