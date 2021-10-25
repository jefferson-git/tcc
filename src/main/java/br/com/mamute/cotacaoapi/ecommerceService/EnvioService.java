package br.com.mamute.cotacaoapi.ecommerceService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;

@Service
public class EnvioService {

	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";
		
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CarrinhoService carrinhoService;
		
	public ModelAndView envio() {
		ModelAndView mvCarrinho = new ModelAndView("ecommerce/envio");
		mvCarrinho.addObject("pedido",carrinhoService.compra);
		mvCarrinho.addObject("departamentos", departamentoRepository.findAll());
		mvCarrinho.addObject("categorias", categoriaRepository.findAll());
		return mvCarrinho.addObject("compras", carrinhoService.listaCompras);
    }	
		
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}

}
