 package br.com.mamute.cotacaoapi.ecommerceService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class ShoppingService {

	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";	
	@Autowired private ProdutoRepository produtoRepository;	
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService; 
	@Autowired private UsuarioService usuarioService; 
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	
	public ModelAndView shopping() {
		ModelAndView mvShopping = new ModelAndView("ecommerce/shopping");
		mvShopping.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvShopping.addObject("cliente", new Cliente());
		mvShopping.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvShopping.addObject("departamentos", departamentoRepository.findAll());
		mvShopping.addObject("categorias", categoriaRepository.findAll());
		mvShopping.addObject("produtos", produtoRepository.findAll());
		mvShopping.addObject("pedido", carrinhoService.compra);
		return mvShopping.addObject("compras", carrinhoService.listaCompras);
    }
	
	
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}

}
