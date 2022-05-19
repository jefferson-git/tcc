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

import br.com.mamute.cotacaoapi.model.Cliente;
import br.com.mamute.cotacaoapi.model.ListaDesejos;
import br.com.mamute.cotacaoapi.model.Produto;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.CompraRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.DescricaoTelefoneRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;
import br.com.mamute.cotacaoapi.repository.listaDesejosRepository;
import br.com.mamute.cotacaoapi.service.UsuarioService;

@Service
public class UsuarioListaDesejosService {
		
	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";
	protected List <ListaDesejos> desejos = new ArrayList<>();	
	@Autowired private DepartamentoRepository departamentoRepository;	
	@Autowired private CategoriaRepository categoriaRepository;	
	@Autowired private ProdutoRepository produtoRepository;	
	@Autowired private CarrinhoService carrinhoService;		
	@Autowired private listaDesejosRepository desejosRepository; 
	@Autowired private UsuarioService usuarioService;
	@Autowired private DescricaoTelefoneRepository descricaoTelefoneRepository;
	@Autowired private CompraRepository compraRepository;

	
	public ModelAndView desejos() { 
		ModelAndView mvDesejo = new ModelAndView("ecommerce/usuario-lista-desejos");
		mvDesejo.addObject("cliente", new Cliente());
		mvDesejo.addObject("descricoes", descricaoTelefoneRepository.findAll());
		mvDesejo.addObject("usuarioLogado",usuarioService.ClienteLogado());
		mvDesejo.addObject("comprasRealizadas", compraRepository.listarCompras(usuarioService.ClienteLogado().getId()));
		mvDesejo.addObject("pedido",carrinhoService.compra);
		mvDesejo.addObject("departamentos", departamentoRepository.findAll());
		mvDesejo.addObject("categorias", categoriaRepository.findAll());
		mvDesejo.addObject("compras", carrinhoService.listaCompras);
		mvDesejo.addObject("desejos", desejosRepository.findAll());
		return mvDesejo;
    }	
	
	public String adicionar(Long id) {	
		for (ListaDesejos lista : desejosRepository.findAll())
			if(lista.getProduto().getId() == id)
				return "redirect:/mamute/desejos";
		
		Optional<Produto> produtOptional = produtoRepository.findById(id);
		Produto produto = produtOptional.get();	
		ListaDesejos item = new ListaDesejos();
		item.setCliente(usuarioService.ClienteLogado());
		item.setProduto(produto);		
		desejosRepository.save(item);		
    	return "redirect:/usuario/desejos";	 
	} 
	
	public String remover(Long id) {
		try {
			if(desejosRepository.findById(id).isEmpty()) 
				return "redirect:/usuario/desejos";				
			
			desejosRepository.deleteById(id);
			return "redirect:/usuario/desejos";				
		} catch (Exception e) {			
			return "redirect:/mamute";
		}		
	}
	
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}
}
