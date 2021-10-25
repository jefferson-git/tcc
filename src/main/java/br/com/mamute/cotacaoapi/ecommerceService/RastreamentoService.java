package br.com.mamute.cotacaoapi.ecommerceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.com.mamute.cotacaoapi.model.Departamento;
import br.com.mamute.cotacaoapi.repository.CategoriaRepository;
import br.com.mamute.cotacaoapi.repository.DepartamentoRepository;
import br.com.mamute.cotacaoapi.repository.ProdutoRepository;

@Service
public class RastreamentoService {
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository ;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
		
	@Autowired
	private CarrinhoService carrinhoService; 
	
	public ModelAndView page(Departamento departamento) {
    	ModelAndView mvRastreamento = new ModelAndView("ecommerce/rastreamento-pedido");
    	mvRastreamento.addObject("departamentos", departamentoRepository.findAll());
    	mvRastreamento.addObject("categorias", categoriaRepository.findAll());
    	mvRastreamento.addObject("produtos", produtoRepository.findAll());
    	mvRastreamento.addObject("pedido", carrinhoService.compra);
    	mvRastreamento.addObject("compras", carrinhoService.listaCompras);
		return mvRastreamento;

    }	
}
