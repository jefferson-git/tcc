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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mamute.cotacaoapi.model.Compra;
import br.com.mamute.cotacaoapi.model.ItensCompra;
import br.com.mamute.cotacaoapi.model.Produto;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;

@Service
public class CarrinhoService {

	private static String caminhoDaImagemProduto = "/cotacao_api/produto/";
	protected List<ItensCompra> listaCompras = new ArrayList<>();
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	protected Compra compra = new Compra();
	
	protected void totalDaCompra() {
		compra.setValorTotal(0.);
		for (ItensCompra lista : listaCompras) 
			compra.setValorTotal(compra.getValorTotal() + lista.getValorTotalItens());	
	}
	
	public ModelAndView carrinho() {
		totalDaCompra();
		ModelAndView mvCarrinho = new ModelAndView("ecommerce/carrinho");
		mvCarrinho.addObject("pedido", compra);
		mvCarrinho.addObject("departamentos", departamentoRepository.findAll());
		mvCarrinho.addObject("categorias", categoriaRepository.findAll());
		return mvCarrinho.addObject("compras", listaCompras);
    }	
	
	public String adicionar(Long id, RedirectAttributes attributes) {
		 
		int controle = 0;			
		for (ItensCompra lista : listaCompras) {
			if(lista.getProduto().getId() == id) {
				lista.setQuantidade(lista.getQuantidade() +1);
				lista.setValorTotalItens(lista.getValorTotalItens() + (lista.getValorUnitario() * lista.getQuantidade()));
				controle++;
				break;				
			}
		}
		
		Optional<Produto> produtOptional = produtoRepository.findById(id);
		Produto produto = produtOptional.get();
		
		if (controle == 0) {
			ItensCompra item = new ItensCompra();
			item.setProduto(produto);
			item.setValorUnitario(produto.getPrecoVenda());
			item.setQuantidade(item.getQuantidade() + 1);
			item.setValorTotalItens(item.getValorUnitario() * item.getQuantidade());
			listaCompras.add(item);	
		}			
		
    	return "redirect:/mamute/carrinho";			
	}
	
	public String quantidade(Long id, Integer acao, RedirectAttributes attributes) {	
		
		for (ItensCompra lista : listaCompras) {
			if(lista.getProduto().getId() == id) { 
				if(acao.equals(1)) {
					lista.setQuantidade(lista.getQuantidade() +1);
					lista.setValorTotalItens(lista.getValorUnitario() * lista.getQuantidade());
					
				}else if(acao.equals(0)){
					lista.setQuantidade((lista.getQuantidade() -1));
					lista.setValorTotalItens(lista.getValorUnitario() * lista.getQuantidade());

				}if(lista.getQuantidade().equals(0)) {
					lista.setQuantidade(lista.getQuantidade() +1);
					lista.setValorTotalItens(lista.getValorUnitario() * lista.getQuantidade());

				}				
			}				
		}		
		
    	return "redirect:/mamute/carrinho";			
	}
	
	public String remover(Long id, RedirectAttributes attributes) {			
		for (ItensCompra item : listaCompras) 
			if(item.getProduto().getId() == id) {
				listaCompras.remove(item);
				break;
			}	
		
    	return "redirect:/mamute/carrinho";			
	}
	
	public byte[] imagemProduto(String imagem) throws IOException {		
		File imagemProduto = new File(caminhoDaImagemProduto+imagem);
		return Files.readAllBytes(imagemProduto.toPath());
	}

}
