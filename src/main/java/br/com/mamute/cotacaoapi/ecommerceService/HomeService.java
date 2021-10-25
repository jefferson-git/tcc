package br.com.mamute.cotacaoapi.ecommerceService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.MarcaRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;

@Service
public class HomeService {
	
	private static String caminhoDaImagemMarca = "/cotacao_api/marca/";
	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";
	private static String caminhoDaImagemDepartamento = "/cotacao_api/departamento/";

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository ;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CarrinhoService carrinhoService; 
	
	public ModelAndView index() {
    	ModelAndView mvIndex = new ModelAndView("index");
    	carrinhoService.totalDaCompra();
		mvIndex.addObject("marcas", marcaRepository.findAll());	
		mvIndex.addObject("departamentos", departamentoRepository.findAll());
		mvIndex.addObject("categorias", categoriaRepository.findAll());
		mvIndex.addObject("produtos", produtoRepository.findAll());
		mvIndex.addObject("pedido", carrinhoService.compra);
		mvIndex.addObject("compras", carrinhoService.listaCompras);
		return mvIndex;
    }	
	
	public byte[] ImagemDepartamento(String imagem) throws IOException {		
		File imagemDepartamento = new File(caminhoDaImagemDepartamento+imagem);
		return Files.readAllBytes(imagemDepartamento.toPath());
	}
	
	public byte[] imagemMarca(String imagem) throws IOException {		
		File imagemMarca = new File(caminhoDaImagemMarca+imagem);
		return Files.readAllBytes(imagemMarca.toPath());
	}
	
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}
}
