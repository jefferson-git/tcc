 package br.com.mamute.cotacaoapi.ecommerceService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;

@Service
public class ShoppingService {

	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarrinhoService carrinhoService; 
	
	
	public ModelAndView shopping() {
		ModelAndView mvCarrinho = new ModelAndView("ecommerce/shopping");
		mvCarrinho.addObject("departamentos", departamentoRepository.findAll());
		mvCarrinho.addObject("categorias", categoriaRepository.findAll());
		mvCarrinho.addObject("produtos", produtoRepository.findAll());
		mvCarrinho.addObject("pedido", carrinhoService.compra);
		return mvCarrinho.addObject("compras", carrinhoService.listaCompras);
    }
	
	
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}

}
