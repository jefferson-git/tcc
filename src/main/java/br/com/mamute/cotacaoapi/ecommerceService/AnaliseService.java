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
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class AnaliseService {
	
	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";		
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
		
	public ModelAndView analise() {
		ModelAndView mvAnalise = new ModelAndView("ecommerce/analise");
		mvAnalise.addObject("cliente", new Cliente());
	    mvAnalise.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvAnalise.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvAnalise.addObject("pedido",carrinhoService.compra);
		mvAnalise.addObject("departamentos", departamentoRepository.findAll());
		mvAnalise.addObject("categorias", categoriaRepository.findAll());
		mvAnalise.addObject("compras", carrinhoService.listaCompras);
		return mvAnalise;
    }	
		
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}

}
