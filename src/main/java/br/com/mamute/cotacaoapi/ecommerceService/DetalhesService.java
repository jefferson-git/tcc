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
public class DetalhesService {

	private static String caminhoDaImagem = "/cotacao_api/imagens/";
	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";		
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private CarrinhoService carrinhoService;
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
		
	public ModelAndView detalhes() {
		ModelAndView mvDetalhes = new ModelAndView("ecommerce/detalhes");
		mvDetalhes.addObject("cliente", new Cliente());
		mvDetalhes.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvDetalhes.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvDetalhes.addObject("pedido",carrinhoService.compra);
		mvDetalhes.addObject("departamentos", departamentoRepository.findAll());
		mvDetalhes.addObject("categorias", categoriaRepository.findAll());
		mvDetalhes.addObject("compras", carrinhoService.listaCompras);
		return mvDetalhes;
    }	
		
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}
	
	public byte[] imagem(String imagem) throws IOException {
		File imagemUsuario = new File(caminhoDaImagem+imagem);
		return Files.readAllBytes(imagemUsuario.toPath());
	}

}
