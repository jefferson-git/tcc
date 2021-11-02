package br.com.mamute.cotacaoapi.ecommerceService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.ListaDesejos;
import br.com.mamute.cotacaoapi.model.Produto;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;

@Service
public class UsuarioListaDesejosService {
		
	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";
	protected List <ListaDesejos> desejos = new ArrayList<>();
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
		
	@Autowired
	private listaDesejosRepository desejosRepository;
	
	public ModelAndView desejos() {
		ModelAndView mvdesejo = new ModelAndView("ecommerce/usuario-lista-desejos");
		mvdesejo.addObject("pedido",carrinhoService.compra);
		mvdesejo.addObject("departamentos", departamentoRepository.findAll());
		mvdesejo.addObject("categorias", categoriaRepository.findAll());
		mvdesejo.addObject("compras", carrinhoService.listaCompras);
		mvdesejo.addObject("desejos", desejosRepository.findAll());
		return mvdesejo;
    }	
	
	public String adicionar(Long id) {		 
	
		for (ListaDesejos lista : desejosRepository.findAll())
			if(lista.getProduto().getId() == id)
				return "redirect:/mamute/desejos";
		
		Optional<Produto> produtOptional = produtoRepository.findById(id);
		Produto produto = produtOptional.get();
	
		ListaDesejos item = new ListaDesejos();
		item.setProduto(produto);
		desejosRepository.save(item);
		
    	return "redirect:/mamute/desejos";	
	} 
	
	public String remover(Long id) {
		try {
			if(desejosRepository.findById(id).isEmpty()) {
				return "redirect:/mamute/desejos";
			}				
			
			desejosRepository.deleteById(id);
			return "redirect:/mamute/desejos";
			
			
		} catch (Exception e) {			
			return "redirect:/mamute";
		}		
	}

	
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}
}
